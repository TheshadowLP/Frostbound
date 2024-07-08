package net.shadowbeast.arcanemysteries.temprature.util;

import com.google.common.collect.ImmutableSet;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.EntityLeaveLevelEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.SleepFinishedTimeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;
import net.shadowbeast.arcanemysteries.api.GatherDefaultTempModifiersEvent;
import net.shadowbeast.arcanemysteries.api.TempModifierEvent;
import net.shadowbeast.arcanemysteries.api.temperature.TempModifier;
import net.shadowbeast.arcanemysteries.api.util.Placement;
import net.shadowbeast.arcanemysteries.config.ConfigSettings;
import net.shadowbeast.arcanemysteries.config.Insulator;
import net.shadowbeast.arcanemysteries.registries.EffectsRegistry;
import net.shadowbeast.arcanemysteries.registries.ModAttributes;
import net.shadowbeast.arcanemysteries.registries.TempModifierRegistry;
import net.shadowbeast.arcanemysteries.temprature.Temperature;
import net.shadowbeast.arcanemysteries.temprature.caps.EntityTempCap;
import net.shadowbeast.arcanemysteries.temprature.caps.ModCapabilities;
import net.shadowbeast.arcanemysteries.temprature.caps.TemperatureCap;
import net.shadowbeast.arcanemysteries.temprature.interfaces.ITemperature;
import net.shadowbeast.arcanemysteries.util.MathHelper;
import net.shadowbeast.arcanemysteries.util.WorldHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/* Code Provided By A friend*/
@Mod.EventBusSubscriber
public class EntityTempManager
{
    public static final Temperature.Trait[] VALID_TEMPERATURE_TRAITS = { Temperature.Trait.CORE, Temperature.Trait.BASE, Temperature.Trait.WORLD,
            Temperature.Trait.HEAT_RESISTANCE, Temperature.Trait.COLD_RESISTANCE,
            Temperature.Trait.HEAT_DAMPENING, Temperature.Trait.COLD_DAMPENING,
            Temperature.Trait.FREEZING_POINT, Temperature.Trait.BURNING_POINT };

    public static final Temperature.Trait[] VALID_MODIFIER_TRAITS = { Temperature.Trait.CORE, Temperature.Trait.BASE,
            Temperature.Trait.RATE, Temperature.Trait.WORLD,
            Temperature.Trait.HEAT_RESISTANCE, Temperature.Trait.COLD_RESISTANCE,
            Temperature.Trait.HEAT_DAMPENING, Temperature.Trait.COLD_DAMPENING,
            Temperature.Trait.FREEZING_POINT, Temperature.Trait.BURNING_POINT };

    public static final Temperature.Trait[] VALID_ATTRIBUTE_TYPES = new Temperature.Trait[]
            {
                    Temperature.Trait.WORLD,
                    Temperature.Trait.BASE,
                    Temperature.Trait.HEAT_RESISTANCE,
                    Temperature.Trait.COLD_RESISTANCE,
                    Temperature.Trait.HEAT_DAMPENING,
                    Temperature.Trait.COLD_DAMPENING,
                    Temperature.Trait.FREEZING_POINT,
                    Temperature.Trait.BURNING_POINT
            };

    public static final Set<EntityType<? extends LivingEntity>> TEMPERATURE_ENABLED_ENTITIES = new HashSet<>(ImmutableSet.<EntityType<? extends LivingEntity>>builder().add(EntityType.PLAYER).build());

    public static final Map<Entity, LazyOptional<ITemperature>> SERVER_CAP_CACHE = new HashMap<>();
    public static final Map<Entity, LazyOptional<ITemperature>> CLIENT_CAP_CACHE = new HashMap<>();

    public static LazyOptional<ITemperature> getTemperatureCap(Entity entity)
    {
        Map<Entity, LazyOptional<ITemperature>> cache = entity.level().isClientSide ? CLIENT_CAP_CACHE : SERVER_CAP_CACHE;
        return cache.computeIfAbsent(entity, e ->
        {   LazyOptional<ITemperature> cap = e.getCapability(entity instanceof Player ? ModCapabilities.PLAYER_TEMPERATURE : ModCapabilities.ENTITY_TEMPERATURE);
            cap.addListener((opt) ->
            {
                SERVER_CAP_CACHE.remove(entity);
                CLIENT_CAP_CACHE.remove(entity);
            });
            return cap;
        });
    }

    /**
     * Attach temperature capability to entities
     */
    @SubscribeEvent
    public static void attachCapabilityToEntityHandler(AttachCapabilitiesEvent<Entity> event)
    {
        if (event.getObject() instanceof LivingEntity entity)
        {
            if (!TEMPERATURE_ENABLED_ENTITIES.contains(entity.getType())) return;

            // Make a new capability instance to attach to the entity
            ITemperature tempCap = entity instanceof Player ? new TemperatureCap() : new EntityTempCap();
            // Optional that holds the capability instance
            LazyOptional<ITemperature> capOptional = LazyOptional.of(() -> tempCap);

            // Capability provider
            ICapabilityProvider provider = new ICapabilitySerializable<CompoundTag>()
            {
                @Nonnull
                @Override
                public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction direction)
                {
                    // If the requested cap is the temperature cap, return the temperature cap
                    if (cap == ModCapabilities.PLAYER_TEMPERATURE)
                    {   return capOptional.cast();
                    }
                    return LazyOptional.empty();
                }

                @Override
                public CompoundTag serializeNBT()
                {   return tempCap.serializeNBT();
                }

                @Override
                public void deserializeNBT(CompoundTag nbt)
                {   tempCap.deserializeNBT(nbt);
                }
            };

            // Attach the capability to the entity
            event.addCapability(new ResourceLocation(ArcaneMysteries.MOD_ID, "temperature"), provider);
        }
    }

    /**
     * Add modifiers to the player and valid entities when they join the world
     */
    @SubscribeEvent
    public static void initModifiersOnEntity(EntityJoinLevelEvent event)
    {
        if (event.getEntity() instanceof LivingEntity living && !living.level().isClientSide()
                && TEMPERATURE_ENABLED_ENTITIES.contains(living.getType()))
        {
            getTemperatureCap(living).ifPresent(cap ->
            {
                // If entity has never been initialized, add default modifiers
                List<TempModifier> allModifiers = new ArrayList<>();
                for (Temperature.Trait trait : VALID_MODIFIER_TRAITS)
                {   allModifiers.addAll(cap.getModifiers(trait));
                }
                if (allModifiers.isEmpty())
                {
                    for (Temperature.Trait trait : VALID_MODIFIER_TRAITS)
                    {
                        GatherDefaultTempModifiersEvent gatherEvent = new GatherDefaultTempModifiersEvent(living, trait);
                        MinecraftForge.EVENT_BUS.post(gatherEvent);

                        cap.getModifiers(trait).addAll(gatherEvent.getModifiers());
                    }
                }
            });
        }
    }

    /**
     * Tick TempModifiers and update temperature for living entities
     */
    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event)
    {
        LivingEntity entity = event.getEntity();
        if (!TEMPERATURE_ENABLED_ENTITIES.contains(entity.getType())) return;

        getTemperatureCap(entity).ifPresent(cap ->
        {
            if (!entity.level().isClientSide)
            {   // Tick modifiers serverside
                cap.tick(entity);
            }
            else
            {   // Tick modifiers clientside
                cap.tickDummy(entity);
            }

            // Remove expired modifiers
            for (Temperature.Trait trait : VALID_MODIFIER_TRAITS)
            {
                cap.getModifiers(trait).removeIf(modifier ->
                {   int expireTime = modifier.getExpireTime();
                    return (modifier.setTicksExisted(modifier.getTicksExisted() + 1) > expireTime && expireTime != -1);
                });
            }

            if (entity instanceof Player && entity.tickCount % 60 == 0)
            {   Temperature.updateModifiers(entity, cap);
            }
        });
    }

    /**
     * Transfer the player's capability when traveling from the End
     */
    @SubscribeEvent
    public static void carryOverPersistentAttributes(PlayerEvent.Clone event)
    {
        Player oldPlayer = event.getOriginal();
        Player newPlayer = event.getEntity();

        if (!newPlayer.level().isClientSide)
        {
            // Get the old player's capability
            oldPlayer.reviveCaps();
            getTemperatureCap(oldPlayer).map(ITemperature::getPersistentAttributes).orElse(new HashSet<>())
                    .forEach(attr ->
                    {   newPlayer.getAttribute(attr).setBaseValue(oldPlayer.getAttribute(attr).getBaseValue());
                    });
            oldPlayer.invalidateCaps();
        }
    }

    /**
     * Reset the player's temperature upon respawning
     */
    @SubscribeEvent
    public static void handlePlayerReset(PlayerEvent.Clone event)
    {
        Player oldPlayer = event.getOriginal();
        Player newPlayer = event.getEntity();

        SERVER_CAP_CACHE.remove(oldPlayer);
        CLIENT_CAP_CACHE.remove(oldPlayer);

        getTemperatureCap(newPlayer).ifPresent(cap ->
        {
            if (!event.isWasDeath())
            {
                oldPlayer.reviveCaps();
                getTemperatureCap(oldPlayer).ifPresent(cap::copy);
                oldPlayer.invalidateCaps();
            }
            if (!newPlayer.level().isClientSide)
            {   Temperature.updateTemperature(newPlayer, cap, true);
            }
        });
    }

    @SubscribeEvent
    public static void invalidateDespawnedEntity(EntityLeaveLevelEvent event)
    {
        SERVER_CAP_CACHE.remove(event.getEntity());
        CLIENT_CAP_CACHE.remove(event.getEntity());
    }

    /**
     * Add default modifiers to players and temperature-enabled entities
     */
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void defineDefaultModifiers(GatherDefaultTempModifiersEvent event)
    {
        // Default TempModifiers for players
        if (event.getEntity() instanceof Player && event.getTrait() == Temperature.Trait.WORLD)
        {
            event.addModifier(new BiomeTempModifier(25).tickRate(10), Placement.Duplicates.BY_CLASS, Placement.BEFORE_FIRST);
            event.addModifier(new UndergroundTempModifier().tickRate(10), Placement.Duplicates.BY_CLASS, Placement.of(Placement.Mode.AFTER, Placement.Order.FIRST, mod -> mod instanceof BiomeTempModifier));
            event.addModifier(new BlockTempModifier().tickRate(4), Placement.Duplicates.BY_CLASS, Placement.of(Placement.Mode.AFTER, Placement.Order.FIRST, mod -> mod instanceof UndergroundTempModifier));

        }
        // Default TempModifiers for other temperature-enabled entities
        else if (event.getTrait() == Temperature.Trait.WORLD && TEMPERATURE_ENABLED_ENTITIES.contains(event.getEntity().getType()))
        {   // Basic modifiers
            event.addModifier(new BiomeTempModifier(16).tickRate(40), Placement.Duplicates.BY_CLASS, Placement.BEFORE_FIRST);
            event.addModifier(new UndergroundTempModifier().tickRate(40), Placement.Duplicates.BY_CLASS, Placement.of(Placement.Mode.AFTER, Placement.Order.FIRST, mod -> mod instanceof BiomeTempModifier));
            event.addModifier(new BlockTempModifier(4).tickRate(20), Placement.Duplicates.BY_CLASS, Placement.of(Placement.Mode.AFTER, Placement.Order.FIRST, mod -> mod instanceof UndergroundTempModifier));

        }
    }


    @SubscribeEvent
    public static void cancelImmuneModifiers(TempModifierEvent.Calculate.Modify event)
    {
        if (!Arrays.stream(VALID_ATTRIBUTE_TYPES).toList().contains(event.getTrait())) return;
        TempModifier mod = event.getModifier();
        ResourceLocation modifierKey = TempModifierRegistry.getKey(mod);

        getTemperatureCap(event.getEntity()).ifPresent(cap ->
        {

        });
    }

    /**
     * Handle modifiers for freezing, burning, and being wet
     */
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event)
    {
        Player player = event.player;

        // Water / Rain
        if (!player.level().isClientSide && event.phase == TickEvent.Phase.START)
        {
            if (player.tickCount % 5 == 0)
            {
                if (!player.isSpectator() && WorldHelper.isInWater(player) || player.tickCount % 40 == 0
                        && WorldHelper.isRainingAt(player.level(), player.blockPosition()))
                {   Temperature.addModifier(player, new WaterTempModifier(0.01f).tickRate(5), Temperature.Trait.WORLD, Placement.Duplicates.BY_CLASS);
                }

                if (player.isFreezing())
                {   Temperature.addOrReplaceModifier(player, new FreezingTempModifier(player.getTicksFrozen() / 13.5f).expires(5), Temperature.Trait.BASE, Placement.Duplicates.BY_CLASS);
                }

                if (player.isOnFire())
                {   Temperature.addOrReplaceModifier(player, new FireTempModifier().expires(5), Temperature.Trait.BASE, Placement.Duplicates.BY_CLASS);
                }
            }

            if (player.isFreezing() && player.getTicksFrozen() > 0)
            {
                AtomicReference<Double> insulation = new AtomicReference<>((double) 0);
                boolean hasIcePotion = player.hasEffect(EffectsRegistry.ICE_RESISTANCE.get()) && ConfigSettings.ICE_RESISTANCE_ENABLED.get();

                if (!hasIcePotion)
                {
                    Temperature.getModifier(player, Temperature.Trait.RATE, ArmorInsulationTempModifier.class).ifPresent(insulModifier ->
                    {   insulation.updateAndGet(v -> (v + insulModifier.getNBT().getDouble("Hot") + insulModifier.getNBT().getDouble("Cold")));
                    });
                }

                if (!(hasIcePotion || insulation.get() > 0) && (player.tickCount % Math.max(1, 37 - insulation.get())) == 0)
                {   player.setTicksFrozen(player.getTicksFrozen() - 1);
                }
            }
        }
    }

    @SubscribeEvent
    public static void updateAttributeModifiersOnSlotChange(LivingEquipmentChangeEvent event)
    {
        updateInsulationAttributeModifiers(event.getEntity());
    }

    public static void updateInsulationAttributeModifiers(LivingEntity entity)
    {
        for (Map.Entry<ItemStack, Insulator> insulationItem : getInsulatorsOnEntity(entity).entrySet())
        {
            Insulator insulator = insulationItem.getValue();
            ItemStack stack = insulationItem.getKey();
            if (insulator.test(entity, stack))
            {
                for (Map.Entry<Attribute, AttributeModifier> entry : insulator.attributes().getMap().entries())
                {
                    Attribute attribute = entry.getKey();
                    AttributeModifier modifier = entry.getValue();
                    AttributeInstance instance = entity.getAttribute(attribute);
                    if (instance != null)
                    {   instance.addTransientModifier(modifier);
                    }
                }
            }
        }
    }

    /**
     * Cancel freezing damage when the player has the Ice Resistance effect
     */
    @SubscribeEvent
    public static void cancelFreezingDamage(LivingAttackEvent event)
    {
        if (event.getSource().equals(event.getEntity().level().damageSources().freeze()) && event.getEntity().hasEffect(EffectsRegistry.ICE_RESISTANCE.get()) && ConfigSettings.ICE_RESISTANCE_ENABLED.get())
        {   event.setCanceled(true);
        }
    }

    /**
     * Handle HearthTempModifier when the player has the Insulation effect
     */

    /**
     * Improve the player's temperature when they sleep
     */
    @SubscribeEvent
    public static void onSleep(SleepFinishedTimeEvent event)
    {
        if (!event.getLevel().isClientSide())
        {
            event.getLevel().players().forEach(player ->
            {
                if (player.isSleeping())
                {
                    // Divide the player's current temperature by 4
                    getTemperatureCap(player).ifPresent(cap ->
                    {
                        double temp = cap.getTrait(Temperature.Trait.CORE);
                        cap.setTrait(Temperature.Trait.CORE, temp / 4f);
                        Temperature.updateTemperature(player, cap, true);
                    });
                }
            });
        }
    }

    /**
     * Handle insulation on mounted entity
     */
    @SubscribeEvent
    public static void playerRiding(TickEvent.PlayerTickEvent event)
    {
        if (event.phase == TickEvent.Phase.START && !event.player.level().isClientSide() && event.player.tickCount % 5 == 0)
        {
            Player player = event.player;
            if (player.getVehicle() != null)
            {
                Entity mount = player.getVehicle();

            }
        }
    }
    

    public static Set<EntityType<? extends LivingEntity>> getEntitiesWithTemperature()
    {   return ImmutableSet.copyOf(TEMPERATURE_ENABLED_ENTITIES);
    }

    public static Map<ItemStack, Insulator> getInsulatorsOnEntity(LivingEntity entity)
    {
        Map<ItemStack, Insulator> insulators = new HashMap<>();
        for (EquipmentSlot slot : EquipmentSlot.values())
        {
            if (!slot.isArmor()) continue;
            ItemStack stack = entity.getItemBySlot(slot);
            if (!stack.isEmpty())
            {
            }
        }

        return insulators;
    }

    /**
     * Sets the corresponding attribute value for the given {@link Temperature.Trait}.
     * @param trait the type or ability to get the attribute for
     */
    public static void setAttribute(Temperature.Trait trait, LivingEntity entity, double value)
    {
        switch (trait)
        {
            case WORLD -> MathHelper.doIfNotNull(entity.getAttribute(ModAttributes.WORLD_TEMPERATURE), att -> att.setBaseValue(value));
            case BASE  -> MathHelper.doIfNotNull(entity.getAttribute(ModAttributes.BASE_BODY_TEMPERATURE), att -> att.setBaseValue(value));
            case HEAT_RESISTANCE -> MathHelper.doIfNotNull(entity.getAttribute(ModAttributes.HEAT_RESISTANCE), att -> att.setBaseValue(value));
            case COLD_RESISTANCE -> MathHelper.doIfNotNull(entity.getAttribute(ModAttributes.COLD_RESISTANCE), att -> att.setBaseValue(value));
            case HEAT_DAMPENING  -> MathHelper.doIfNotNull(entity.getAttribute(ModAttributes.HEAT_DAMPENING), att -> att.setBaseValue(value));
            case COLD_DAMPENING  -> MathHelper.doIfNotNull(entity.getAttribute(ModAttributes.COLD_DAMPENING), att -> att.setBaseValue(value));
            case FREEZING_POINT -> MathHelper.doIfNotNull(entity.getAttribute(ModAttributes.FREEZING_POINT), att -> att.setBaseValue(value));
            case BURNING_POINT  -> MathHelper.doIfNotNull(entity.getAttribute(ModAttributes.BURNING_POINT), att -> att.setBaseValue(value));
        }
    }

    /**
     * Gets the corresponding attribute value for the given {@link Temperature.Trait}.
     * @param trait the type or ability to get the attribute for
     */
    @Nullable
    public static AttributeInstance getAttribute(Temperature.Trait trait, LivingEntity entity)
    {
        return switch (trait)
        {
            case WORLD -> entity.getAttribute(ModAttributes.WORLD_TEMPERATURE);
            case BASE  -> entity.getAttribute(ModAttributes.BASE_BODY_TEMPERATURE);
            case FREEZING_POINT  -> entity.getAttribute(ModAttributes.FREEZING_POINT);
            case BURNING_POINT   -> entity.getAttribute(ModAttributes.BURNING_POINT);
            case HEAT_RESISTANCE -> entity.getAttribute(ModAttributes.HEAT_RESISTANCE);
            case COLD_RESISTANCE -> entity.getAttribute(ModAttributes.COLD_RESISTANCE);
            case HEAT_DAMPENING  -> entity.getAttribute(ModAttributes.HEAT_DAMPENING);
            case COLD_DAMPENING  -> entity.getAttribute(ModAttributes.COLD_DAMPENING);

            default -> throw ArcaneMysteries.LOGGER.throwing(new IllegalArgumentException("\"" + trait + "\" is not a valid trait!"));
        };
    }

    public static Collection<AttributeModifier> getAllAttributeModifiers(LivingEntity entity, AttributeInstance attribute, @Nullable AttributeModifier.Operation operation)
    {
        Collection<AttributeModifier> modifiers = new ArrayList<>(operation == null
                ? attribute.getModifiers()
                : attribute.getModifiers(operation));
        modifiers.addAll(getAllEquipmentAttributeModifiers(entity, attribute, operation));

        return modifiers;
    }

    public static Collection<AttributeModifier> getAllEquipmentAttributeModifiers(LivingEntity entity, AttributeInstance attribute, @Nullable AttributeModifier.Operation operation)
    {
        Collection<AttributeModifier> modifiers = new ArrayList<>();

        for (EquipmentSlot slot : EquipmentSlot.values())
        {
            if (!slot.isArmor()) continue;
            ItemStack stack = entity.getItemBySlot(slot);
            if (!stack.isEmpty())
            {   modifiers.addAll(ItemInsulationManager.getAttributeModifiersForSlot(stack, attribute.getAttribute(), slot, operation, entity));
            }
        }
        return modifiers;
    }

    public static AttributeModifier makeAttributeModifier(Temperature.Trait trait, double value, AttributeModifier.Operation operation)
    {
        return switch (trait)
        {
            case WORLD -> new AttributeModifier("World Temperature Modifier", value, operation);
            case BASE  -> new AttributeModifier("Base Body Temperature Modifier", value, operation);

            case FREEZING_POINT -> new AttributeModifier("Freezing Point Modifier", value, operation);
            case BURNING_POINT  -> new AttributeModifier("Burning Point Modifier", value, operation);
            case HEAT_RESISTANCE -> new AttributeModifier("Heat Resistance Modifier", value, operation);
            case COLD_RESISTANCE -> new AttributeModifier("Cold Resistance Modifier", value, operation);
            case HEAT_DAMPENING  -> new AttributeModifier("Heat Dampening Modifier", value, operation);
            case COLD_DAMPENING  -> new AttributeModifier("Cold Dampening Modifier", value, operation);
            default -> throw ArcaneMysteries.LOGGER.throwing(new IllegalArgumentException("\"" + trait + "\" is not a valid trait!"));
        };
    }

    public static boolean isTemperatureAttribute(Attribute attribute)
    {
        return MathHelper.containsAny(ForgeRegistries.ATTRIBUTES.getKey(attribute).toString(),
                Arrays.stream(EntityTempManager.VALID_ATTRIBUTE_TYPES)
                        .map(Temperature.Trait::getSerializedName).toArray(String[]::new));
    }
}
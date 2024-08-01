package net.shadowbeast.frostbound.temprature.caps;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.registries.ForgeRegistries;
import net.shadowbeast.frostbound.api.temperature.TempModifier;
import net.shadowbeast.frostbound.config.ConfigSettings;
import net.shadowbeast.frostbound.registries.EffectsRegistry;
import net.shadowbeast.frostbound.registries.ModDamageSources;
import net.shadowbeast.frostbound.temprature.Temperature;
import net.shadowbeast.frostbound.temprature.interfaces.ITemperature;
import net.shadowbeast.frostbound.temprature.util.EntityTempManager;
import net.shadowbeast.frostbound.util.MathHelper;
import net.shadowbeast.frostbound.util.nbt.NBTHelper;

import java.util.*;
import java.util.function.Supplier;

import static net.shadowbeast.frostbound.temprature.util.EntityTempManager.VALID_MODIFIER_TRAITS;
import static net.shadowbeast.frostbound.temprature.util.EntityTempManager.VALID_TEMPERATURE_TRAITS;

/**
 * Logic Again By a Friend And some code too 😅
 * Holds all the information regarding the entity's temperature. This should very rarely be used directly.
 */
public class AbsTemperatureCapability implements ITemperature
{
    boolean changed = true;
    int syncTimer = 0;
    Temperature.Units preferredUnits = Temperature.Units.F;

    private final Set<Attribute> persistentAttributes = new HashSet<>();

    // Map valid temperature types to a new EnumMap
    private final EnumMap<Temperature.Trait, Double> traits = Arrays.stream(VALID_TEMPERATURE_TRAITS).collect(
            () -> new EnumMap<>(Temperature.Trait.class),
            (map, type) -> map.put(type, 0.0),
            EnumMap::putAll);

    // Map valid modifier types to a new EnumMap
    private final EnumMap<Temperature.Trait, List<TempModifier>> modifiers = Arrays.stream(VALID_MODIFIER_TRAITS).collect(
            () -> new EnumMap<>(Temperature.Trait.class),
            (map, type) -> map.put(type, new ArrayList<>()),
            EnumMap::putAll);

    public boolean showBodyTemp;
    public boolean showWorldTemp;

    @Override
    public double getTrait(Temperature.Trait trait)
    {   // Special case for BODY
        if (trait == Temperature.Trait.BODY) return getTrait(Temperature.Trait.CORE) + getTrait(Temperature.Trait.BASE);
        // Throw exception if this temperature trait is not supported
        if (!traits.containsKey(trait))
        {   throw new IllegalArgumentException("Invalid temperature trait: " + trait);
        }

        return traits.get(trait);
    }

    @Override
    public EnumMap<Temperature.Trait, Double> getTraits()
    {   return new EnumMap<>(traits);
    }

    @Override
    public void setTrait(Temperature.Trait trait, double value)
    {
        changed |= switch (trait)
        {
            case CORE  -> ((int) value) != ((int) getTrait(Temperature.Trait.CORE));
            case BASE  -> ((int) value) != ((int) getTrait(Temperature.Trait.BASE));
            case WORLD -> Math.abs(value - getTrait(Temperature.Trait.WORLD)) >= 0.02;
            default -> true;
        };
        // Throw exception if this temperature trait is not supported
        if (traits.replace(trait, value) == null)
        {   throw new IllegalArgumentException("Invalid temperature trait: " + trait);
        }
    }

    public void setTrait(Temperature.Trait trait, double value, LivingEntity entity)
    {
        double oldTemp = this.getTrait(trait);
        this.setTrait(trait, value);
        if (oldTemp != value && entity instanceof ServerPlayer player)
        {
        }
    }

    @Override
    public void addModifier(TempModifier modifier, Temperature.Trait trait)
    {   modifiers.get(trait).add(modifier);
    }

    @Override
    public List<TempModifier> getModifiers(Temperature.Trait trait)
    {   // Throw exception if this modifier type is not supported
        return modifiers.computeIfAbsent(trait, t ->
        {   throw new IllegalArgumentException("Invalid modifier trait: " + t);
        });
    }

    @Override
    public boolean hasModifier(Temperature.Trait trait, Class<? extends TempModifier> mod)
    {   return getModifiers(trait).stream().anyMatch(mod::isInstance);
    }

    @Override
    public void markPersistentAttribute(Attribute attribute)
    {   persistentAttributes.add(attribute);
    }

    @Override
    public void clearPersistentAttribute(Attribute attribute)
    {   persistentAttributes.remove(attribute);
    }

    @Override
    public Collection<Attribute> getPersistentAttributes()
    {   return persistentAttributes;
    }

    @Override
    public void clearModifiers(Temperature.Trait trait)
    {   getModifiers(trait).clear();
    }

    @Override
    public Temperature.Units getPreferredUnits()
    {   return preferredUnits;
    }

    @Override
    public void setPreferredUnits(Temperature.Units units)
    {   preferredUnits = units;
    }

    public boolean shouldShowBodyTemp()
    {   return showBodyTemp;
    }

    public boolean showAdvancedWorldTemp()
    {   return showWorldTemp;
    }

    /* See Temperature.class for more temperature-related methods */

    /**
     * Used for clientside ticking of TempModifiers. The result is not used.
     */
    @Override
    public void tickDummy(LivingEntity entity)
    {
        if (!(entity instanceof Player player)) return;

        Temperature.apply(0, player, Temperature.Trait.WORLD, getModifiers(Temperature.Trait.WORLD));
        Temperature.apply(getTrait(Temperature.Trait.CORE), player, Temperature.Trait.CORE, getModifiers(Temperature.Trait.CORE));
        Temperature.apply(0, player, Temperature.Trait.BASE, getModifiers(Temperature.Trait.BASE));
    }

    @Override
    public void tick(LivingEntity entity)
    {
        // Tick TempModifiers and pre-attribute-bases
        double newWorldTemp = this.modifyFromAttribute(entity, Temperature.Trait.WORLD, 0);
        double newBaseTemp  = this.modifyFromAttribute(entity, Temperature.Trait.BASE, 0);
        double newCoreTemp  = Temperature.apply(getTrait(Temperature.Trait.CORE), entity, Temperature.Trait.CORE, getModifiers(Temperature.Trait.CORE));

        // Get abilities
        double maxTemp = this.modifyFromAttribute(entity, Temperature.Trait.BURNING_POINT, ConfigSettings.MAX_TEMP.get());
        double minTemp = this.modifyFromAttribute(entity, Temperature.Trait.FREEZING_POINT, ConfigSettings.MIN_TEMP.get());
        double coldDampening   = this.modifyFromAttribute(entity, Temperature.Trait.COLD_DAMPENING, 0);
        double heatDampening   = this.modifyFromAttribute(entity, Temperature.Trait.HEAT_DAMPENING, 0);
        double coldResistance  = this.modifyFromAttribute(entity, Temperature.Trait.COLD_RESISTANCE, 0);
        double heatResistance  = this.modifyFromAttribute(entity, Temperature.Trait.HEAT_RESISTANCE, 0);

        // 1 if newWorldTemp is above max, -1 if below min, 0 if between the values (safe)
        int worldTempSign = MathHelper.getSignForRange(newWorldTemp, minTemp, maxTemp);

        boolean isFullyColdDampened = worldTempSign < 0 && coldDampening >= 1;
        boolean isFullyHeatDampened = worldTempSign > 0 && heatDampening >= 1;

        // Don't change player temperature if they're in creative/spectator mode
        if (worldTempSign != 0 && (!(entity instanceof Player player) || !player.isCreative()) && !entity.isSpectator())
        {
            // How much hotter/colder the player's temp is compared to max/min
            double difference = Math.abs(newWorldTemp - MathHelper.clamp(newWorldTemp, minTemp, maxTemp));

            // How much the player's temperature should change
            double changeBy = (Math.max(
                    // Change proportionally to the extremity of the world temperature
                    (difference / 7d) * ConfigSettings.TEMP_RATE.get().floatValue(),
                    // Ensure a minimum speed for temperature change
                    Math.abs(ConfigSettings.TEMP_RATE.get().floatValue() / 50d)
                    // If it's hot or cold
            ) * worldTempSign);

            // Temp is decreasing; apply cold dampening
            if (changeBy < 0)
            {   changeBy = (coldDampening < 0
                    // Cold dampening is negative; increase the change by the dampening
                    ? changeBy * (1 + Math.abs(coldDampening))
                    // Cold dampening is positive; apply the change as a percentage of the dampening
                    : MathHelper.blend(changeBy, 0, coldDampening, 0, 1));
            }
            // Temp is increasing; apply heat dampening
            else if (changeBy > 0)
            {   changeBy = (heatDampening < 0
                    // Heat dampening is negative; increase the change by the dampening
                    ? changeBy * (1 + Math.abs(heatDampening))
                    // Heat dampening is positive; apply the change as a percentage of the dampening
                    : MathHelper.blend(changeBy, 0, heatDampening, 0, 1));
            }
            newCoreTemp += Temperature.apply(changeBy, entity, Temperature.Trait.RATE, this.getModifiers(Temperature.Trait.RATE));
        }

        // Get the sign of the player's core temperature (-1, 0, or 1)
        int coreTempSign = MathHelper.sign(newCoreTemp);
        // If needed, blend the player's temperature back to 0
        if (this.getModifiers(Temperature.Trait.CORE).isEmpty())
        {
            double amount = 0;
            // Player is fully cold dampened & body is cold
            if (isFullyColdDampened && coreTempSign < 0)
            {   amount = ConfigSettings.TEMP_RATE.get() / 10d;
            }
            // Player is fully heat dampened & body is hot
            else if (isFullyHeatDampened && coreTempSign > 0)
            {   amount = ConfigSettings.TEMP_RATE.get() / -10d;
            }
            // Else if the player's core temp is not the same as the world temp
            else if (coreTempSign != 0 && coreTempSign != worldTempSign)
            {   amount = (coreTempSign == 1 ? newWorldTemp - maxTemp : newWorldTemp - minTemp) / 3;
            }
            // Blend back to 0
            if (amount != 0)
            {   double changeBy = MathHelper.maxAbs(amount * ConfigSettings.TEMP_RATE.get(), ConfigSettings.TEMP_RATE.get() / 10d * -coreTempSign);
                newCoreTemp += MathHelper.minAbs(changeBy, -getTrait(Temperature.Trait.CORE));
            }
        }

        // Write the new temperature values
        this.setTrait(Temperature.Trait.CORE, MathHelper.clamp(newCoreTemp, -150, 150), entity);
        this.setTrait(Temperature.Trait.BASE, MathHelper.clamp(newBaseTemp, -150, 150), entity);
        this.setTrait(Temperature.Trait.WORLD, newWorldTemp, entity);
        // Write the new ability values
        this.setTrait(Temperature.Trait.BURNING_POINT, maxTemp);
        this.setTrait(Temperature.Trait.FREEZING_POINT, minTemp);
        this.setTrait(Temperature.Trait.COLD_RESISTANCE, coldResistance);
        this.setTrait(Temperature.Trait.HEAT_RESISTANCE, heatResistance);
        this.setTrait(Temperature.Trait.COLD_DAMPENING, coldDampening);
        this.setTrait(Temperature.Trait.HEAT_DAMPENING, heatDampening);

        if (syncTimer > 0)
        {   syncTimer--;
        }

        // Sync the temperature values to the client
        if (changed && syncTimer <= 0)
        {   this.syncValues(entity);
        }

        // Deal damage to the player at a set interval if temperature is critical
        this.tickHurting(entity, heatResistance, coldResistance);
    }

    private double modifyFromAttribute(LivingEntity entity, Temperature.Trait type, double baseValue)
    {
        Supplier<Double> defaultSupplier = () -> Temperature.apply(baseValue, entity, type, this.getModifiers(type));
        AttributeInstance attribute = EntityTempManager.getAttribute(type, entity);
        // If the attribute is null, return the default value
        if (attribute == null)
        {   return defaultSupplier.get();
        }
        // If base attribute is unset
        else
        {
            double base = MathHelper.safeDouble(attribute.getBaseValue()).orElse(defaultSupplier.get());

            for (AttributeModifier mod : EntityTempManager.getAllAttributeModifiers(entity, attribute, AttributeModifier.Operation.ADDITION))
            {   base += mod.getAmount();
            }
            double value = base;
            for (AttributeModifier mod : EntityTempManager.getAllAttributeModifiers(entity, attribute, AttributeModifier.Operation.MULTIPLY_BASE))
            {   value += base * mod.getAmount();
            }
            for (AttributeModifier mod : EntityTempManager.getAllAttributeModifiers(entity, attribute, AttributeModifier.Operation.MULTIPLY_TOTAL))
            {   value *= 1.0D + mod.getAmount();
            }
            return value;
        }
    }

    public void syncValues(LivingEntity entity)
    {   Temperature.updateTemperature(entity, this, false);
        changed = false;
        syncTimer = 5;
    }

    public void tickHurting(LivingEntity entity, double heatResistance, double coldResistance)
    {
        double bodyTemp = getTrait(Temperature.Trait.BODY);

        boolean hasFireResist = entity.hasEffect(MobEffects.FIRE_RESISTANCE);
        boolean hasIceResist = entity.hasEffect(EffectsRegistry.ICE_RESISTANCE.get());

        Registry<DamageType> damageTypes = entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE);

        if (entity.tickCount % 40 == 0)
        {
            if (bodyTemp >= 100 && !(hasFireResist && ConfigSettings.FIRE_RESISTANCE_ENABLED.get()))
            {   DamageSource hot = new DamageSource(damageTypes.getHolderOrThrow(ModDamageSources.HOT));
                DamageSource hotScaling = new DamageSource(damageTypes.getHolderOrThrow(ModDamageSources.HOT_SCALING));

                entity.hurt(ConfigSettings.DAMAGE_SCALING.get() ? hotScaling : hot, (float) MathHelper.blend(ConfigSettings.TEMP_DAMAGE.get(), 0, heatResistance, 0, 1));
            }
            else if (bodyTemp <= -100 && !(hasIceResist && ConfigSettings.ICE_RESISTANCE_ENABLED.get()))
            {   DamageSource cold = new DamageSource(damageTypes.getHolderOrThrow(ModDamageSources.COLD));
                DamageSource coldScaling = new DamageSource(damageTypes.getHolderOrThrow(ModDamageSources.COLD_SCALING));

                entity.hurt(ConfigSettings.DAMAGE_SCALING.get() ? coldScaling : cold, (float) MathHelper.blend(ConfigSettings.TEMP_DAMAGE.get(), 0, coldResistance, 0, 1));
            }
        }
    }

    @Override
    public void copy(ITemperature cap)
    {
        // Copy temperature values
        for (Temperature.Trait trait : VALID_TEMPERATURE_TRAITS)
        {   this.setTrait(trait, cap.getTrait(trait));
        }

        // Copy the modifiers
        for (Temperature.Trait trait : VALID_MODIFIER_TRAITS)
        {   this.getModifiers(trait).clear();
            this.getModifiers(trait).addAll(cap.getModifiers(trait));
        }

        // Copy persistent attributes
        this.persistentAttributes.clear();
        this.persistentAttributes.addAll(cap.getPersistentAttributes());

        // Copy preferred units
        this.setPreferredUnits(cap.getPreferredUnits());
    }

    @Override
    public CompoundTag serializeNBT()
    {
        CompoundTag nbt = new CompoundTag();
        // Save the player's temperatures
        nbt.put("Traits", this.serializeTraits());
        // Save the player's modifiers
        nbt.put("TempModifiers", this.serializeModifiers());
        // Save the player's persistent attributes
        ListTag attributes = new ListTag();
        for (Attribute attribute : this.getPersistentAttributes())
        {   attributes.add(StringTag.valueOf(ForgeRegistries.ATTRIBUTES.getKey(attribute).toString()));
        }
        nbt.put("PersistentAttributes", attributes);
        return nbt;
    }

    @Override
    public CompoundTag serializeTraits()
    {
        CompoundTag nbt = new CompoundTag();

        // Save the player's temperature data
        for (Map.Entry<Temperature.Trait, Double> trait : traits.entrySet())
        {   nbt.putDouble(NBTHelper.getTraitTagKey(trait.getKey()), trait.getValue());
        }
        return nbt;
    }

    @Override
    public CompoundTag serializeModifiers()
    {
        CompoundTag nbt = new CompoundTag();

        // Save the player's modifiers
        for (Temperature.Trait trait : VALID_MODIFIER_TRAITS)
        {
            ListTag modifiers = new ListTag();
            for (TempModifier modifier : this.getModifiers(trait))
            {   modifiers.add(NBTHelper.modifierToTag(modifier));
            }
            // Write the list of modifiers to the player's persistent data
            nbt.put(NBTHelper.getTraitTagKey(trait), modifiers);
        }
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt)
    {   // Load the player's temperatures
        deserializeTraits(nbt.getCompound("Traits"));
        // Load the player's modifiers
        deserializeModifiers(nbt.getCompound("TempModifiers"));
        // Load the player's persistent attributes
        ListTag attributes = nbt.getList("PersistentAttributes", 8);
        for (int i = 0; i < attributes.size(); i++)
        {   this.markPersistentAttribute(ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation(attributes.getString(i))));
        }
    }

    @Override
    public void deserializeTraits(CompoundTag nbt)
    {
        for (Temperature.Trait trait : VALID_TEMPERATURE_TRAITS)
        {   setTrait(trait, nbt.getDouble(NBTHelper.getTraitTagKey(trait)));
        }
    }

    @Override
    public void deserializeModifiers(CompoundTag nbt)
    {
        for (Temperature.Trait trait : VALID_MODIFIER_TRAITS)
        {
            getModifiers(trait).clear();

            // Get the list of modifiers from the player's persistent data
            ListTag modifiers = nbt.getList(NBTHelper.getTraitTagKey(trait), 10);

            // For each modifier in the list
            modifiers.forEach(modNBT ->
            {
                NBTHelper.tagToModifier((CompoundTag) modNBT).ifPresent(modifier ->
                {   getModifiers(trait).add(modifier);
                });
            });
        }
    }
}

package net.shadowbeast.frostbound.registries;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.shadowbeast.frostbound.Frostbound;
import net.shadowbeast.frostbound.api.BlockTempRegisterEvent;
import net.shadowbeast.frostbound.api.TempModifierRegisterEvent;
import net.shadowbeast.frostbound.config.BlockTempConfig;
import net.shadowbeast.frostbound.config.ConfigHelper;
import net.shadowbeast.frostbound.core.WorldSettingConfig;
import net.shadowbeast.frostbound.temprature.temp.LavaBlockTemp;
import net.shadowbeast.frostbound.temprature.util.*;
import net.shadowbeast.frostbound.util.MathHelper;
import net.shadowbeast.frostbound.util.nbt.NBTHelper;

import java.util.*;
import java.util.function.Predicate;

@Mod.EventBusSubscriber
public class TempModifierInit
{
    @SubscribeEvent
    public static void onServerStart(ServerStartingEvent event)
    {   buildRegistries();
    }

    // Trigger registry events
    public static void buildRegistries()
    {
        TempModifierRegistry.flush();
        BlockTempRegistry.flush();

        try { MinecraftForge.EVENT_BUS.post(new TempModifierRegisterEvent()); }
        catch (Exception e)
        {
            Frostbound.LOGGER.error("Registering TempModifiers failed!");
            throw e;
        }

        try { MinecraftForge.EVENT_BUS.post(new BlockTempRegisterEvent()); }
        catch (Exception e)
        {
            Frostbound.LOGGER.error("Registering BlockTemps failed!");
            throw e;
        }
    }

    // Register BlockTemps
    @SubscribeEvent
    public static void registerBlockTemps(BlockTempRegisterEvent event)
    {
        long startMS = System.currentTimeMillis();
        // Auto-generate BlockTemps from config
        for (List<?> effectBuilder : WorldSettingConfig.getInstance().getBlockTemps())
        {
            try
            {
                // Get IDs associated with this config entry
                String[] blockIDs = ((String) effectBuilder.get(0)).split(",");

                // Parse block IDs into blocks
                Block[] effectBlocks = Arrays.stream(blockIDs).map(ConfigHelper::getBlocks).flatMap(List::stream).toArray(Block[]::new);

                // Get block predicate
                Map<String, Predicate<BlockState>> blockPredicates = effectBuilder.size() >= 6 && effectBuilder.get(5) instanceof String
                        ? ConfigHelper.getBlockStatePredicates(effectBlocks[0], (String) effectBuilder.get(5))
                        : new HashMap<>();

                event.register(new BlockTempConfig(blockPredicates, effectBlocks)
                {
                    // Temp of block
                    final double blockTemp = ((Number) effectBuilder.get(1)).doubleValue();
                    // Range of effect
                    final double blockRange = ((Number) effectBuilder.get(2)).doubleValue();

                    // Weakens over distance?
                    final boolean weaken = effectBuilder.size() > 3 && effectBuilder.get(3) instanceof Boolean
                            ? (Boolean) effectBuilder.get(3)
                            : true;

                    // Get min/max effect
                    final double maxChange = effectBuilder.size() > 4 && effectBuilder.get(4) instanceof Number
                            ? ((Number) effectBuilder.get(4)).doubleValue()
                            : Double.MAX_VALUE;

                    final Optional<CompoundTag> tag = effectBuilder.size() > 6 && effectBuilder.get(6) instanceof String
                            ? Optional.of(NBTHelper.parseCompoundNbt((String) effectBuilder.get(5)))
                            : Optional.empty();

                    final double maxEffect = blockTemp > 0 ?  maxChange :  Double.MAX_VALUE;
                    final double minEffect = blockTemp < 0 ? -maxChange : -Double.MAX_VALUE;

                    @Override
                    public double getTemperature(Level level, LivingEntity entity, BlockState state, BlockPos pos, double distance)
                    {
                        // Check the list of predicates first
                        if (blockPredicates.isEmpty() || this.testPredicates(state))
                        {
                            if (tag.isPresent())
                            {
                                BlockEntity blockEntity = level.getBlockEntity(pos);
                                if (blockEntity != null)
                                {
                                    CompoundTag blockTag = blockEntity.saveWithFullMetadata();
                                    for (String key : tag.get().getAllKeys())
                                    {
                                        if (!tag.get().get(key).equals(blockTag.get(key)))
                                        {   return 0;
                                        }
                                    }
                                }
                            }
                            return weaken ? MathHelper.blend(blockTemp, 0, distance, 0.5, blockRange) : blockTemp;
                        }
                        return  0;
                    }

                    @Override
                    public double maxEffect()
                    {   return maxEffect;
                    }

                    @Override
                    public double minEffect()
                    {   return minEffect;
                    }
                });
            }
            catch (Exception e)
            {   Frostbound.LOGGER.error("Invalid configuration for BlockTemps", e);
                break;
            }
        }

        event.register(new LavaBlockTemp());
        //TODO::FIX
//        event.register(new FurnaceBlockTemp());
//        event.register(new CampfireBlockTemp());
//        event.register(new NetherPortalBlockTemp());
        Frostbound.LOGGER.debug("Registered BlockTemps in {}ms", System.currentTimeMillis() - startMS);
    }

    // Register TempModifiers
    @SubscribeEvent
    public static void registerTempModifiers(TempModifierRegisterEvent event)
    {
        long startMS = System.currentTimeMillis();
        event.register(new ResourceLocation(Frostbound.MOD_ID, "blocks"), BlockTempModifier::new);
        event.register(new ResourceLocation(Frostbound.MOD_ID, "biomes"), BiomeTempModifier::new);
        event.register(new ResourceLocation(Frostbound.MOD_ID, "underground"), UndergroundTempModifier::new);
        event.register(new ResourceLocation(Frostbound.MOD_ID, "armor"), ArmorInsulationTempModifier::new);
        event.register(new ResourceLocation(Frostbound.MOD_ID, "mount"), MountTemp::new);
        event.register(new ResourceLocation(Frostbound.MOD_ID, "water"), WaterTempModifier::new);
        event.register(new ResourceLocation(Frostbound.MOD_ID, "freezing"), FreezingTempModifier::new);
        event.register(new ResourceLocation(Frostbound.MOD_ID, "on_fire"), FireTempModifier::new);


        Frostbound.LOGGER.debug("Registered TempModifiers in {}ms", System.currentTimeMillis() - startMS);
    }
}

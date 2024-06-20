package net.shadowbeast.arcanemysteries.events;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.fml.common.Mod;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;
import net.shadowbeast.arcanemysteries.enums.TempMode;
import net.shadowbeast.arcanemysteries.json.DataMaps;
import net.shadowbeast.arcanemysteries.json.EntityTemperatureJsonHolder;
import net.shadowbeast.arcanemysteries.registries.EffectsRegistry;
import net.shadowbeast.arcanemysteries.temprature.TemperatureData;
import net.shadowbeast.arcanemysteries.temprature.util.EStats;
import net.shadowbeast.arcanemysteries.temprature.util.TUtil;
import net.shadowbeast.arcanemysteries.temprature.util.TemperatureModifier;
import net.shadowbeast.arcanemysteries.temprature.util.TemperatureQuery;
import net.shadowbeast.arcanemysteries.util.MathHelper;


@Mod.EventBusSubscriber
public class ArcaneEvents
{

    public static void updateEnvTemperature(LivingEntity living) {
        if (living != null && living instanceof ServerPlayer player) {
            if (player.isAlive()) {
                for (ResourceLocation queryId : TemperatureQuery.queries.keySet()) {
                    double queryValue = TemperatureQuery.queries.get(queryId).getA().run(player, EStats.getTemperatureStats(player).getTemperatureLevel(), player.level(), player.blockPosition(), true);
                    TemperatureData.setTemperatureModifier(player, queryId, queryValue, TemperatureQuery.queries.get(queryId).getB());
                }
            }
        }
    }
    @SuppressWarnings("deprecation")
    public static double getExactTemperature(Level world, BlockPos pos, TempType type) {
        float skyLight = world.getChunkSource().getLightEngine().getLayerListener(LightLayer.SKY).getLightValue(pos);
        float gameTime = world.getDayTime() % 24000L;
        gameTime = gameTime/(200/3);
        gameTime = (float) Math.sin(Math.toRadians(gameTime));

        switch (type) {
            case SUN:
                float sunIntensity = 5.0f;
                if (world.getBiome(pos).unwrapKey().isPresent() && DataMaps.Server.biome.containsKey(world.getBiome(pos).unwrapKey().get().location())) {
                    sunIntensity = DataMaps.Server.biome.get(world.getBiome(pos).unwrapKey().get().location()).getSunIntensity();
                }
                if (skyLight > 5.0F) return gameTime*sunIntensity;
                else return -1.0F * sunIntensity;

            case BIOME:
                float biomeTemp = (TUtil.getTemperature(world.getBiome(pos), pos)*2)-2;

                return biomeTemp;
            case SHADE:
                return ((skyLight / 7.5F) - 1);

            default:
                return ArcaneMysteries.DEF_TEMP;
        }
    }

    private enum TempType {
        BIOME("biome", 7, false), BLOCK("block", 9, true), ENTITY("entity", 9, true), SHADE("shade", 200, true), SUN("sun", 200, true);

        String name;
        double reductionAmount;
        boolean usingExact;
        private TempType(String name, double reductionAmountIn, boolean usingExactIn) {
            this.reductionAmount = reductionAmountIn;
            this.usingExact = usingExactIn;
            this.name = name;
        }
        public String getName() {
            return name;
        }
        public double getReductionAmount() {
            return reductionAmount;
        }

        public boolean isUsingExact() {
            return usingExact;
        }
    }

    public static double getBlendedTemperature(Level world, BlockPos mainPos, BlockPos blendPos, TempType type) {
        float distance = (float) Math.sqrt(mainPos.distSqr(blendPos));// 2 - 10 - 0
        if (distance <= 5.0D) {
            float blendRatio0 = distance / 5.0F;   // 0.2 - 1.0 - 0.0
            float blendRatio1 = 1.0F - blendRatio0; // 0.8 - 0.0 - 1.0
            double temp0 = getExactTemperature(world, blendPos, type);
            double temp1 = getExactTemperature(world, mainPos, type);
            return ((temp0*blendRatio0)+(temp1*blendRatio1));
        } else {
            return getExactTemperature(world, mainPos, type);
        }
    }

    public static float getAverageTemperature(Level world, BlockPos pos, TempType type, int rangeInBlocks, TempMode mode) {
        float temp = 0;
        int tempAmount = 0;
        for (int x = -rangeInBlocks; x <= rangeInBlocks; x++) {
            for (int y = -rangeInBlocks; y <= rangeInBlocks; y++) {
                for (int z = -rangeInBlocks; z <= rangeInBlocks; z++) {
                    if (mode == TempMode.BLEND)temp+=getBlendedTemperature(world, new BlockPos(pos.getX()+x, pos.getY()+y, pos.getZ()+z), pos, type);
                    else if (mode == TempMode.NORMAL)temp+=getExactTemperature(world, new BlockPos(pos.getX()+x, pos.getY()+y, pos.getZ()+z), type);
                    tempAmount++;
                }
            }
        }
        return temp/((float)tempAmount);
    }
    @SuppressWarnings("deprecation")
    public static boolean isSnowingAt(Level world, BlockPos position) {
        if (!world.isRaining()) {
            return false;
        } else if (!world.canSeeSky(position)) {
            return false;
        } else if (world.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, position).getY() > position.getY()) {
            return false;
        } else {
            Biome biome = world.getBiome(position).value();
            return biome.getPrecipitationAt(position) == Biome.Precipitation.SNOW;
        }
    }

    public static void addReload(LevelAccessor lvl) {
        System.out.println("Start Resistering Temperature Queries");
        for (TempType type : TempType.values()) {
            TemperatureQuery.registerQuery("arcanemysteries:"+type.getName(), TemperatureModifier.ContributingFactor.ENVIRONMENTAL, (player, temp, level, pos, applyTemp)-> {
                double temperature;
                if (type.isUsingExact()) {
                    temperature = getExactTemperature(level, pos, type);
                } else {
                    temperature = getAverageTemperature(level, pos, type, 5, TempMode.NORMAL);
                }
                return MathHelper.roundDecimal(3, (temperature)/type.getReductionAmount());
            });
        }
        TemperatureQuery.registerQuery("arcanemysteries:snow", TemperatureModifier.ContributingFactor.ENVIRONMENTAL, (player, temp, level, pos, applyTemp)-> {
            double snow = 0.0D;
            if (isSnowingAt(level, pos)) {
                snow = -2.0D;
            }
            return snow;
        });
        TemperatureQuery.registerQuery("arcanemysteries:chilled_effect", TemperatureModifier.ContributingFactor.INTERNAL, (player, temp, level, pos, applyTemp)->{
            if (player.hasEffect(EffectsRegistry.CHILLED.get()))
                return -(0.05F * (float)(player.getEffect(EffectsRegistry.CHILLED.get()).getAmplifier() + 1));
            else
                return 0;
        });
        TemperatureQuery.registerQuery("arcanemysteries:heated_effect", TemperatureModifier.ContributingFactor.INTERNAL, (player, temp, level, pos, applyTemp)->{
            if (player.hasEffect(EffectsRegistry.HEATED.get()))
                return +(0.05F * (float)(player.getEffect(EffectsRegistry.HEATED.get()).getAmplifier() + 1));
            else
                return 0;
        });
        System.out.println("Done Resistering Temperature Queries");
    }
}

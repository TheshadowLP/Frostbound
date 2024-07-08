package net.shadowbeast.arcanemysteries.temprature.util;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.Tags;
import net.shadowbeast.arcanemysteries.api.temperature.TempModifier;
import net.shadowbeast.arcanemysteries.config.ConfigSettings;
import net.shadowbeast.arcanemysteries.temprature.Temperature;
import net.shadowbeast.arcanemysteries.util.MathHelper;
import net.shadowbeast.arcanemysteries.util.WorldHelper;
import oshi.util.tuples.Triplet;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
public class UndergroundTempModifier extends TempModifier
{
    @Override
    public Function<Double, Double> calculate(LivingEntity entity, Temperature.Trait trait)
    {
        if (entity.level().dimensionType().hasCeiling()) return temp -> temp;

        double midTemp = (ConfigSettings.MAX_TEMP.get() + ConfigSettings.MIN_TEMP.get()) / 2;
        BlockPos playerPos = BlockPos.containing(entity.getEyePosition());
        Level level = entity.level();

        List<Pair<Double, Double>> depthTable = new ArrayList<>();

        double biomeTempTotal = 0;
        int caveBiomeCount = 0;

        for (BlockPos pos : WorldHelper.getPositionCube(entity.blockPosition(), 5, 10))
        {
            if (!level.isInWorldBounds(pos)) continue;

            depthTable.add(Pair.of(Math.max(0d, WorldHelper.getHeight(pos, level) - playerPos.getY()), Math.sqrt(pos.distSqr(playerPos))));
            if (WorldHelper.getHeight(pos, level) <= entity.getY()) continue;

            // Get temperature of underground biomes
            Holder<Biome> holder = level.getBiomeManager().getBiome(pos);
            if (holder.is(Tags.Biomes.IS_UNDERGROUND))
            {
                if (holder.unwrapKey().isEmpty()) continue;
                Biome biome = holder.value();
                double baseTemp = biome.getBaseTemperature();

                Triplet<Double, Double, Temperature.Units> cTemp = ConfigSettings.BIOME_TEMPS.get().getOrDefault(biome, new Triplet<>(baseTemp, baseTemp, Temperature.Units.MC));
                Triplet<Double, Double, Temperature.Units> cOffset = ConfigSettings.BIOME_OFFSETS.get().getOrDefault(biome, new Triplet<>(0d, 0d, Temperature.Units.MC));

                double biomeTemp = MathHelper.averagePair(Pair.of(cTemp.getA(), cTemp.getB()))
                        + MathHelper.averagePair(Pair.of(cOffset.getA(), cOffset.getB()));

                biomeTempTotal += biomeTemp;
                caveBiomeCount++;
            }
        }
        if (depthTable.isEmpty()) return temp -> temp;

        double depth = MathHelper.blend(0, MathHelper.weightedAverage(depthTable), ConfigSettings.CAVE_INSULATION.get(), 0, 1);
        int biomeCount = Math.max(1, caveBiomeCount);
        double biomeTempAvg = biomeTempTotal / biomeCount;
        return temp ->
        {
            double depthAvg = MathHelper.weightedAverage(MathHelper.blend(midTemp, temp, entity.level().getBrightness(LightLayer.SKY, entity.blockPosition()), 0, 15),
                    MathHelper.blend(temp, midTemp, depth, 4, 20), 1, 2);
            return MathHelper.blend(depthAvg, biomeTempAvg, biomeCount, 0, depthTable.size());
        };
    }
}

package net.shadowbeast.arcanemysteries.temprature.util;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.synth.PerlinSimplexNoise;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;
import net.shadowbeast.arcanemysteries.json.DataMaps;
import net.shadowbeast.arcanemysteries.util.BiomeJsonHolder;

public class TUtil
{
    public static double firstHeat(Player player) {
        return ArcaneMysteries.DEF_TEMP +(1.0D);
    }

    public static double secondHeat(Player player) {
        return ArcaneMysteries.DEF_TEMP + (1.0D+(21.0D/63.0D));
    }

    public static double maxHeat(Player player) {
        return ArcaneMysteries.DEF_TEMP + ( 1.0D+(28.0D/63.0D));
    }

    public static double firstCold(Player player) {
        return ArcaneMysteries.DEF_TEMP - (1.0D);
    }

    public static double secondCold(Player player) {
        return ArcaneMysteries.DEF_TEMP - (1.0D+(21.0D/63.0D));
    }

    public static double maxCold(Player player) {
        return ArcaneMysteries.DEF_TEMP - (1.0D+(28.0D/63.0D));
    }



    private static final PerlinSimplexNoise TEMPERATURE_NOISE = new PerlinSimplexNoise(new WorldgenRandom(new LegacyRandomSource(1234L)), ImmutableList.of(0));

    public static float getTemperature(Holder<Biome> biome, BlockPos pos) {
        float f = (float)(TEMPERATURE_NOISE.getValue((double)((float)pos.getX() / 8.0F), (double)((float)pos.getZ() / 8.0F), false) * 4.0D);
        float modifier = 1.0f;
        float t = biome.value().getBaseTemperature();
        if (biome.unwrapKey().isPresent() && DataMaps.Server.biome.containsKey(biome.unwrapKey().get().location())) {
            BiomeJsonHolder temperatureData = DataMaps.Server.biome.get(biome.unwrapKey().get().location());
            t = (temperatureData.getTemperature() + 2) / 2;
            if (pos.getY() > 64.0F) {
                modifier = temperatureData.getAltitudeLevelModifier().getFirst();
            } else if (pos.getY() < 64.0F) {
                modifier = temperatureData.getAltitudeLevelModifier().getSecond();
            }
        }
        return t - modifier*((f + (float)pos.getY() - 64.0F) * 0.05F / 3.75F);
    }
}

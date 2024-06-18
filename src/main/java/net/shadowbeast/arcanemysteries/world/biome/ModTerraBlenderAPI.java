package net.shadowbeast.arcanemysteries.world.biome;

import net.minecraft.resources.ResourceLocation;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;
import net.shadowbeast.arcanemysteries.world.biome.custom.ModOverworldRegion;
import terrablender.api.Regions;

public class ModTerraBlenderAPI
{
    public static void registerRegions(){
        Regions.register(new ModOverworldRegion(new ResourceLocation(ArcaneMysteries.MOD_ID, "overworld"), 5));
    }
}

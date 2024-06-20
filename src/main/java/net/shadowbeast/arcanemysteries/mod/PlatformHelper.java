package net.shadowbeast.arcanemysteries.mod;

import net.minecraftforge.fml.loading.FMLEnvironment;

public class PlatformHelper
{
    public static boolean isClientInstance() {
        return FMLEnvironment.dist.isClient();
    }
}

package net.shadowbeast.arcanemysteries.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;

import java.util.Properties;

@Mod.EventBusSubscriber(modid = ArcaneMysteries.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    private static final Properties defaultValues = new Properties();
    // private static final ForgeConfigSpec.BooleanValue BAKED_POTATOES_DO_DAMAGE = BUILDER
    //        .comment("Weather Hot Potatoes do damage")
    //        .define("bakedPotatoesDoDamage", true);
    public static final ForgeConfigSpec SPEC = BUILDER.build();
    // public static boolean bakedPotatoesDoDamage;
    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        // bakedPotatoesDoDamage = BAKED_POTATOES_DO_DAMAGE.get();
    }
}
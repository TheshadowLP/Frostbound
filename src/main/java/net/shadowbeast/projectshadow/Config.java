package net.shadowbeast.projectshadow;


import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;


@Mod.EventBusSubscriber(modid = ProjectShadow.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.BooleanValue SNOWBALLS_DOES_DAMAGE = BUILDER
            .comment("Whether snowballs do damage")
            .define("snowballsDoesDamage", true);


    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static boolean snowballsDoesDamage;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        snowballsDoesDamage = SNOWBALLS_DOES_DAMAGE.get();
    }
}

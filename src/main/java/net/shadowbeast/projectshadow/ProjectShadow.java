package net.shadowbeast.projectshadow;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.shadowbeast.projectshadow.blocks.ModBlocks;
import net.shadowbeast.projectshadow.entity.ModEntities;
import net.shadowbeast.projectshadow.util.creativetab.CreativeTabs;
import net.shadowbeast.projectshadow.entity.ModBlockEntities;
import net.shadowbeast.projectshadow.items.ModItems;
import net.shadowbeast.projectshadow.util.recipes.ModRecipes;
import net.shadowbeast.projectshadow.util.screen.FusionFurnaceScreen;
import net.shadowbeast.projectshadow.util.screen.ModMenuTypes;
import net.shadowbeast.projectshadow.util.screen.WinterFurnaceScreen;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ProjectShadow.MOD_ID)
public class ProjectShadow {
    public static final String MOD_ID = "projectshadow";
    private static final Logger LOGGER = LogUtils.getLogger();

    public ProjectShadow() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        CreativeTabs.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModEntities.register(modEventBus);
        ModRecipes.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        modEventBus.addListener(this::clientSetup);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);

    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    private void clientSetup(final FMLClientSetupEvent event) {
        MenuScreens.register(ModMenuTypes.FUSION_FURNACE_MENU.get(), FusionFurnaceScreen::new);
        MenuScreens.register(ModMenuTypes.WINTER_FURNACE_MENU.get(), WinterFurnaceScreen::new);
        EntityRenderers.register(ModEntities.MUDBALL_PROJECTILE.get(), ThrownItemRenderer::new);
    }
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}

package net.shadowbeast.projectshadow;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.shadowbeast.projectshadow.blocks.ModBlocks;
import net.shadowbeast.projectshadow.blockEntities.screen.CrusherScreen;
import net.shadowbeast.projectshadow.client.ModBoatRenderer;
import net.shadowbeast.projectshadow.entity.ModEntities;
import net.shadowbeast.projectshadow.items.custom.ModItemProperties;
import net.shadowbeast.projectshadow.util.ModWoodTypes;
import net.shadowbeast.projectshadow.util.creativetab.CreativeTabs;
import net.shadowbeast.projectshadow.entity.ModBlockEntities;
import net.shadowbeast.projectshadow.items.ModItems;
import net.shadowbeast.projectshadow.blockEntities.recipes.ModRecipes;
import net.shadowbeast.projectshadow.blockEntities.screen.AlloyFurnaceScreen;
import net.shadowbeast.projectshadow.blockEntities.menu.ModMenuTypes;
import net.shadowbeast.projectshadow.blockEntities.screen.WinterFurnaceScreen;

@Mod(ProjectShadow.MOD_ID)
public class ProjectShadow {
    public static final String MOD_ID = "projectshadow";
    // public static final Logger LOGGER = LogUtils.getLogger();
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
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> new ConfigScreenHandler.ConfigScreenFactory((Minecraft mcInstance, Screen returnTo) -> new ConfigScreen(returnTo)));

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);

    }
    private void commonSetup(final FMLCommonSetupEvent event) {}
    private void addCreative(BuildCreativeModeTabContentsEvent event) {}
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {}
    private void clientSetup(final FMLClientSetupEvent event) {
        Sheets.addWoodType(ModWoodTypes.FROZEN);
        MenuScreens.register(ModMenuTypes.ALLOY_FURNACE_MENU.get(), AlloyFurnaceScreen::new);
        MenuScreens.register(ModMenuTypes.CRUSHER_MENU.get(), CrusherScreen::new);
        MenuScreens.register(ModMenuTypes.WINTER_FURNACE_MENU.get(), WinterFurnaceScreen::new);
        EntityRenderers.register(ModEntities.MUDBALL_PROJECTILE.get(), ThrownItemRenderer::new);
        EntityRenderers.register(ModEntities.MOD_BOAT.get(), pContext -> new ModBoatRenderer(pContext, false));
        EntityRenderers.register(ModEntities.MOD_CHEST_BOAT.get(), pContext -> new ModBoatRenderer(pContext, true));
        ModItemProperties.addCustomItemProperties();
    }
}

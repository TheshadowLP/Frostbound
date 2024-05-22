package net.shadowbeast.projectshadow;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.shadowbeast.projectshadow.block_entities.menu.ModMenuTypes;
import net.shadowbeast.projectshadow.block_entities.recipes.ModRecipes;
import net.shadowbeast.projectshadow.block_entities.screen.AlloyFurnaceScreen;
import net.shadowbeast.projectshadow.block_entities.screen.CrusherScreen;
import net.shadowbeast.projectshadow.block_entities.screen.WinterFurnaceScreen;
import net.shadowbeast.projectshadow.blocks.ModBlocks;
import net.shadowbeast.projectshadow.client.ModBoatRenderer;
import net.shadowbeast.projectshadow.config.Config;
import net.shadowbeast.projectshadow.creativetab.CreativeTabs;
import net.shadowbeast.projectshadow.effect.ModEffects;
import net.shadowbeast.projectshadow.enchantments.ModEnchantments;
import net.shadowbeast.projectshadow.entity.ModBlockEntities;
import net.shadowbeast.projectshadow.entity.ModEntities;
import net.shadowbeast.projectshadow.entity.mob.client.DungeonIceRenderer;
import net.shadowbeast.projectshadow.entity.mob.client.YakRenderer;
import net.shadowbeast.projectshadow.fluid.ModFluidTypes;
import net.shadowbeast.projectshadow.fluid.ModFluids;
import net.shadowbeast.projectshadow.items.ModItems;
import net.shadowbeast.projectshadow.items.custom.ModItemProperties;
import net.shadowbeast.projectshadow.particle.ModParticles;
import net.shadowbeast.projectshadow.sound.ModSounds;
import net.shadowbeast.projectshadow.util.ModWoodTypes;
import org.slf4j.Logger;

@Mod(ProjectShadow.MOD_ID)
public class ProjectShadow {
    public static final String MOD_ID = "projectshadow";
    public static final Logger LOGGER = LogUtils.getLogger();
    public ProjectShadow() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        CreativeTabs.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModEffects.register(modEventBus);
        ModParticles.register(modEventBus);
        ModEntities.register(modEventBus);
        ModFluids.register(modEventBus);
        ModSounds.register(modEventBus);
        ModFluidTypes.register(modEventBus);
        ModRecipes.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModEnchantments.register(modEventBus);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        //  ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> new ConfigScreenHandler.ConfigScreenFactory((Minecraft mcInstance, Screen returnTo) -> new ConfigScreen(returnTo)));

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }
    private void commonSetup(final FMLCommonSetupEvent event) {}
    private void addCreative(BuildCreativeModeTabContentsEvent event) {}
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            MenuScreens.register(ModMenuTypes.ALLOY_FURNACE_MENU.get(), AlloyFurnaceScreen::new);
            MenuScreens.register(ModMenuTypes.CRUSHER_MENU.get(), CrusherScreen::new);
            MenuScreens.register(ModMenuTypes.WINTER_FURNACE_MENU.get(), WinterFurnaceScreen::new);
            EntityRenderers.register(ModEntities.DUNGEON_ICE.get(), DungeonIceRenderer::new);
            EntityRenderers.register(ModEntities.YAK.get(), YakRenderer::new);

            event.enqueueWork(() -> {
            });
        }
        @SubscribeEvent
        public static void registerRenderers(FMLClientSetupEvent event) {
            Sheets.addWoodType(ModWoodTypes.FROZEN);
            EntityRenderers.register(ModEntities.MUDBALL_PROJECTILE.get(), ThrownItemRenderer::new);
            EntityRenderers.register(ModEntities.ICE_BEAM_PROJECTILE.get(), ThrownItemRenderer::new);
            EntityRenderers.register(ModEntities.MOD_BOAT.get(), pContext -> new ModBoatRenderer(pContext, false));
            EntityRenderers.register(ModEntities.MOD_CHEST_BOAT.get(), pContext -> new ModBoatRenderer(pContext, true));
            ModItemProperties.addCustomItemProperties();
        }

    }
}
package net.shadowbeast.arcanemysteries;

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
import net.shadowbeast.arcanemysteries.block_entities.menu.ModMenuTypes;
import net.shadowbeast.arcanemysteries.block_entities.recipes.ModRecipes;
import net.shadowbeast.arcanemysteries.block_entities.screen.AlloyFurnaceScreen;
import net.shadowbeast.arcanemysteries.block_entities.screen.CrusherScreen;
import net.shadowbeast.arcanemysteries.block_entities.screen.WinterFurnaceScreen;
import net.shadowbeast.arcanemysteries.blocks.ModBlocks;
import net.shadowbeast.arcanemysteries.client.ModBoatRenderer;
import net.shadowbeast.arcanemysteries.config.Config;
import net.shadowbeast.arcanemysteries.creativetab.CreativeTabs;
import net.shadowbeast.arcanemysteries.effect.ModEffects;
import net.shadowbeast.arcanemysteries.enchantments.ModEnchantments;
import net.shadowbeast.arcanemysteries.entity.ModBlockEntities;
import net.shadowbeast.arcanemysteries.entity.ModEntities;
import net.shadowbeast.arcanemysteries.entity.mob.client.DungeonIceRenderer;
import net.shadowbeast.arcanemysteries.entity.mob.client.YakRenderer;
import net.shadowbeast.arcanemysteries.fluid.ModFluidTypes;
import net.shadowbeast.arcanemysteries.fluid.ModFluids;
import net.shadowbeast.arcanemysteries.items.ModItems;
import net.shadowbeast.arcanemysteries.items.custom.ModItemProperties;
import net.shadowbeast.arcanemysteries.networking.ModMessages;
import net.shadowbeast.arcanemysteries.particle.ModParticles;
import net.shadowbeast.arcanemysteries.sound.ModSounds;
import net.shadowbeast.arcanemysteries.util.ModWoodTypes;
import org.slf4j.Logger;

@Mod(ArcaneMysteries.MOD_ID)
public class ArcaneMysteries {
    public static final String MOD_ID = "arcanemysteries";
    public static final Logger LOGGER = LogUtils.getLogger();
    public ArcaneMysteries() {
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
    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(ModMessages::register);
    }
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
package net.shadowbeast.arcanemysteries;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.shadowbeast.arcanemysteries.block_entities.menu.MenuTypesMod;
import net.shadowbeast.arcanemysteries.block_entities.recipes.RecipesMod;
import net.shadowbeast.arcanemysteries.block_entities.screen.AlloyFurnaceScreen;
import net.shadowbeast.arcanemysteries.block_entities.screen.CrusherScreen;
import net.shadowbeast.arcanemysteries.block_entities.screen.WinterFurnaceScreen;
import net.shadowbeast.arcanemysteries.client.BoatModRenderer;
import net.shadowbeast.arcanemysteries.enchant.EnchantmentsRegistry;
import net.shadowbeast.arcanemysteries.entities.mobs.client.DungeonIceRenderer;
import net.shadowbeast.arcanemysteries.entities.mobs.client.YakRenderer;
import net.shadowbeast.arcanemysteries.items.ItemModProperties;
import net.shadowbeast.arcanemysteries.registries.*;
import net.shadowbeast.arcanemysteries.config.Config;
import net.shadowbeast.arcanemysteries.fluid.FluidTypesMod;
import net.shadowbeast.arcanemysteries.fluid.FluidsMod;
import net.shadowbeast.arcanemysteries.networking.MessagesMod;
import net.shadowbeast.arcanemysteries.util.WoodTypesMod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ArcaneMysteries.MOD_ID)
public class ArcaneMysteries {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "arcanemysteries";
    public ArcaneMysteries() {
        var bus = FMLJavaModLoadingContext.get().getModEventBus();

        //BlockRegistry.BLOCKS.register(bus);
        //BlockRegistry.BLOCK_ITEMS.register(bus);
        ItemRegistry.ITEMS.register(bus);
        CreativeTabRegistry.TAB.register(bus);
        SoundRegistry.SOUNDS.register(bus);
        ParticleRegistry.PARTICLES.register(bus);
        EffectsRegistry.EFFECTS.register(bus);
        RecipesMod.SERIALIZERS.register(bus);
        EnchantmentsRegistry.ENCHANTMENTS.register(bus);
        FluidsMod.FLUIDS.register(bus);
        FluidTypesMod.FLUID_TYPES.register(bus);
        MenuTypesMod.MENUS.register(bus);
        EntityRegistry.ENTITIES.register(bus);
        EntityRegistry.BLOCK_ENTITIES.register(bus);

        //delete
        ModBlocks.BLOCKS.register(bus);
        ModBlocks.BLOCK_ITEMS.register(bus);
        //ModItems.register(modEventBus);
        //CreativeTabs.register(modEventBus);
        //EffectsRegistry.register(modEventBus);
        //ParticlesRegistry.register(modEventBus);
        //EntityRegistry.register(modEventBus);
        //FluidsMod.register(modEventBus);
        //ModSounds.register(modEventBus);
        //FluidTypesMod.register(modEventBus);
        //ModRecipes.register(modEventBus);
        //ModMenuTypes.register(modEventBus);
        //EnchantmentsRegistry.register(modEventBus);
        //  ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> new ConfigScreenHandler.ConfigScreenFactory((Minecraft mcInstance, Screen returnTo) -> new ConfigScreen(returnTo)));

        bus.addListener(this::commonSetup);
        bus.addListener(this::addCreative);
        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(MessagesMod::register);
    }
    private void addCreative(BuildCreativeModeTabContentsEvent event) {}
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            MenuScreens.register(MenuTypesMod.ALLOY_FURNACE_MENU.get(), AlloyFurnaceScreen::new);
            MenuScreens.register(MenuTypesMod.CRUSHER_MENU.get(), CrusherScreen::new);
            MenuScreens.register(MenuTypesMod.WINTER_FURNACE_MENU.get(), WinterFurnaceScreen::new);
            EntityRenderers.register(EntityRegistry.DUNGEON_ICE.get(), DungeonIceRenderer::new);
            EntityRenderers.register(EntityRegistry.YAK.get(), YakRenderer::new);
            MinecraftForge.EVENT_BUS.register(EnchantmentsRegistry.MAGNETISM.get());


            event.enqueueWork(() -> {
            });
        }
        @SubscribeEvent
        public static void registerRenderers(FMLClientSetupEvent event) {
            Sheets.addWoodType(WoodTypesMod.FROZEN);
            EntityRenderers.register(EntityRegistry.MUDBALL_PROJECTILE.get(), ThrownItemRenderer::new);
            EntityRenderers.register(EntityRegistry.ICE_BEAM_PROJECTILE.get(), ThrownItemRenderer::new);
            EntityRenderers.register(EntityRegistry.MOD_BOAT.get(), pContext -> new BoatModRenderer(pContext, false));
            EntityRenderers.register(EntityRegistry.MOD_CHEST_BOAT.get(), pContext -> new BoatModRenderer(pContext, true));
            EntityRenderers.register(EntityRegistry.YAK.get(), YakRenderer::new);
            ItemModProperties.addCustomItemProperties();
        }

    }
}
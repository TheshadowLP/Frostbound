package net.shadowbeast.frostbound;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DataPackRegistryEvent;
import net.shadowbeast.frostbound.block_entities.menu.ModMenuTypes;
import net.shadowbeast.frostbound.block_entities.recipes.ModRecipes;
import net.shadowbeast.frostbound.block_entities.screen.AlloyFurnaceScreen;
import net.shadowbeast.frostbound.block_entities.screen.CrusherScreen;
import net.shadowbeast.frostbound.block_entities.screen.WinterFurnaceScreen;
import net.shadowbeast.frostbound.client.BoatModRenderer;
import net.shadowbeast.frostbound.config.ConfigSettings;
import net.shadowbeast.frostbound.core.*;
import net.shadowbeast.frostbound.core.data.*;
import net.shadowbeast.frostbound.enchant.EnchantmentsRegistry;
import net.shadowbeast.frostbound.entities.mobs.client.DungeonIceRenderer;
import net.shadowbeast.frostbound.entities.mobs.client.YakRenderer;
import net.shadowbeast.frostbound.items.ItemModProperties;
import net.shadowbeast.frostbound.networking.ModMessages;
import net.shadowbeast.frostbound.registries.*;
import net.shadowbeast.frostbound.temprature.caps.EntityTempCap;
import net.shadowbeast.frostbound.temprature.caps.TemperatureCap;
import net.shadowbeast.frostbound.util.WoodTypesMod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

@Mod(Frostbound.MOD_ID)
public class Frostbound {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "frostbound";
    public static float DEF_TEMP = 37.0F;
    private static Frostbound instance;
    public Frostbound() {
        var bus = FMLJavaModLoadingContext.get().getModEventBus();
        instance = this;
        ItemRegistry.ITEMS.register(bus);
        CreativeTabRegistry.TAB.register(bus);
        SoundRegistry.SOUNDS.register(bus);
        ParticleRegistry.PARTICLES.register(bus);
        EffectsRegistry.EFFECTS.register(bus);
        ModRecipes.SERIALIZERS.register(bus);
        EnchantmentsRegistry.ENCHANTMENTS.register(bus);
        ModMenuTypes.MENUS.register(bus);
        EntityRegistry.ENTITIES.register(bus);
        EntityRegistry.BLOCK_ENTITIES.register(bus);
        AttributeInit.ATTRIBUTES.register(bus);
        BiomeCodecInit.BIOME_MODIFIER_SERIALIZERS.register(bus);

        ModBlocks.BLOCKS.register(bus);
        ModBlocks.BLOCK_ITEMS.register(bus);
        WorldSettingConfig.setup();
        ItemSettingConfig.setup();
        MainSettingConfig.setup();
        ClientSettingConfig.setup();
        EntitySettingConfig.setup();

        bus.addListener(this::commonSetup);
        bus.addListener(this::addCreative);
        bus.addListener(this::registerCaps);
        bus.addListener((DataPackRegistryEvent.NewRegistry event) ->
        {
            event.dataPackRegistry(ArcaneRegistries.BLOCK_TEMP_DATA, BlockTempData.CODEC);
            event.dataPackRegistry(ArcaneRegistries.BIOME_TEMP_DATA, BiomeTempData.CODEC);
            event.dataPackRegistry(ArcaneRegistries.DIMENSION_TEMP_DATA, DimensionTempData.CODEC);
            event.dataPackRegistry(ArcaneRegistries.STRUCTURE_TEMP_DATA, StructureTempData.CODEC);
            event.dataPackRegistry(ArcaneRegistries.ENTITY_SPAWN_BIOME_DATA, SpawnBiomeData.CODEC);
        });
        MinecraftForge.EVENT_BUS.register(this);
    }
    private void commonSetup(final @NotNull FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            CriteriaTriggerRegistry.registerCriteriaTriggers();
        });
        ModMessages.registerPackets();
        ConfigSettings.load(null);
    }
    private void clientSetup(final FMLClientSetupEvent event) {

    }
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }
    private void addCreative(BuildCreativeModeTabContentsEvent event) {}
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            MenuScreens.register(ModMenuTypes.ALLOY_FURNACE_MENU.get(), AlloyFurnaceScreen::new);
            MenuScreens.register(ModMenuTypes.CRUSHER_MENU.get(), CrusherScreen::new);
            MenuScreens.register(ModMenuTypes.WINTER_FURNACE_MENU.get(), WinterFurnaceScreen::new);
            EntityRenderers.register(EntityRegistry.DUNGEON_ICE.get(), DungeonIceRenderer::new);
            EntityRenderers.register(EntityRegistry.YAK.get(), YakRenderer::new);
            MinecraftForge.EVENT_BUS.register(EnchantmentsRegistry.CHOPPER.get());
            MinecraftForge.EVENT_BUS.register(EnchantmentsRegistry.MAGNETISM.get());

            event.enqueueWork(() -> {

            });
        }
        @SubscribeEvent
        public static void registerRenderers(FMLClientSetupEvent event) {
            Sheets.addWoodType(WoodTypesMod.FROZEN);
            EntityRenderers.register(EntityRegistry.MUDBALL_PROJECTILE.get(), ThrownItemRenderer::new);
            EntityRenderers.register(EntityRegistry.MOD_BOAT.get(), pContext -> new BoatModRenderer(pContext, false));
            EntityRenderers.register(EntityRegistry.MOD_CHEST_BOAT.get(), pContext -> new BoatModRenderer(pContext, true));
            EntityRenderers.register(EntityRegistry.YAK.get(), YakRenderer::new);
            ItemModProperties.addCustomItemProperties();
        }

    }
    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
    }

    public void registerCaps(RegisterCapabilitiesEvent event)
    {   event.register(TemperatureCap.class);
        event.register(EntityTempCap.class);
    }
    public static Frostbound getInstance(){
        return instance;
    }
    public ResourceLocation location(String name) {
        return new ResourceLocation(MOD_ID, name);
    }

}
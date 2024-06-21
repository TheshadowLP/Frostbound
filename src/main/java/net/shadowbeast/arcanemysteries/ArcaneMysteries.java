package net.shadowbeast.arcanemysteries;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
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
import net.shadowbeast.arcanemysteries.client.command.TemperatureCommand;
import net.shadowbeast.arcanemysteries.config.Config;
import net.shadowbeast.arcanemysteries.enchant.EnchantmentsRegistry;
import net.shadowbeast.arcanemysteries.entities.mobs.client.DungeonIceRenderer;
import net.shadowbeast.arcanemysteries.entities.mobs.client.YakRenderer;
import net.shadowbeast.arcanemysteries.events.ArcaneEvents;
import net.shadowbeast.arcanemysteries.fluid.FluidTypesMod;
import net.shadowbeast.arcanemysteries.fluid.FluidsMod;
import net.shadowbeast.arcanemysteries.interfaces.ReloadListener;
import net.shadowbeast.arcanemysteries.interfaces.ReloadListeners;
import net.shadowbeast.arcanemysteries.items.ItemModProperties;
import net.shadowbeast.arcanemysteries.json.BiomeDataManager;
import net.shadowbeast.arcanemysteries.json.DataMaps;
import net.shadowbeast.arcanemysteries.json.EntityTemperatureDataManager;
import net.shadowbeast.arcanemysteries.json.EntityTemperatureJsonHolder;
import net.shadowbeast.arcanemysteries.mod.MinecraftMod;
import net.shadowbeast.arcanemysteries.mod.PlatformHelper;
import net.shadowbeast.arcanemysteries.networking.MessagesMod;
import net.shadowbeast.arcanemysteries.registries.*;
import net.shadowbeast.arcanemysteries.util.BiomeJsonHolder;
import net.shadowbeast.arcanemysteries.util.InsertCollector;
import net.shadowbeast.arcanemysteries.util.WoodTypesMod;
import net.shadowbeast.arcanemysteries.util.insert.InsertSystem;
import net.shadowbeast.arcanemysteries.util.insert.Inserts;
import net.shadowbeast.arcanemysteries.world.biome.ModTerraBlenderAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

@Mod(ArcaneMysteries.MOD_ID)
public class ArcaneMysteries extends MinecraftMod {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "arcanemysteries";
    public static float DEF_TEMP = 37.0F;
    private static ArcaneMysteries instance;
    public static EntityTemperatureDataManager entityManager = new EntityTemperatureDataManager();
    public static BiomeDataManager biomeManager = new BiomeDataManager();
    private final ReloadListeners reloadListeners = ReloadListener::id;
    public ArcaneMysteries() {
        var bus = FMLJavaModLoadingContext.get().getModEventBus();
        instance = this;
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
        ModTerraBlenderAPI.registerRegions();


        ModBlocks.BLOCKS.register(bus);
        ModBlocks.BLOCK_ITEMS.register(bus);

        bus.addListener(this::commonSetup);
        bus.addListener(this::addCreative);
        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
    private void commonSetup(final @NotNull FMLCommonSetupEvent event)
    {
        MessagesMod.registerPackets();
        ArcaneMysteriesClient.register();
        event.enqueueWork(() -> {
            registerInserts(InsertSystem.instance);

            reloadListeners.listenTo(entityManager);
            reloadListeners.listenTo(biomeManager);
        });
    }
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

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
            PlatformHelper.handleEvents();

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
    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        TemperatureCommand.register(event.getDispatcher());
    }

    @Override
    public void registerInserts(InsertCollector collector) {
        collector.addInsert(Inserts.LEVEL_LOAD,ArcaneEvents::addReload);
        collector.addInsert(Inserts.LIVING_TICK,ArcaneEvents::sendToClient);
        collector.addInsert(Inserts.LOGGED_OUT, ArcaneEvents::desyncClient);
        collector.addInsert(Inserts.LIVING_TICK, ArcaneEvents::updateEnvTemperature);
    }

    public static void registerEntityTemperatures(ResourceLocation location, EntityTemperatureJsonHolder drinkData) {
        DataMaps.Server.entityTemperature.put(location, drinkData);
    }
    public static void registerBiomeTemperatures(ResourceLocation location, BiomeJsonHolder biomeData) {
        DataMaps.Server.biome.put(location, biomeData);
    }

    public static ArcaneMysteries getInstance(){
        return instance;
    }
    public ResourceLocation location(String name) {
        return new ResourceLocation(MOD_ID, name);
    }
}
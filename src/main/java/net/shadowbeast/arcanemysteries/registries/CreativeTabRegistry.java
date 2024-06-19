package net.shadowbeast.arcanemysteries.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static net.shadowbeast.arcanemysteries.ArcaneMysteries.MOD_ID;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = MOD_ID)
public class CreativeTabRegistry {
    public static final DeferredRegister<CreativeModeTab> TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);
    public static final RegistryObject<CreativeModeTab> BLOCKS = TAB.register("blocks",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.END_OBSIDIAN.get()))
                    .title(Component.translatable("creativetab.arcanemysteries.blocks"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModBlocks.FROZEN_LOG.get());
                        pOutput.accept(ModBlocks.FROZEN_WOOD.get());
                        pOutput.accept(ModBlocks.STRIPPED_FROZEN_LOG.get());
                        pOutput.accept(ModBlocks.STRIPPED_FROZEN_WOOD.get());
                        pOutput.accept(ModBlocks.FROZEN_PLANKS.get());
                        pOutput.accept(ModBlocks.FROZEN_STAIRS.get());
                        pOutput.accept(ModBlocks.FROZEN_SLAB.get());
                        pOutput.accept(ModBlocks.FROZEN_FENCE.get());
                        pOutput.accept(ModBlocks.FROZEN_FENCE_GATE.get());
                        pOutput.accept(ModBlocks.FROZEN_DOOR.get());
                        pOutput.accept(ModBlocks.FROZEN_TRAPDOOR.get());
                        pOutput.accept(ModBlocks.FROZEN_PRESSURE_PLATE.get());
                        pOutput.accept(ModBlocks.FROZEN_BUTTON.get());
                        pOutput.accept(ItemRegistry.FROZEN_SIGN.get());
                        pOutput.accept(ItemRegistry.FROZEN_HANGING_SIGN.get());
                        pOutput.accept(ModBlocks.FROZEN_SAPLING.get());
                        pOutput.accept(ModBlocks.FROZEN_LEAVES.get());

                        pOutput.accept(ModBlocks.FROZEN_STONE.get());
                        pOutput.accept(ModBlocks.FROZEN_COBBLESTONE.get());
                        pOutput.accept(ModBlocks.FROZEN_STONE_BRICKS.get());
                        pOutput.accept(ModBlocks.FROZEN_COBBLESTONE_WALL.get());
                        pOutput.accept(ModBlocks.FROZEN_STONE_BRICK_WALL.get());

                        pOutput.accept(ModBlocks.COPPER_BARS.get());
                        pOutput.accept(ModBlocks.PLATINUM_BARS.get());
                        pOutput.accept(ModBlocks.STEEL_BARS.get());
                        pOutput.accept(ModBlocks.TITANIUM_BARS.get());

                        pOutput.accept(ModBlocks.WINTER_FURNACE.get());
                        pOutput.accept(ModBlocks.ALLOY_FURNACE.get());
                        pOutput.accept(ModBlocks.CRUSHER.get());
                        pOutput.accept(ModBlocks.SULFURIC_TNT.get());

                        pOutput.accept(ModBlocks.RUBY_BlOCK.get());
                        pOutput.accept(ModBlocks.SULFUR_BlOCK.get());
                        pOutput.accept(ModBlocks.SILVER_BlOCK.get());
                        pOutput.accept(ModBlocks.PLATINUM_BlOCK.get());
                        pOutput.accept(ModBlocks.STEEL_BlOCK.get());
                        pOutput.accept(ModBlocks.TITANIUM_BlOCK.get());
                        pOutput.accept(ModBlocks.LUMINITE_BlOCK.get());
                        pOutput.accept(ModBlocks.AQUANIUM_BlOCK.get());
                        pOutput.accept(ModBlocks.FROZEN_GEM_BlOCK.get());
                        pOutput.accept(ModBlocks.FIRERITE_BlOCK.get());
                        pOutput.accept(ModBlocks.ENDERIUM_BlOCK.get());
                        pOutput.accept(ModBlocks.ENDER_PEARL_BLOCK.get());

                        pOutput.accept(ModBlocks.BONE_ORE.get());
                        pOutput.accept(ModBlocks.SULFUR_ORE.get());
                        pOutput.accept(ModBlocks.SILVER_ORE.get());
                        pOutput.accept(ModBlocks.PLATINUM_ORE.get());
                        pOutput.accept(ModBlocks.TITANIUM_ORE.get());
                        pOutput.accept(ModBlocks.LUMINITE_ORE.get());
                        pOutput.accept(ModBlocks.AQUANIUM_ORE.get());
                        pOutput.accept(ModBlocks.FROZEN_GEM_ORE.get());
                        pOutput.accept(ModBlocks.NETHER_FIRERITE_ORE.get());
                        pOutput.accept(ModBlocks.ENDERIUM_END_ORE.get());
                        //pOutput.accept(ModBlocks.END_GOLD_ORE.get()); //TODO
                        pOutput.accept(ModBlocks.RUBY_ORE.get());
                        pOutput.accept(ModBlocks.DEEPSLATE_LUMINITE_ORE.get());
                        pOutput.accept(ModBlocks.SAND_AQUANIUM_ORE.get());
                        pOutput.accept(ModBlocks.BAUXITE.get());
                        pOutput.accept(ModBlocks.BEDROCK.get());

                        pOutput.accept(ModBlocks.END_LANTERN.get());
                        //pOutput.accept(ModBlocks.END_OBSIDIAN.get()); //TODO
                        //pOutput.accept(ModBlocks.DRAGON_SCALE_BLOCK.get()); //TODO
                        pOutput.accept(ModBlocks.END_STONE_PILLAR.get());
                        pOutput.accept(ModBlocks.TILED_END_STONE_BRICKS.get());
                        pOutput.accept(ModBlocks.POLISHED_END_STONE.get());
                        pOutput.accept(ModBlocks.RUNIC_END_STONE_BRICKS.get());
                        pOutput.accept(ModBlocks.CHISELED_PURPUR.get());
                        pOutput.accept(ModBlocks.POLISHED_PURPUR.get());
                        pOutput.accept(ModBlocks.BRICKED_PURPUR.get());
                        pOutput.accept(ModBlocks.BRICKED_PURPUR_PILLAR.get());
                        pOutput.accept(ModBlocks.TILED_PURPUR.get());
                        pOutput.accept(ModBlocks.TILED_PURPUR_PILLAR.get());
                        pOutput.accept(ModBlocks.RUNIC_PURPUR.get());
                        pOutput.accept(ModBlocks.END_GLASS.get());
                        pOutput.accept(ModBlocks.END_GLASS_PANE.get());
                    })
                    .build());
    public static final RegistryObject<CreativeModeTab> COMBAT = TAB.register("combat",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ItemRegistry.ENDERIUM_SWORD.get()))
                    .title(Component.translatable("creativetab.arcanemysteries.combat"))
                    .displayItems((pParameters, pOutput) ->{
                        pOutput.accept(ItemRegistry.SILVER_SWORD.get());
                        pOutput.accept(ItemRegistry.COPPER_SWORD.get());
                        pOutput.accept(ItemRegistry.PLATINUM_SWORD.get());
                        pOutput.accept(ItemRegistry.STEEL_SWORD.get());
                        pOutput.accept(ItemRegistry.TITANIUM_SWORD.get());
                        pOutput.accept(ItemRegistry.LUMINITE_SWORD.get());
                        pOutput.accept(ItemRegistry.AQUANIUM_SWORD.get());
                        pOutput.accept(ItemRegistry.FROZEN_SWORD.get());
                        pOutput.accept(ItemRegistry.FIRERITE_SWORD.get());
                        pOutput.accept(ItemRegistry.ENDERIUM_SWORD.get());
                        pOutput.accept(ItemRegistry.BEDROCK_SWORD.get());

                        pOutput.accept(ItemRegistry.SILVER_AXE.get());
                        pOutput.accept(ItemRegistry.COPPER_AXE.get());
                        pOutput.accept(ItemRegistry.PLATINUM_AXE.get());
                        pOutput.accept(ItemRegistry.STEEL_AXE.get());
                        pOutput.accept(ItemRegistry.TITANIUM_AXE.get());
                        pOutput.accept(ItemRegistry.LUMINITE_AXE.get());
                        pOutput.accept(ItemRegistry.AQUANIUM_AXE.get());
                        pOutput.accept(ItemRegistry.FROZEN_AXE.get());
                        pOutput.accept(ItemRegistry.FIRERITE_AXE.get());
                        pOutput.accept(ItemRegistry.ENDERIUM_AXE.get());
                        pOutput.accept(ItemRegistry.BEDROCK_AXE.get());

                        pOutput.accept(ItemRegistry.SILVER_HAMMER.get());
                        pOutput.accept(ItemRegistry.PLATINUM_HAMMER.get());
                        pOutput.accept(ItemRegistry.STEEL_HAMMER.get());
                        pOutput.accept(ItemRegistry.TITANIUM_HAMMER.get());

                        pOutput.accept(ItemRegistry.HEAL_STAFF.get());
                        pOutput.accept(ItemRegistry.LEVITATION_STAFF.get());
                        pOutput.accept(ItemRegistry.TELEPORTATION_STAFF.get());

                        pOutput.accept(ItemRegistry.SILVER_SHIELD.get());
                        pOutput.accept(ItemRegistry.GOLD_SHIELD.get());
                        pOutput.accept(ItemRegistry.COPPER_SHIELD.get());
                        pOutput.accept(ItemRegistry.PLATINUM_SHIELD.get());
                        pOutput.accept(ItemRegistry.STEEL_SHIELD.get());
                        pOutput.accept(ItemRegistry.TITANIUM_SHIELD.get());
                        pOutput.accept(ItemRegistry.LUMINITE_SHIELD.get());
                        pOutput.accept(ItemRegistry.AQUANIUM_SHIELD.get());
                        pOutput.accept(ItemRegistry.FROZEN_SHIELD.get());
                        pOutput.accept(ItemRegistry.FIRERITE_SHIELD.get());
                        pOutput.accept(ItemRegistry.ENDERIUM_SHIELD.get());

                        pOutput.accept(ItemRegistry.SILVER_HELMET.get());
                        pOutput.accept(ItemRegistry.SILVER_CHESTPLATE.get());
                        pOutput.accept(ItemRegistry.SILVER_LEGGINGS.get());
                        pOutput.accept(ItemRegistry.SILVER_BOOTS.get());

                        pOutput.accept(ItemRegistry.COPPER_HELMET.get());
                        pOutput.accept(ItemRegistry.COPPER_CHESTPLATE.get());
                        pOutput.accept(ItemRegistry.COPPER_LEGGINGS.get());
                        pOutput.accept(ItemRegistry.COPPER_BOOTS.get());

                        pOutput.accept(ItemRegistry.PLATINUM_HELMET.get());
                        pOutput.accept(ItemRegistry.PLATINUM_CHESTPLATE.get());
                        pOutput.accept(ItemRegistry.PLATINUM_LEGGINGS.get());
                        pOutput.accept(ItemRegistry.PLATINUM_BOOTS.get());

                        pOutput.accept(ItemRegistry.STEEL_HELMET.get());
                        pOutput.accept(ItemRegistry.STEEL_CHESTPLATE.get());
                        pOutput.accept(ItemRegistry.STEEL_LEGGINGS.get());
                        pOutput.accept(ItemRegistry.STEEL_BOOTS.get());

                        pOutput.accept(ItemRegistry.TITANIUM_HELMET.get());
                        pOutput.accept(ItemRegistry.TITANIUM_CHESTPLATE.get());
                        pOutput.accept(ItemRegistry.TITANIUM_LEGGINGS.get());
                        pOutput.accept(ItemRegistry.TITANIUM_BOOTS.get());

                        pOutput.accept(ItemRegistry.LUMINITE_HELMET.get());
                        pOutput.accept(ItemRegistry.LUMINITE_CHESTPLATE.get());
                        pOutput.accept(ItemRegistry.LUMINITE_LEGGINGS.get());
                        pOutput.accept(ItemRegistry.LUMINITE_BOOTS.get());

                        pOutput.accept(ItemRegistry.AQUANIUM_HELMET.get());
                        pOutput.accept(ItemRegistry.AQUANIUM_CHESTPLATE.get());
                        pOutput.accept(ItemRegistry.AQUANIUM_LEGGINGS.get());
                        pOutput.accept(ItemRegistry.AQUANIUM_BOOTS.get());

                        pOutput.accept(ItemRegistry.FROZEN_HELMET.get());
                        pOutput.accept(ItemRegistry.FROZEN_CHESTPLATE.get());
                        pOutput.accept(ItemRegistry.FROZEN_LEGGINGS.get());
                        pOutput.accept(ItemRegistry.FROZEN_BOOTS.get());

                        pOutput.accept(ItemRegistry.FIRERITE_HELMET.get());
                        pOutput.accept(ItemRegistry.FIRERITE_CHESTPLATE.get());
                        pOutput.accept(ItemRegistry.FIRERITE_LEGGINGS.get());
                        pOutput.accept(ItemRegistry.FIRERITE_BOOTS.get());

                        pOutput.accept(ItemRegistry.ENDERIUM_HELMET.get());
                        pOutput.accept(ItemRegistry.ENDERIUM_CHESTPLATE.get());
                        pOutput.accept(ItemRegistry.ENDERIUM_LEGGINGS.get());
                        pOutput.accept(ItemRegistry.ENDERIUM_BOOTS.get());
                        //pOutput.accept(ItemRegistry.ENDER_ELYTRA.get()); //TODO

                        pOutput.accept(ItemRegistry.MUD_BALL.get());
                        //pOutput.accept(ItemRegistry.DUNGEON_ICE_SPAWN_EGG.get()); //TODO
                        //pOutput.accept(ItemRegistry.ICE_BEAM.get()); //TODO
                    }).build());
    public static final RegistryObject<CreativeModeTab> INGREDIENTS = TAB.register("ingredients",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ItemRegistry.ENDER_INGOT.get()))
                    .title(Component.translatable("creativetab.arcanemysteries.ingredients"))
                    .displayItems((pParameters, pOutput) ->{
                        pOutput.accept(ItemRegistry.RAW_SILVER.get());
                        pOutput.accept(ItemRegistry.RAW_PLATINUM.get());
                        pOutput.accept(ItemRegistry.RAW_TITANIUM.get());
                        pOutput.accept(ItemRegistry.RAW_LUMINITE.get());
                        pOutput.accept(ItemRegistry.RAW_ENDERIUM.get());
                        pOutput.accept(ItemRegistry.SULFUR.get());
                        pOutput.accept(ItemRegistry.BEDROCK_CHUNK.get());

                        pOutput.accept(ItemRegistry.FROZEN_GEM.get());
                        pOutput.accept(ItemRegistry.FIRERITE_GEM.get());
                        pOutput.accept(ItemRegistry.RUBY.get());
                        pOutput.accept(ItemRegistry.SHARD.get());
                        pOutput.accept(ItemRegistry.DIAMOND_SHARD.get());

                        pOutput.accept(ItemRegistry.SILVER_NUGGET.get());
                        pOutput.accept(ItemRegistry.COPPER_NUGGET.get());
                        pOutput.accept(ItemRegistry.PLATINUM_NUGGET.get());
                        pOutput.accept(ItemRegistry.STEEL_NUGGET.get());
                        pOutput.accept(ItemRegistry.TITANIUM_NUGGET.get());
                        pOutput.accept(ItemRegistry.LUMINITE_NUGGET.get());
                        pOutput.accept(ItemRegistry.AQUANIUM_NUGGET.get());
                        pOutput.accept(ItemRegistry.ENDERIUM_NUGGET.get());

                        pOutput.accept(ItemRegistry.ALUMINIUM_INGOT.get());
                        pOutput.accept(ItemRegistry.SILVER_INGOT.get());
                        pOutput.accept(ItemRegistry.PLATINUM_INGOT.get());
                        pOutput.accept(ItemRegistry.STEEL_INGOT.get());
                        pOutput.accept(ItemRegistry.TITANIUM_INGOT.get());
                        pOutput.accept(ItemRegistry.LUMINITE_INGOT.get());
                        pOutput.accept(ItemRegistry.AQUANIUM_INGOT.get());
                        pOutput.accept(ItemRegistry.ENDERIUM_INGOT.get());
                        //pOutput.accept(ItemRegistry.END_GOLD_INGOT.get()); //TODO
                        pOutput.accept(ItemRegistry.ENDER_INGOT.get());

                        pOutput.accept(ItemRegistry.SILVER_DUST.get());
                        pOutput.accept(ItemRegistry.GOLD_DUST.get());
                        pOutput.accept(ItemRegistry.COPPER_DUST.get());
                        pOutput.accept(ItemRegistry.IRON_DUST.get());
                        pOutput.accept(ItemRegistry.PLATINUM_DUST.get());
                        pOutput.accept(ItemRegistry.TITANIUM_DUST.get());
                        pOutput.accept(ItemRegistry.DIAMOND_DUST.get());
                        pOutput.accept(ItemRegistry.SULFUR_DUST.get());
                        pOutput.accept(ItemRegistry.ENDER_PEARL_DUST.get());

                        pOutput.accept(ItemRegistry.SILVER_STICK.get());
                        pOutput.accept(ItemRegistry.GOLD_STICK.get());

                        pOutput.accept(ItemRegistry.IRON_PLATE.get());
                        pOutput.accept(ItemRegistry.IRON_SAW_BLADE.get());
                        pOutput.accept(ItemRegistry.PLATINUM_SAW_BLADE.get());
                        pOutput.accept(ItemRegistry.DIAMOND_SAW_BLADE.get());
                        pOutput.accept(ItemRegistry.TITANIUM_SAW_BLADE.get());
                        pOutput.accept(ItemRegistry.ENDER_ARCH_FRAGMENT.get());
                        pOutput.accept(ItemRegistry.ENDER_ARCH.get());
                    }).build());
    public static final RegistryObject<CreativeModeTab> TOOLS = TAB.register("tools",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ItemRegistry.AQUANIUM_PICKAXE.get()))
                    .title(Component.translatable("creativetab.arcanemysteries.tools"))
                    .displayItems((pParameters, pOutput) ->{
                        pOutput.accept(ItemRegistry.SILVER_SHOVEL.get());
                        pOutput.accept(ItemRegistry.SILVER_PICKAXE.get());
                        pOutput.accept(ItemRegistry.SILVER_AXE.get());
                        pOutput.accept(ItemRegistry.SILVER_HOE.get());
                        pOutput.accept(ItemRegistry.SILVER_HAMMER.get());

                        pOutput.accept(ItemRegistry.COPPER_SHOVEL.get());
                        pOutput.accept(ItemRegistry.COPPER_PICKAXE.get());
                        pOutput.accept(ItemRegistry.COPPER_AXE.get());
                        pOutput.accept(ItemRegistry.COPPER_HOE.get());

                        pOutput.accept(ItemRegistry.PLATINUM_SHOVEL.get());
                        pOutput.accept(ItemRegistry.PLATINUM_PICKAXE.get());
                        pOutput.accept(ItemRegistry.PLATINUM_AXE.get());
                        pOutput.accept(ItemRegistry.PLATINUM_HOE.get());
                        pOutput.accept(ItemRegistry.PLATINUM_HAMMER.get());

                        pOutput.accept(ItemRegistry.STEEL_SHOVEL.get());
                        pOutput.accept(ItemRegistry.STEEL_PICKAXE.get());
                        pOutput.accept(ItemRegistry.STEEL_AXE.get());
                        pOutput.accept(ItemRegistry.STEEL_HOE.get());
                        pOutput.accept(ItemRegistry.STEEL_HAMMER.get());

                        pOutput.accept(ItemRegistry.TITANIUM_SHOVEL.get());
                        pOutput.accept(ItemRegistry.TITANIUM_PICKAXE.get());
                        pOutput.accept(ItemRegistry.TITANIUM_AXE.get());
                        pOutput.accept(ItemRegistry.TITANIUM_HOE.get());
                        pOutput.accept(ItemRegistry.TITANIUM_HAMMER.get());

                        pOutput.accept(ItemRegistry.LUMINITE_SHOVEL.get());
                        pOutput.accept(ItemRegistry.LUMINITE_PICKAXE.get());
                        pOutput.accept(ItemRegistry.LUMINITE_AXE.get());
                        pOutput.accept(ItemRegistry.LUMINITE_HOE.get());

                        pOutput.accept(ItemRegistry.AQUANIUM_SHOVEL.get());
                        pOutput.accept(ItemRegistry.AQUANIUM_PICKAXE.get());
                        pOutput.accept(ItemRegistry.AQUANIUM_AXE.get());
                        pOutput.accept(ItemRegistry.AQUANIUM_HOE.get());

                        pOutput.accept(ItemRegistry.FROZEN_SHOVEL.get());
                        pOutput.accept(ItemRegistry.FROZEN_PICKAXE.get());
                        pOutput.accept(ItemRegistry.FROZEN_AXE.get());
                        pOutput.accept(ItemRegistry.FROZEN_HOE.get());

                        pOutput.accept(ItemRegistry.FIRERITE_SHOVEL.get());
                        pOutput.accept(ItemRegistry.FIRERITE_PICKAXE.get());
                        pOutput.accept(ItemRegistry.FIRERITE_AXE.get());
                        pOutput.accept(ItemRegistry.FIRERITE_HOE.get());

                        pOutput.accept(ItemRegistry.ENDERIUM_SHOVEL.get());
                        pOutput.accept(ItemRegistry.ENDERIUM_PICKAXE.get());
                        pOutput.accept(ItemRegistry.ENDERIUM_AXE.get());
                        pOutput.accept(ItemRegistry.ENDERIUM_HOE.get());

                        pOutput.accept(ItemRegistry.BEDROCK_SHOVEL.get());
                        pOutput.accept(ItemRegistry.BEDROCK_PICKAXE.get());
                        pOutput.accept(ItemRegistry.BEDROCK_AXE.get());
                        pOutput.accept(ItemRegistry.BEDROCK_HOE.get());

                        //pOutput.accept(ItemRegistry.END_LAVA_BUCKET.get()); //TODO
                        pOutput.accept(ItemRegistry.MOLTEN_SULFUR_BUCKET.get()); //TODO

                        pOutput.accept(ItemRegistry.FROZEN_BOAT.get());
                        pOutput.accept(ItemRegistry.FROZEN_CHEST_BOAT.get());
                        //pOutput.accept(ItemRegistry.STRONGHOLD_COMPASS.get()); //TODO
                    }).build());
    public static final RegistryObject<CreativeModeTab> DRINKS = TAB.register("drinks",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ItemRegistry.MILK_BOTTLE.get()))
                    .title(Component.translatable("creativetab.arcanemysteries.drinks"))
                    .displayItems((pParameters, pOutput) ->{
                        pOutput.accept(ItemRegistry.RAW_GOAT_MUTTON.get());
                        pOutput.accept(ItemRegistry.COOKED_GOAT_MUTTON.get());
                        pOutput.accept(ItemRegistry.KOHLRABI.get());
                        pOutput.accept(ItemRegistry.PEPPER.get());
                        pOutput.accept(ItemRegistry.STACKED_POTATO.get());
                        pOutput.accept(ItemRegistry.STACKED_BAKED_POTATO.get());
                        pOutput.accept(ItemRegistry.KOHLRABI_SEEDS.get());
                        pOutput.accept(ItemRegistry.PEPPER_SEEDS.get());
                        pOutput.accept(ItemRegistry.MILK_BOTTLE.get());
                        pOutput.accept(ItemRegistry.RAW_YAK_MEAT.get());
                        pOutput.accept(ItemRegistry.COOKED_YAK_MEAT.get());
                    }).build());
}

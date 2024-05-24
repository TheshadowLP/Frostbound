package net.shadowbeast.arcanemysteries.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static net.shadowbeast.arcanemysteries.ArcaneMysteries.MODID;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = MODID)
public class CreativeTabRegistry {
    public static final DeferredRegister<CreativeModeTab> TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
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
                        pOutput.accept(ItemRegistry.frozen_sign.get());
                        pOutput.accept(ItemRegistry.frozen_hanging_sign.get());
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

                        pOutput.accept(ModBlocks.BLUE_TARGET.get());
                        pOutput.accept(ModBlocks.BLACK_TARGET.get());
                        pOutput.accept(ModBlocks.GREEN_TARGET.get());
                        pOutput.accept(ModBlocks.YELLOW_TARGET.get());
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
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ItemRegistry.enderium_sword.get()))
                    .title(Component.translatable("creativetab.arcanemysteries.combat"))
                    .displayItems((pParameters, pOutput) ->{
                        pOutput.accept(ItemRegistry.silver_sword.get());
                        pOutput.accept(ItemRegistry.copper_sword.get());
                        pOutput.accept(ItemRegistry.platinum_sword.get());
                        pOutput.accept(ItemRegistry.steel_sword.get());
                        pOutput.accept(ItemRegistry.titanium_sword.get());
                        pOutput.accept(ItemRegistry.luminite_sword.get());
                        pOutput.accept(ItemRegistry.aquanium_sword.get());
                        pOutput.accept(ItemRegistry.frozen_sword.get());
                        pOutput.accept(ItemRegistry.firerite_sword.get());
                        pOutput.accept(ItemRegistry.enderium_sword.get());
                        pOutput.accept(ItemRegistry.bedrock_sword.get());

                        pOutput.accept(ItemRegistry.silver_axe.get());
                        pOutput.accept(ItemRegistry.copper_axe.get());
                        pOutput.accept(ItemRegistry.platinum_axe.get());
                        pOutput.accept(ItemRegistry.steel_axe.get());
                        pOutput.accept(ItemRegistry.titanium_axe.get());
                        pOutput.accept(ItemRegistry.luminite_axe.get());
                        pOutput.accept(ItemRegistry.aquanium_axe.get());
                        pOutput.accept(ItemRegistry.frozen_axe.get());
                        pOutput.accept(ItemRegistry.firerite_axe.get());
                        pOutput.accept(ItemRegistry.enderium_axe.get());
                        pOutput.accept(ItemRegistry.bedrock_axe.get());

                        pOutput.accept(ItemRegistry.silver_hammer.get());
                        pOutput.accept(ItemRegistry.platinum_hammer.get());
                        pOutput.accept(ItemRegistry.steel_hammer.get());
                        pOutput.accept(ItemRegistry.titanium_hammer.get());

                        pOutput.accept(ItemRegistry.heal_staff.get());
                        pOutput.accept(ItemRegistry.levitation_staff.get());
                        pOutput.accept(ItemRegistry.teleportation_staff.get());

                        pOutput.accept(ItemRegistry.silver_shield.get());
                        pOutput.accept(ItemRegistry.gold_shield.get());
                        pOutput.accept(ItemRegistry.copper_shield.get());
                        pOutput.accept(ItemRegistry.platinum_shield.get());
                        pOutput.accept(ItemRegistry.steel_shield.get());
                        pOutput.accept(ItemRegistry.titanium_shield.get());
                        pOutput.accept(ItemRegistry.luminite_shield.get());
                        pOutput.accept(ItemRegistry.aquanium_shield.get());
                        pOutput.accept(ItemRegistry.frozen_shield.get());
                        pOutput.accept(ItemRegistry.firerite_shield.get());
                        pOutput.accept(ItemRegistry.enderium_shield.get());

                        pOutput.accept(ItemRegistry.silver_helmet.get());
                        pOutput.accept(ItemRegistry.silver_chestplate.get());
                        pOutput.accept(ItemRegistry.silver_leggings.get());
                        pOutput.accept(ItemRegistry.silver_boots.get());

                        pOutput.accept(ItemRegistry.copper_helmet.get());
                        pOutput.accept(ItemRegistry.copper_chestplate.get());
                        pOutput.accept(ItemRegistry.copper_leggings.get());
                        pOutput.accept(ItemRegistry.copper_boots.get());

                        pOutput.accept(ItemRegistry.platinum_helmet.get());
                        pOutput.accept(ItemRegistry.platinum_chestplate.get());
                        pOutput.accept(ItemRegistry.platinum_leggings.get());
                        pOutput.accept(ItemRegistry.platinum_boots.get());

                        pOutput.accept(ItemRegistry.steel_helmet.get());
                        pOutput.accept(ItemRegistry.steel_chestplate.get());
                        pOutput.accept(ItemRegistry.steel_leggings.get());
                        pOutput.accept(ItemRegistry.steel_boots.get());

                        pOutput.accept(ItemRegistry.titanium_helmet.get());
                        pOutput.accept(ItemRegistry.titanium_chestplate.get());
                        pOutput.accept(ItemRegistry.titanium_leggings.get());
                        pOutput.accept(ItemRegistry.titanium_boots.get());

                        pOutput.accept(ItemRegistry.luminite_helmet.get());
                        pOutput.accept(ItemRegistry.luminite_chestplate.get());
                        pOutput.accept(ItemRegistry.luminite_leggings.get());
                        pOutput.accept(ItemRegistry.luminite_boots.get());

                        pOutput.accept(ItemRegistry.aquanium_helmet.get());
                        pOutput.accept(ItemRegistry.aquanium_chestplate.get());
                        pOutput.accept(ItemRegistry.aquanium_leggings.get());
                        pOutput.accept(ItemRegistry.aquanium_boots.get());

                        pOutput.accept(ItemRegistry.frozen_helmet.get());
                        pOutput.accept(ItemRegistry.frozen_chestplate.get());
                        pOutput.accept(ItemRegistry.frozen_leggings.get());
                        pOutput.accept(ItemRegistry.frozen_boots.get());

                        pOutput.accept(ItemRegistry.firerite_helmet.get());
                        pOutput.accept(ItemRegistry.firerite_chestplate.get());
                        pOutput.accept(ItemRegistry.firerite_leggings.get());
                        pOutput.accept(ItemRegistry.firerite_boots.get());

                        pOutput.accept(ItemRegistry.enderium_helmet.get());
                        pOutput.accept(ItemRegistry.enderium_chestplate.get());
                        pOutput.accept(ItemRegistry.enderium_leggings.get());
                        pOutput.accept(ItemRegistry.enderium_boots.get());
                        //pOutput.accept(ItemRegistry.ENDER_ELYTRA.get()); //TODO

                        pOutput.accept(ItemRegistry.mud_ball.get());
                        //pOutput.accept(ItemRegistry.DUNGEON_ICE_SPAWN_EGG.get()); //TODO
                        //pOutput.accept(ItemRegistry.ICE_BEAM.get()); //TODO
                    }).build());
    public static final RegistryObject<CreativeModeTab> INGREDIENTS = TAB.register("ingredients",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ItemRegistry.ender_ingot.get()))
                    .title(Component.translatable("creativetab.arcanemysteries.ingredients"))
                    .displayItems((pParameters, pOutput) ->{
                        pOutput.accept(ItemRegistry.raw_silver.get());
                        pOutput.accept(ItemRegistry.raw_platinum.get());
                        pOutput.accept(ItemRegistry.raw_titanium.get());
                        pOutput.accept(ItemRegistry.raw_luminite.get());
                        pOutput.accept(ItemRegistry.raw_enderium.get());
                        pOutput.accept(ItemRegistry.sulfur.get());
                        pOutput.accept(ItemRegistry.bedrock_chunk.get());

                        pOutput.accept(ItemRegistry.frozen_gem.get());
                        pOutput.accept(ItemRegistry.firerite_gem.get());
                        pOutput.accept(ItemRegistry.ruby.get());
                        pOutput.accept(ItemRegistry.aquanium_shard.get());
                        pOutput.accept(ItemRegistry.diamond_shard.get());

                        pOutput.accept(ItemRegistry.silver_nugget.get());
                        pOutput.accept(ItemRegistry.copper_nugget.get());
                        pOutput.accept(ItemRegistry.platinum_nugget.get());
                        pOutput.accept(ItemRegistry.steel_nugget.get());
                        pOutput.accept(ItemRegistry.titanium_nugget.get());
                        pOutput.accept(ItemRegistry.luminite_nugget.get());
                        pOutput.accept(ItemRegistry.aquanium_nugget.get());
                        pOutput.accept(ItemRegistry.enderium_nugget.get());

                        pOutput.accept(ItemRegistry.aluminium_ingot.get());
                        pOutput.accept(ItemRegistry.silver_ingot.get());
                        pOutput.accept(ItemRegistry.platinum_ingot.get());
                        pOutput.accept(ItemRegistry.steel_ingot.get());
                        pOutput.accept(ItemRegistry.titanium_ingot.get());
                        pOutput.accept(ItemRegistry.luminite_ingot.get());
                        pOutput.accept(ItemRegistry.aquanium_ingot.get());
                        pOutput.accept(ItemRegistry.enderium_ingot.get());
                        //pOutput.accept(ItemRegistry.END_GOLD_INGOT.get()); //TODO
                        pOutput.accept(ItemRegistry.enderium_ingot.get());

                        pOutput.accept(ItemRegistry.silver_dust.get());
                        pOutput.accept(ItemRegistry.gold_dust.get());
                        pOutput.accept(ItemRegistry.copper_dust.get());
                        pOutput.accept(ItemRegistry.iron_dust.get());
                        pOutput.accept(ItemRegistry.platinum_dust.get());
                        pOutput.accept(ItemRegistry.titanium_dust.get());
                        pOutput.accept(ItemRegistry.diamond_dust.get());
                        pOutput.accept(ItemRegistry.sulfur_dust.get());
                        pOutput.accept(ItemRegistry.ender_pearl_dust.get());

                        pOutput.accept(ItemRegistry.silver_stick.get());
                        pOutput.accept(ItemRegistry.gold_stick.get());

                        pOutput.accept(ItemRegistry.iron_plate.get());
                        pOutput.accept(ItemRegistry.iron_saw_blade.get());
                        pOutput.accept(ItemRegistry.platinum_saw_blade.get());
                        pOutput.accept(ItemRegistry.diamond_saw_blade.get());
                        pOutput.accept(ItemRegistry.titanium_saw_blade.get());
                        pOutput.accept(ItemRegistry.ender_arch_fragment.get());
                        pOutput.accept(ItemRegistry.ender_arch.get());
                    }).build());
    public static final RegistryObject<CreativeModeTab> TOOLS = TAB.register("tools",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ItemRegistry.aquanium_pickaxe.get()))
                    .title(Component.translatable("creativetab.arcanemysteries.tools"))
                    .displayItems((pParameters, pOutput) ->{
                        pOutput.accept(ItemRegistry.silver_shovel.get());
                        pOutput.accept(ItemRegistry.silver_pickaxe.get());
                        pOutput.accept(ItemRegistry.silver_axe.get());
                        pOutput.accept(ItemRegistry.silver_hoe.get());
                        pOutput.accept(ItemRegistry.silver_hammer.get());

                        pOutput.accept(ItemRegistry.copper_shovel.get());
                        pOutput.accept(ItemRegistry.copper_pickaxe.get());
                        pOutput.accept(ItemRegistry.copper_axe.get());
                        pOutput.accept(ItemRegistry.copper_hoe.get());

                        pOutput.accept(ItemRegistry.platinum_shovel.get());
                        pOutput.accept(ItemRegistry.platinum_pickaxe.get());
                        pOutput.accept(ItemRegistry.platinum_axe.get());
                        pOutput.accept(ItemRegistry.platinum_hoe.get());
                        pOutput.accept(ItemRegistry.platinum_hammer.get());

                        pOutput.accept(ItemRegistry.steel_shovel.get());
                        pOutput.accept(ItemRegistry.steel_pickaxe.get());
                        pOutput.accept(ItemRegistry.steel_axe.get());
                        pOutput.accept(ItemRegistry.steel_hoe.get());
                        pOutput.accept(ItemRegistry.steel_hammer.get());

                        pOutput.accept(ItemRegistry.titanium_shovel.get());
                        pOutput.accept(ItemRegistry.titanium_pickaxe.get());
                        pOutput.accept(ItemRegistry.titanium_axe.get());
                        pOutput.accept(ItemRegistry.titanium_hoe.get());
                        pOutput.accept(ItemRegistry.titanium_hammer.get());

                        pOutput.accept(ItemRegistry.luminite_shovel.get());
                        pOutput.accept(ItemRegistry.luminite_pickaxe.get());
                        pOutput.accept(ItemRegistry.luminite_axe.get());
                        pOutput.accept(ItemRegistry.luminite_hoe.get());

                        pOutput.accept(ItemRegistry.aquanium_shovel.get());
                        pOutput.accept(ItemRegistry.aquanium_pickaxe.get());
                        pOutput.accept(ItemRegistry.aquanium_axe.get());
                        pOutput.accept(ItemRegistry.aquanium_hoe.get());

                        pOutput.accept(ItemRegistry.frozen_shovel.get());
                        pOutput.accept(ItemRegistry.frozen_pickaxe.get());
                        pOutput.accept(ItemRegistry.frozen_axe.get());
                        pOutput.accept(ItemRegistry.frozen_hoe.get());

                        pOutput.accept(ItemRegistry.firerite_shovel.get());
                        pOutput.accept(ItemRegistry.firerite_pickaxe.get());
                        pOutput.accept(ItemRegistry.firerite_axe.get());
                        pOutput.accept(ItemRegistry.firerite_hoe.get());

                        pOutput.accept(ItemRegistry.enderium_shovel.get());
                        pOutput.accept(ItemRegistry.enderium_pickaxe.get());
                        pOutput.accept(ItemRegistry.enderium_axe.get());
                        pOutput.accept(ItemRegistry.enderium_hoe.get());

                        pOutput.accept(ItemRegistry.bedrock_shovel.get());
                        pOutput.accept(ItemRegistry.bedrock_pickaxe.get());
                        pOutput.accept(ItemRegistry.bedrock_axe.get());
                        pOutput.accept(ItemRegistry.bedrock_hoe.get());

                        //pOutput.accept(ItemRegistry.END_LAVA_BUCKET.get()); //TODO
                        //pOutput.accept(ItemRegistry.MOLTEN_SULFUR_BUCKET.get()); //TODO

                        pOutput.accept(ItemRegistry.frozen_boat.get());
                        pOutput.accept(ItemRegistry.frozen_chest_boat.get());
                        //pOutput.accept(ItemRegistry.STRONGHOLD_COMPASS.get()); //TODO
                    }).build());
    public static final RegistryObject<CreativeModeTab> DRINKS = TAB.register("drinks",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ItemRegistry.milk_bottle.get()))
                    .title(Component.translatable("creativetab.arcanemysteries.drinks"))
                    .displayItems((pParameters, pOutput) ->{
                        pOutput.accept(ItemRegistry.kohlrabi.get());
                        pOutput.accept(ItemRegistry.pepper.get());
                        pOutput.accept(ItemRegistry.stacked_potato.get());
                        pOutput.accept(ItemRegistry.stacked_baked_potato.get());
                        pOutput.accept(ItemRegistry.kohlrabi_seeds.get());
                        pOutput.accept(ItemRegistry.pepper_seeds.get());
                        pOutput.accept(ItemRegistry.milk_bottle.get());
                    }).build());

}

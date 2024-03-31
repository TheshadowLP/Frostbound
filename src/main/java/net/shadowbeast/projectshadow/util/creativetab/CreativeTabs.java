package net.shadowbeast.projectshadow.util.creativetab;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.shadowbeast.projectshadow.ProjectShadow;
import net.shadowbeast.projectshadow.blocks.ModBlocks;
import net.shadowbeast.projectshadow.items.ModItems;

@SuppressWarnings("unused")
public class CreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ProjectShadow.MOD_ID);

    public static final RegistryObject<CreativeModeTab> DEV_TAB = CREATIVE_MODE_TABS.register("dev_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.AQUANIUM_ORE.get()))
                    .title(Component.translatable("creativetab.dev_tab"))
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
                        pOutput.accept(ModBlocks.FROZEN_GEM_ORE.get());
                        pOutput.accept(ModItems.FROZEN_GEM.get());
                        pOutput.accept(ModBlocks.FROZEN_GEM_BlOCK.get());
                        pOutput.accept(ModBlocks.FROZEN_STONE.get());
                        pOutput.accept(ModBlocks.FROZEN_COBBLESTONE.get());
                        pOutput.accept(ModBlocks.FROZEN_STONE_BRICKS.get());
                        pOutput.accept(ModBlocks.FROZEN_COBBLESTONE_WALL.get());
                        pOutput.accept(ModBlocks.FROZEN_STONE_BRICK_WALL.get());
                        pOutput.accept(ModBlocks.FROZEN_SAPLING.get());
                        pOutput.accept(ModBlocks.FROZEN_LEAVES.get());
                        pOutput.accept(ModItems.FROZEN_SIGN.get());
                        pOutput.accept(ModItems.FROZEN_HANGING_SIGN.get());
                        pOutput.accept(ModItems.FROZEN_BOAT.get());
                        pOutput.accept(ModItems.FROZEN_CHEST_BOAT.get());

                        pOutput.accept(ModBlocks.AQUANIUM_ORE.get());
                        pOutput.accept(ModBlocks.SAND_AQUANIUM_ORE.get());
                        pOutput.accept(ModItems.AQUANIUM_SHARD.get());
                        pOutput.accept(ModItems.AQUANIUM_INGOT.get());
                        pOutput.accept(ModItems.AQUANIUM_POWDER.get());
                        pOutput.accept(ModItems.AQUANIUM_NUGGET.get());
                        pOutput.accept(ModBlocks.AQUANIUM_BlOCK.get());

                        pOutput.accept(ModBlocks.RUBY_ORE.get());
                        pOutput.accept(ModItems.RUBY.get());
                        pOutput.accept(ModBlocks.RUBY_BlOCK.get());
                        pOutput.accept(ModBlocks.PLATINUM_ORE.get());
                        pOutput.accept(ModItems.RAW_PLATINUM.get());
                        pOutput.accept(ModItems.PLATINUM_INGOT.get());
                        pOutput.accept(ModItems.PLATINUM_POWDER.get());
                        pOutput.accept(ModItems.PLATINUM_NUGGET.get());
                        pOutput.accept(ModBlocks.PLATINUM_BlOCK.get());

                        pOutput.accept(ModBlocks.STEEL_ORE.get());
                        pOutput.accept(ModItems.RAW_STEEL.get());
                        pOutput.accept(ModItems.STEEL_INGOT.get());
                        pOutput.accept(ModItems.STEEL_POWDER.get());
                        pOutput.accept(ModItems.STEEL_NUGGET.get());
                        pOutput.accept(ModBlocks.STEEL_BlOCK.get());

                        pOutput.accept(ModBlocks.SILVER_ORE.get());
                        pOutput.accept(ModItems.RAW_SILVER.get());
                        pOutput.accept(ModItems.SILVER_INGOT.get());
                        pOutput.accept(ModItems.SILVER_POWDER.get());
                        pOutput.accept(ModItems.SILVER_NUGGET.get());
                        pOutput.accept(ModItems.SILVER_STICK.get());
                        pOutput.accept(ModBlocks.SILVER_BlOCK.get());

                        pOutput.accept(ModBlocks.TITANIUM_ORE.get());
                        pOutput.accept(ModItems.RAW_TITANIUM.get());
                        pOutput.accept(ModItems.TITANIUM_INGOT.get());
                        pOutput.accept(ModItems.TITANIUM_POWDER.get());
                        pOutput.accept(ModItems.TITANIUM_NUGGET.get());
                        pOutput.accept(ModBlocks.TITANIUM_BlOCK.get());

                        pOutput.accept(ModBlocks.ENDERIUM_END_ORE.get());
                        pOutput.accept(ModItems.RAW_ENDERIUM.get());
                        pOutput.accept(ModItems.ENDERIUM_INGOT.get());
                        pOutput.accept(ModItems.ENDERIUM_POWDER.get());
                        pOutput.accept(ModItems.ENDERIUM_NUGGET.get());
                        pOutput.accept(ModBlocks.ENDERIUM_BlOCK.get());

                        pOutput.accept(ModBlocks.LUMINITE_ORE.get());
                        pOutput.accept(ModBlocks.DEEPSLATE_LUMINITE_ORE.get());
                        pOutput.accept(ModItems.RAW_LUMINITE.get());
                        pOutput.accept(ModItems.LUMINITE_NUGGET.get());
                        pOutput.accept(ModItems.LUMINITE_INGOT.get());
                        pOutput.accept(ModItems.LUMINITE_POWDER.get());
                        pOutput.accept(ModBlocks.LUMINITE_BlOCK.get());

                        pOutput.accept(ModItems.COPPER_POWDER.get());
                        pOutput.accept(ModItems.COPPER_NUGGET.get());

                        pOutput.accept(ModItems.GOLD_POWDER.get());
                        pOutput.accept(ModItems.GOLD_STICK.get());
                        pOutput.accept(ModItems.ENDER_PEARL_POWDER.get());
                        pOutput.accept(ModItems.IRON_POWDER.get());
                        pOutput.accept(ModItems.DIAMOND_POWDER.get());
                        pOutput.accept(ModItems.DIAMOND_SHARD.get());
                        pOutput.accept(ModBlocks.NETHER_FIRERITE_ORE.get());
                        pOutput.accept(ModItems.FIRERITE_GEM.get());
                        pOutput.accept(ModBlocks.FIRERITE_BlOCK.get());
                        pOutput.accept(ModBlocks.SULFUR_ORE.get());
                        pOutput.accept(ModItems.SULFUR.get());
                        pOutput.accept(ModItems.SULFUR_POWDER.get());
                        pOutput.accept(ModItems.SAW_BLADE.get());
                        //pOutput.accept(ModBlocks.WINTER_FURNACE.get()); //TODO will be avaible in the second mod version
                        //pOutput.accept(ModBlocks.ALLOY_FURNACE.get()); //TODO will be avaible in the second mod version
                        pOutput.accept(ModBlocks.CRUSHER.get());
                        pOutput.accept(ModItems.IRON_PLATE.get());
                        pOutput.accept(ModItems.ENDER_ARCH_FRAGMENT.get());
                        pOutput.accept(ModItems.ENDER_ARCH.get());
                        pOutput.accept(ModItems.ENDER_INGOT.get());
                        pOutput.accept(ModItems.MOLTEN_SULFUR_BUCKET.get());
                        pOutput.accept(ModItems.MILK_BOTTLE.get());
                        pOutput.accept(ModBlocks.COPPER_BRICK.get());
                        pOutput.accept(ModBlocks.EXPOSED_COPPER_BRICK.get());
                        pOutput.accept(ModBlocks.WEATHERED_COPPER_BRICK.get());
                        pOutput.accept(ModBlocks.OXIDIZED_COPPER_BRICK.get());
                        pOutput.accept(ModBlocks.WAXED_COPPER_BRICK.get());
                        pOutput.accept(ModBlocks.WAXED_EXPOSED_COPPER_BRICK.get());
                        pOutput.accept(ModBlocks.WAXED_WEATHERED_COPPER_BRICK.get());
                        pOutput.accept(ModBlocks.WAXED_OXIDIZED_COPPER_BRICK.get());
                        pOutput.accept(ModBlocks.CUT_COPPER_BRICK.get());
                        pOutput.accept(ModBlocks.EXPOSED_CUT_COPPER_BRICK.get());
                        pOutput.accept(ModBlocks.WEATHERED_CUT_COPPER_BRICK.get());
                        pOutput.accept(ModBlocks.OXIDIZED_CUT_COPPER_BRICK.get());
                        pOutput.accept(ModBlocks.WAXED_CUT_COPPER_BRICK.get());
                        pOutput.accept(ModBlocks.WAXED_EXPOSED_CUT_COPPER_BRICK.get());
                        pOutput.accept(ModBlocks.WAXED_WEATHERED_CUT_COPPER_BRICK.get());
                        pOutput.accept(ModBlocks.WAXED_OXIDIZED_CUT_COPPER_BRICK.get());
                        pOutput.accept(ModBlocks.BONE_ORE.get());
                    })
                    .build());
    public static final RegistryObject<CreativeModeTab> COMBAT = CREATIVE_MODE_TABS.register("combat",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.ENDERIUM_SWORD.get()))
                    .title(Component.translatable("creativetab.combat"))
                    .displayItems((pParameters, pOutput) ->{
                        pOutput.accept(ModItems.SILVER_SWORD.get());
                        pOutput.accept(ModItems.COPPER_SWORD.get());
                        pOutput.accept(ModItems.PLATINUM_SWORD.get());
                        pOutput.accept(ModItems.STEEL_SWORD.get());
                        pOutput.accept(ModItems.TITANIUM_SWORD.get());
                        pOutput.accept(ModItems.LUMINITE_SWORD.get());
                        pOutput.accept(ModItems.AQUANIUM_SWORD.get());
                        pOutput.accept(ModItems.FROZEN_SWORD.get());
                        pOutput.accept(ModItems.FIRERITE_SWORD.get());
                        pOutput.accept(ModItems.ENDERIUM_SWORD.get());

                        pOutput.accept(ModItems.SILVER_AXE.get());
                        pOutput.accept(ModItems.COPPER_AXE.get());
                        pOutput.accept(ModItems.PLATINUM_AXE.get());
                        pOutput.accept(ModItems.STEEL_AXE.get());
                        pOutput.accept(ModItems.TITANIUM_AXE.get());
                        pOutput.accept(ModItems.LUMINITE_AXE.get());
                        pOutput.accept(ModItems.AQUANIUM_AXE.get());
                        pOutput.accept(ModItems.FROZEN_AXE.get());
                        pOutput.accept(ModItems.FIRERITE_AXE.get());
                        pOutput.accept(ModItems.ENDERIUM_AXE.get());

                        pOutput.accept(ModItems.SILVER_HAMMER.get());
                        pOutput.accept(ModItems.PLATINUM_HAMMER.get());
                        pOutput.accept(ModItems.STEEL_HAMMER.get());
                        pOutput.accept(ModItems.TITANIUM_HAMMER.get());

                        pOutput.accept(ModItems.HEAL_STAFF.get());
                        pOutput.accept(ModItems.LEVITATION_STAFF.get());
                        pOutput.accept(ModItems.TELEPORTATION_STAFF.get());

                        pOutput.accept(ModItems.SILVER_SHIELD.get());
                        pOutput.accept(ModItems.GOLD_SHIELD.get());
                        pOutput.accept(ModItems.COPPER_SHIELD.get());
                        pOutput.accept(ModItems.PLATINUM_SHIELD.get());
                        pOutput.accept(ModItems.STEEL_SHIELD.get());
                        pOutput.accept(ModItems.TITANIUM_SHIELD.get());
                        pOutput.accept(ModItems.LUMINITE_SHIELD.get());
                        pOutput.accept(ModItems.AQUANIUM_SHIELD.get());
                        pOutput.accept(ModItems.FROZEN_SHIELD.get());
                        pOutput.accept(ModItems.FIRERITE_SHIELD.get());
                        pOutput.accept(ModItems.ENDERIUM_SHIELD.get());

                        pOutput.accept(ModItems.COPPER_HELMET.get());
                        pOutput.accept(ModItems.COPPER_CHESTPLATE.get());
                        pOutput.accept(ModItems.COPPER_LEGGINGS.get());
                        pOutput.accept(ModItems.COPPER_BOOTS.get());

                        pOutput.accept(ModItems.STEEL_HELMET.get());
                        pOutput.accept(ModItems.STEEL_CHESTPLATE.get());
                        pOutput.accept(ModItems.STEEL_LEGGINGS.get());
                        pOutput.accept(ModItems.STEEL_BOOTS.get());

                        pOutput.accept(ModItems.AQUANIUM_HELMET.get());
                        pOutput.accept(ModItems.AQUANIUM_CHESTPLATE.get());
                        pOutput.accept(ModItems.AQUANIUM_LEGGINGS.get());
                        pOutput.accept(ModItems.AQUANIUM_BOOTS.get());

                        pOutput.accept(ModItems.ENDERIUM_HELMET.get());
                        pOutput.accept(ModItems.ENDERIUM_CHESTPLATE.get());
                        pOutput.accept(ModItems.ENDERIUM_LEGGINGS.get());
                        pOutput.accept(ModItems.ENDERIUM_BOOTS.get());

                        pOutput.accept(ModItems.LUMINITE_HELMET.get());
                        pOutput.accept(ModItems.LUMINITE_CHESTPLATE.get());
                        pOutput.accept(ModItems.LUMINITE_LEGGINGS.get());
                        pOutput.accept(ModItems.LUMINITE_BOOTS.get());

                        pOutput.accept(ModItems.PLATINUM_HELMET.get());
                        pOutput.accept(ModItems.PLATINUM_CHESTPLATE.get());
                        pOutput.accept(ModItems.PLATINUM_LEGGINGS.get());
                        pOutput.accept(ModItems.PLATINUM_BOOTS.get());

                        pOutput.accept(ModItems.SILVER_HELMET.get());
                        pOutput.accept(ModItems.SILVER_CHESTPLATE.get());
                        pOutput.accept(ModItems.SILVER_LEGGINGS.get());
                        pOutput.accept(ModItems.SILVER_BOOTS.get());

                        pOutput.accept(ModItems.FROZEN_HELMET.get());
                        pOutput.accept(ModItems.FROZEN_CHESTPLATE.get());
                        pOutput.accept(ModItems.FROZEN_LEGGINGS.get());
                        pOutput.accept(ModItems.FROZEN_BOOTS.get());

                        pOutput.accept(ModItems.FIRERITE_HELMET.get());
                        pOutput.accept(ModItems.FIRERITE_CHESTPLATE.get());
                        pOutput.accept(ModItems.FIRERITE_LEGGINGS.get());
                        pOutput.accept(ModItems.FIRERITE_BOOTS.get());

                        pOutput.accept(ModItems.MUD_BALL.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> TOOLS = CREATIVE_MODE_TABS.register("tools",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.AQUANIUM_PICKAXE.get()))
                    .title(Component.translatable("creativetab.tools"))
                    .displayItems((pParameters, pOutput) ->{
                        pOutput.accept(ModItems.SILVER_SHOVEL.get());
                        pOutput.accept(ModItems.SILVER_PICKAXE.get());
                        pOutput.accept(ModItems.SILVER_AXE.get());
                        pOutput.accept(ModItems.SILVER_HOE.get());
                        pOutput.accept(ModItems.SILVER_HAMMER.get());

                        pOutput.accept(ModItems.COPPER_SHOVEL.get());
                        pOutput.accept(ModItems.COPPER_PICKAXE.get());
                        pOutput.accept(ModItems.COPPER_AXE.get());
                        pOutput.accept(ModItems.COPPER_HOE.get());

                        pOutput.accept(ModItems.PLATINUM_SHOVEL.get());
                        pOutput.accept(ModItems.PLATINUM_PICKAXE.get());
                        pOutput.accept(ModItems.PLATINUM_AXE.get());
                        pOutput.accept(ModItems.PLATINUM_HOE.get());
                        pOutput.accept(ModItems.PLATINUM_HAMMER.get());

                        pOutput.accept(ModItems.STEEL_SHOVEL.get());
                        pOutput.accept(ModItems.STEEL_PICKAXE.get());
                        pOutput.accept(ModItems.STEEL_AXE.get());
                        pOutput.accept(ModItems.STEEL_HOE.get());
                        pOutput.accept(ModItems.STEEL_HAMMER.get());

                        pOutput.accept(ModItems.TITANIUM_SHOVEL.get());
                        pOutput.accept(ModItems.TITANIUM_PICKAXE.get());
                        pOutput.accept(ModItems.TITANIUM_AXE.get());
                        pOutput.accept(ModItems.TITANIUM_HOE.get());
                        pOutput.accept(ModItems.TITANIUM_HAMMER.get());

                        pOutput.accept(ModItems.LUMINITE_SHOVEL.get());
                        pOutput.accept(ModItems.LUMINITE_PICKAXE.get());
                        pOutput.accept(ModItems.LUMINITE_AXE.get());
                        pOutput.accept(ModItems.LUMINITE_HOE.get());

                        pOutput.accept(ModItems.AQUANIUM_SHOVEL.get());
                        pOutput.accept(ModItems.AQUANIUM_PICKAXE.get());
                        pOutput.accept(ModItems.AQUANIUM_AXE.get());
                        pOutput.accept(ModItems.AQUANIUM_HOE.get());

                        pOutput.accept(ModItems.FROZEN_SHOVEL.get());
                        pOutput.accept(ModItems.FROZEN_PICKAXE.get());
                        pOutput.accept(ModItems.FROZEN_AXE.get());
                        pOutput.accept(ModItems.FROZEN_HOE.get());

                        pOutput.accept(ModItems.FIRERITE_SHOVEL.get());
                        pOutput.accept(ModItems.FIRERITE_PICKAXE.get());
                        pOutput.accept(ModItems.FIRERITE_AXE.get());
                        pOutput.accept(ModItems.FIRERITE_HOE.get());

                        pOutput.accept(ModItems.ENDERIUM_SHOVEL.get());
                        pOutput.accept(ModItems.ENDERIUM_PICKAXE.get());
                        pOutput.accept(ModItems.ENDERIUM_AXE.get());
                        pOutput.accept(ModItems.ENDERIUM_HOE.get());

                        pOutput.accept(ModItems.MOLTEN_SULFUR_BUCKET.get());
                    })
                    .build());
    public static final RegistryObject<CreativeModeTab> DRINKS = CREATIVE_MODE_TABS.register("drinks",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.STACKED_BAKED_POTATO.get()))
                    .title(Component.translatable("creativetab.drinks"))
                    .displayItems((pParameters, pOutput) ->{
                        pOutput.accept(ModItems.KOHLRABI.get());
                        pOutput.accept(ModItems.KOHLRABI_SEEDS.get());
                        //pOutput.accept(ModItems.PEPPER.get()); //TODO will be avaible in the second mod version
                        //pOutput.accept(ModItems.PEPPER_SEEDS.get()); //TODO will be avaible in the second mod version
                        pOutput.accept(ModItems.STACKED_POTATO.get());
                        pOutput.accept(ModItems.STACKED_BAKED_POTATO.get());
                        pOutput.accept(ModItems.MILK_BOTTLE.get());
                    })
                    .build());
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}

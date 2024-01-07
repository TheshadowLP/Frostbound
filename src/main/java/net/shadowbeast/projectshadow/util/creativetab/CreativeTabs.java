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
                        pOutput.accept(ModBlocks.FROZEN_STONE.get());
                        pOutput.accept(ModBlocks.FROZEN_COBBLESTONE.get());


                        pOutput.accept(ModBlocks.AQUANIUM_ORE.get());
                        pOutput.accept(ModBlocks.SAND_AQUANIUM_ORE.get());
                        pOutput.accept(ModItems.AQUANIUM_SHARD.get());
                        pOutput.accept(ModItems.AQUANIUM_INGOT.get());
                        pOutput.accept(ModItems.AQUANIUM_NUGGET.get());
                        pOutput.accept(ModBlocks.AQUANIUM_BlOCK.get());
                        pOutput.accept(ModBlocks.RUBY_ORE.get());
                        pOutput.accept(ModItems.RUBY.get());
                        pOutput.accept(ModBlocks.RUBY_BlOCK.get());
                        pOutput.accept(ModBlocks.PLATINUM_ORE.get());
                        pOutput.accept(ModItems.RAW_PLATINUM.get());
                        pOutput.accept(ModItems.PLATINUM_INGOT.get());
                        pOutput.accept(ModItems.PLATINUM_NUGGET.get());
                        pOutput.accept(ModBlocks.PLATINUM_BlOCK.get());
                        pOutput.accept(ModItems.PLATINUM_AXE.get());
                        pOutput.accept(ModItems.PLATINUM_HAMMER.get());
                        pOutput.accept(ModItems.PLATINUM_HOE.get());
                        pOutput.accept(ModItems.PLATINUM_PICKAXE.get());
                        pOutput.accept(ModItems.PLATINUM_SHOVEL.get());
                        pOutput.accept(ModItems.PLATINUM_SWORD.get());
                        pOutput.accept(ModBlocks.STEEL_ORE.get());
                        pOutput.accept(ModItems.RAW_STEEL.get());
                        pOutput.accept(ModItems.STEEL_INGOT.get());
                        pOutput.accept(ModItems.STEEL_NUGGET.get());
                        pOutput.accept(ModBlocks.STEEL_BlOCK.get());
                        pOutput.accept(ModItems.STEEL_AXE.get());
                        pOutput.accept(ModItems.STEEL_HAMMER.get());
                        pOutput.accept(ModItems.STEEL_HOE.get());
                        pOutput.accept(ModItems.STEEL_PICKAXE.get());
                        pOutput.accept(ModItems.STEEL_SHOVEL.get());
                        pOutput.accept(ModItems.STEEL_SWORD.get());
                        pOutput.accept(ModBlocks.SILVER_ORE.get());
                        pOutput.accept(ModItems.RAW_SILVER.get());
                        pOutput.accept(ModItems.SILVER_INGOT.get());
                        pOutput.accept(ModItems.SILVER_NUGGET.get());
                        pOutput.accept(ModBlocks.SILVER_BlOCK.get());
                        pOutput.accept(ModItems.SILVER_AXE.get());
                        pOutput.accept(ModItems.SILVER_HAMMER.get());
                        pOutput.accept(ModItems.SILVER_HOE.get());
                        pOutput.accept(ModItems.SILVER_PICKAXE.get());
                        pOutput.accept(ModItems.SILVER_SHOVEL.get());
                        pOutput.accept(ModItems.SILVER_SWORD.get());
                        pOutput.accept(ModBlocks.TITANIUM_ORE.get());
                        pOutput.accept(ModItems.RAW_TITANIUM.get());
                        pOutput.accept(ModItems.TITANIUM_INGOT.get());
                        pOutput.accept(ModItems.TITANIUM_NUGGET.get());
                        pOutput.accept(ModBlocks.TITANIUM_BlOCK.get());
                        pOutput.accept(ModItems.TITANIUM_AXE.get());
                        pOutput.accept(ModItems.TITANIUM_HAMMER.get());
                        pOutput.accept(ModItems.TITANIUM_HOE.get());
                        pOutput.accept(ModItems.TITANIUM_PICKAXE.get());
                        pOutput.accept(ModItems.TITANIUM_SHOVEL.get());
                        pOutput.accept(ModItems.TITANIUM_SWORD.get());
                        pOutput.accept(ModBlocks.ENDERIUM_END_ORE.get());
                        pOutput.accept(ModItems.RAW_ENDERIUM.get());
                        pOutput.accept(ModItems.ENDERIUM_INGOT.get());
                        pOutput.accept(ModItems.ENDERIUM_NUGGET.get());
                        pOutput.accept(ModBlocks.ENDERIUM_BlOCK.get());
                        pOutput.accept(ModItems.ENDERIUM_AXE.get());
                        pOutput.accept(ModItems.ENDERIUM_HOE.get());
                        pOutput.accept(ModItems.ENDERIUM_PICKAXE.get());
                        pOutput.accept(ModItems.ENDERIUM_SHOVEL.get());
                        pOutput.accept(ModItems.ENDERIUM_SWORD.get());
                        pOutput.accept(ModBlocks.LUMINITE_ORE.get());
                        pOutput.accept(ModBlocks.DEEPSLATE_LUMINITE_ORE.get());
                        pOutput.accept(ModItems.RAW_LUMINITE.get());
                        pOutput.accept(ModItems.LUMINITE_NUGGET.get());
                        pOutput.accept(ModItems.LUMINITE_INGOT.get());
                        pOutput.accept(ModBlocks.LUMINITE_BlOCK.get());

                        pOutput.accept(ModItems.COPPER_NUGGET.get());
                        pOutput.accept(ModItems.COPPER_AXE.get());
                        pOutput.accept(ModItems.COPPER_HOE.get());
                        pOutput.accept(ModItems.COPPER_PICKAXE.get());
                        pOutput.accept(ModItems.COPPER_SHOVEL.get());
                        pOutput.accept(ModItems.COPPER_SWORD.get());

                        pOutput.accept(ModItems.HEAL_STAFF.get());


                        //TODO

                        pOutput.accept(ModBlocks.FIRERITE_ORE.get());
                        pOutput.accept(ModBlocks.FIRERITE_BlOCK.get());
                        pOutput.accept(ModBlocks.FUSION_FURNACE.get());
                        pOutput.accept(ModBlocks.WINTER_FURNACE.get());
                        pOutput.accept(ModBlocks.FROZEN_SAPLING.get());
                        pOutput.accept(ModBlocks.FROZEN_COBBLESTONE_WALL.get());
                        pOutput.accept(ModBlocks.FROZEN_LEAVES.get());


                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}

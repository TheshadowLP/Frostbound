package net.shadowbeast.arcanemysteries.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;
import net.shadowbeast.arcanemysteries.items.ModItems;
import net.shadowbeast.arcanemysteries.util.ModTags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> future,
                               CompletableFuture<TagLookup<Block>> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, future, completableFuture, ArcaneMysteries.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
        // Add Item Tags here
        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.SILVER_HELMET.get(),
                        ModItems.SILVER_CHESTPLATE.get(),
                        ModItems.SILVER_LEGGINGS.get(),
                        ModItems.SILVER_BOOTS.get())
                .add(ModItems.AQUANIUM_HELMET.get(),
                        ModItems.AQUANIUM_CHESTPLATE.get(),
                        ModItems.AQUANIUM_LEGGINGS.get(),
                        ModItems.AQUANIUM_BOOTS.get())
                .add(ModItems.ENDERIUM_HELMET.get(),
                        ModItems.ENDERIUM_CHESTPLATE.get(),
                        ModItems.ENDERIUM_LEGGINGS.get(),
                        ModItems.ENDERIUM_BOOTS.get())
                .add(ModItems.COPPER_HELMET.get(),
                        ModItems.COPPER_CHESTPLATE.get(),
                        ModItems.COPPER_LEGGINGS.get(),
                        ModItems.COPPER_BOOTS.get())
                .add(ModItems.FROZEN_HELMET.get(),
                        ModItems.FROZEN_CHESTPLATE.get(),
                        ModItems.FROZEN_LEGGINGS.get(),
                        ModItems.FROZEN_BOOTS.get())
                .add(ModItems.FIRERITE_HELMET.get(),
                        ModItems.FIRERITE_CHESTPLATE.get(),
                        ModItems.FIRERITE_LEGGINGS.get(),
                        ModItems.FIRERITE_BOOTS.get())
                .add(ModItems.SILVER_HELMET.get(),
                        ModItems.SILVER_CHESTPLATE.get(),
                        ModItems.SILVER_LEGGINGS.get(),
                        ModItems.SILVER_BOOTS.get())
                .add(ModItems.STEEL_HELMET.get(),
                        ModItems.STEEL_CHESTPLATE.get(),
                        ModItems.STEEL_LEGGINGS.get(),
                        ModItems.STEEL_BOOTS.get())
                .add(ModItems.LUMINITE_HELMET.get(),
                        ModItems.LUMINITE_CHESTPLATE.get(),
                        ModItems.LUMINITE_LEGGINGS.get(),
                        ModItems.LUMINITE_BOOTS.get())
                .add(ModItems.PLATINUM_HELMET.get(),
                        ModItems.PLATINUM_CHESTPLATE.get(),
                        ModItems.PLATINUM_LEGGINGS.get(),
                        ModItems.PLATINUM_BOOTS.get())
                .add(ModItems.TITANIUM_HELMET.get(),
                        ModItems.TITANIUM_CHESTPLATE.get(),
                        ModItems.TITANIUM_LEGGINGS.get(),
                        ModItems.TITANIUM_BOOTS.get());
        this.tag(ModTags.Items.SAW_BLADES)
                .add(ModItems.IRON_SAW_BLADE.get(),
                        ModItems.DIAMOND_SAW_BLADE.get(),
                        ModItems.TITANIUM_SAW_BLADE.get(),
                        ModItems.PLATINUM_SAW_BLADE.get());
        this.tag(ModTags.Items.ALLOYING_FUEL)
                .add(Items.ACACIA_LOG,
                        Items.BIRCH_LOG,
                        Items.OAK_LOG,
                        Items.JUNGLE_LOG,
                        Items.SPRUCE_LOG,
                        Items.CHERRY_LOG,
                        Items.DARK_OAK_LOG,
                        Items.MANGROVE_LOG,
                        Items.STRIPPED_ACACIA_LOG,
                        Items.STRIPPED_BIRCH_LOG,
                        Items.STRIPPED_OAK_LOG,
                        Items.STRIPPED_JUNGLE_LOG,
                        Items.STRIPPED_SPRUCE_LOG,
                        Items.STRIPPED_CHERRY_LOG,
                        Items.STRIPPED_DARK_OAK_LOG,
                        Items.STRIPPED_MANGROVE_LOG,
                        Items.ACACIA_WOOD,
                        Items.BIRCH_WOOD,
                        Items.OAK_WOOD,
                        Items.JUNGLE_WOOD,
                        Items.SPRUCE_WOOD,
                        Items.CHERRY_WOOD,
                        Items.DARK_OAK_WOOD,
                        Items.MANGROVE_WOOD,
                        Items.STRIPPED_ACACIA_WOOD,
                        Items.STRIPPED_BIRCH_WOOD,
                        Items.STRIPPED_OAK_WOOD,
                        Items.STRIPPED_JUNGLE_WOOD,
                        Items.STRIPPED_SPRUCE_WOOD,
                        Items.STRIPPED_CHERRY_WOOD,
                        Items.STRIPPED_DARK_OAK_WOOD,
                        Items.STRIPPED_MANGROVE_WOOD,
                        Items.COAL, Items.CHARCOAL, Items.COAL_BLOCK, Items.LAVA_BUCKET);
        this.tag(ModTags.Items.ALLOYING_FUEL_SMALL)
                .add(Items.ACACIA_LOG,
                        Items.BIRCH_LOG,
                        Items.OAK_LOG,
                        Items.JUNGLE_LOG,
                        Items.SPRUCE_LOG,
                        Items.CHERRY_LOG,
                        Items.DARK_OAK_LOG,
                        Items.MANGROVE_LOG,
                        Items.STRIPPED_ACACIA_LOG,
                        Items.STRIPPED_BIRCH_LOG,
                        Items.STRIPPED_OAK_LOG,
                        Items.STRIPPED_JUNGLE_LOG,
                        Items.STRIPPED_SPRUCE_LOG,
                        Items.STRIPPED_CHERRY_LOG,
                        Items.STRIPPED_DARK_OAK_LOG,
                        Items.STRIPPED_MANGROVE_LOG,
                        Items.ACACIA_WOOD,
                        Items.BIRCH_WOOD,
                        Items.OAK_WOOD,
                        Items.JUNGLE_WOOD,
                        Items.SPRUCE_WOOD,
                        Items.CHERRY_WOOD,
                        Items.DARK_OAK_WOOD,
                        Items.MANGROVE_WOOD,
                        Items.STRIPPED_ACACIA_WOOD,
                        Items.STRIPPED_BIRCH_WOOD,
                        Items.STRIPPED_OAK_WOOD,
                        Items.STRIPPED_JUNGLE_WOOD,
                        Items.STRIPPED_SPRUCE_WOOD,
                        Items.STRIPPED_CHERRY_WOOD,
                        Items.STRIPPED_DARK_OAK_WOOD,
                        Items.STRIPPED_MANGROVE_WOOD);
        this.tag(ModTags.Items.ALLOYING_FUEL_MEDIUM)
                .add(Items.COAL, Items.CHARCOAL);
        this.tag(ModTags.Items.ALLOYING_FUEL_LARGE)
                .add(Items.COAL_BLOCK);
    }
    @Override
    public @NotNull String getName() {
        return "Item Tags";
    }
}

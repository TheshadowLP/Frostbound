package net.shadowbeast.frostbound.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.shadowbeast.frostbound.Frostbound;
import net.shadowbeast.frostbound.registries.ItemRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;
public class ItemTagGenerator extends ItemTagsProvider {
    public ItemTagGenerator(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture,
                            CompletableFuture<TagLookup<Block>> lookupCompletableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, completableFuture, lookupCompletableFuture, Frostbound.MOD_ID, existingFileHelper);
    }
    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {

        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ItemRegistry.AQUANIUM_BOOTS.get())
                .add(ItemRegistry.AQUANIUM_LEGGINGS.get())
                .add(ItemRegistry.AQUANIUM_CHESTPLATE.get())
                .add(ItemRegistry.AQUANIUM_HELMET.get())

                .add(ItemRegistry.LUMINITE_BOOTS.get())
                .add(ItemRegistry.LUMINITE_LEGGINGS.get())
                .add(ItemRegistry.LUMINITE_CHESTPLATE.get())
                .add(ItemRegistry.LUMINITE_HELMET.get())

                .add(ItemRegistry.SILVER_BOOTS.get())
                .add(ItemRegistry.SILVER_LEGGINGS.get())
                .add(ItemRegistry.SILVER_CHESTPLATE.get())
                .add(ItemRegistry.SILVER_HELMET.get())

                .add(ItemRegistry.STEEL_BOOTS.get())
                .add(ItemRegistry.STEEL_LEGGINGS.get())
                .add(ItemRegistry.STEEL_CHESTPLATE.get())
                .add(ItemRegistry.STEEL_HELMET.get())

                .add(ItemRegistry.PLATINUM_BOOTS.get())
                .add(ItemRegistry.PLATINUM_LEGGINGS.get())
                .add(ItemRegistry.PLATINUM_CHESTPLATE.get())
                .add(ItemRegistry.PLATINUM_HELMET.get())

                .add(ItemRegistry.TITANIUM_BOOTS.get())
                .add(ItemRegistry.TITANIUM_LEGGINGS.get())
                .add(ItemRegistry.TITANIUM_CHESTPLATE.get())
                .add(ItemRegistry.TITANIUM_HELMET.get())

                .add(ItemRegistry.FIRERITE_BOOTS.get())
                .add(ItemRegistry.FIRERITE_LEGGINGS.get())
                .add(ItemRegistry.FIRERITE_CHESTPLATE.get())
                .add(ItemRegistry.FIRERITE_HELMET.get())

                .add(ItemRegistry.FROZEN_BOOTS.get())
                .add(ItemRegistry.FROZEN_LEGGINGS.get())
                .add(ItemRegistry.FROZEN_CHESTPLATE.get())
                .add(ItemRegistry.FROZEN_HELMET.get())

                .add(ItemRegistry.COPPER_BOOTS.get())
                .add(ItemRegistry.COPPER_LEGGINGS.get())
                .add(ItemRegistry.COPPER_CHESTPLATE.get())
                .add(ItemRegistry.COPPER_HELMET.get());
    }
}
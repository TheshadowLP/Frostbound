package net.shadowbeast.projectshadow.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.shadowbeast.projectshadow.ProjectShadow;
import net.shadowbeast.projectshadow.items.ModItems;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> future,
                               CompletableFuture<TagLookup<Block>> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, future, completableFuture, ProjectShadow.MOD_ID, existingFileHelper);
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
    }
    @Override
    public @NotNull String getName() {
        return "Item Tags";
    }
}

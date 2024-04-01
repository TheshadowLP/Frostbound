package net.shadowbeast.projectshadow.items.custom;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.shadowbeast.projectshadow.items.ModItems;

public class ModItemProperties {
    public static void addCustomItemProperties() {
        ItemProperties.register(ModItems.AQUANIUM_SHIELD.get(), new ResourceLocation("blocking"), (p_174575_, p_174576_, p_174577_, p_174578_) -> p_174577_ != null && p_174577_.isUsingItem() && p_174577_.getUseItem() == p_174575_ ? 1.0F : 0.0F);
        ItemProperties.register(ModItems.COPPER_SHIELD.get(), new ResourceLocation("blocking"), (p_174575_, p_174576_, p_174577_, p_174578_) -> p_174577_ != null && p_174577_.isUsingItem() && p_174577_.getUseItem() == p_174575_ ? 1.0F : 0.0F);
        ItemProperties.register(ModItems.ENDERIUM_SHIELD.get(), new ResourceLocation("blocking"), (p_174575_, p_174576_, p_174577_, p_174578_) -> p_174577_ != null && p_174577_.isUsingItem() && p_174577_.getUseItem() == p_174575_ ? 1.0F : 0.0F);
        ItemProperties.register(ModItems.FIRERITE_SHIELD.get(), new ResourceLocation("blocking"), (p_174575_, p_174576_, p_174577_, p_174578_) -> p_174577_ != null && p_174577_.isUsingItem() && p_174577_.getUseItem() == p_174575_ ? 1.0F : 0.0F);
        ItemProperties.register(ModItems.FROZEN_SHIELD.get(), new ResourceLocation("blocking"), (p_174575_, p_174576_, p_174577_, p_174578_) -> p_174577_ != null && p_174577_.isUsingItem() && p_174577_.getUseItem() == p_174575_ ? 1.0F : 0.0F);
        ItemProperties.register(ModItems.GOLD_SHIELD.get(), new ResourceLocation("blocking"), (p_174575_, p_174576_, p_174577_, p_174578_) -> p_174577_ != null && p_174577_.isUsingItem() && p_174577_.getUseItem() == p_174575_ ? 1.0F : 0.0F);
        ItemProperties.register(ModItems.LUMINITE_SHIELD.get(), new ResourceLocation("blocking"), (p_174575_, p_174576_, p_174577_, p_174578_) -> p_174577_ != null && p_174577_.isUsingItem() && p_174577_.getUseItem() == p_174575_ ? 1.0F : 0.0F);
        ItemProperties.register(ModItems.PLATINUM_SHIELD.get(), new ResourceLocation("blocking"), (p_174575_, p_174576_, p_174577_, p_174578_) -> p_174577_ != null && p_174577_.isUsingItem() && p_174577_.getUseItem() == p_174575_ ? 1.0F : 0.0F);
        ItemProperties.register(ModItems.SILVER_SHIELD.get(), new ResourceLocation("blocking"), (p_174575_, p_174576_, p_174577_, p_174578_) -> p_174577_ != null && p_174577_.isUsingItem() && p_174577_.getUseItem() == p_174575_ ? 1.0F : 0.0F);
        ItemProperties.register(ModItems.STEEL_SHIELD.get(), new ResourceLocation("blocking"), (p_174575_, p_174576_, p_174577_, p_174578_) -> p_174577_ != null && p_174577_.isUsingItem() && p_174577_.getUseItem() == p_174575_ ? 1.0F : 0.0F);
        ItemProperties.register(ModItems.TITANIUM_SHIELD.get(), new ResourceLocation("blocking"), (p_174575_, p_174576_, p_174577_, p_174578_) -> p_174577_ != null && p_174577_.isUsingItem() && p_174577_.getUseItem() == p_174575_ ? 1.0F : 0.0F);
    }
}

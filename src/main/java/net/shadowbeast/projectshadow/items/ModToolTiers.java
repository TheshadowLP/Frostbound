package net.shadowbeast.projectshadow.items;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.shadowbeast.projectshadow.ProjectShadow;
import net.shadowbeast.projectshadow.util.ModTags;

import java.util.List;

public class ModToolTiers {
    public static final Tier PLATINUM = TierSortingRegistry.registerTier(
            new ForgeTier(2,510,6.0f,2f,14,
                    ModTags.Blocks.NEEDS_PLATINUM_TOOL, () -> Ingredient.of(ModItems.PLATINUM_INGOT.get())),
            new ResourceLocation(ProjectShadow.MOD_ID,"platinum"), List.of(Tiers.IRON),List.of());
    public static final Tier TITANIUM = TierSortingRegistry.registerTier(
            new ForgeTier(2,1120,6.0f,2f,15,
                    ModTags.Blocks.NEEDS_TITANIUM_TOOL, () -> Ingredient.of(ModItems.TITANIUM_INGOT.get())),
            new ResourceLocation(ProjectShadow.MOD_ID,"titanium"), List.of(Tiers.IRON),List.of());
    public static final Tier SILVER = TierSortingRegistry.registerTier(
            new ForgeTier(1,210,5.0f,2f,13,
                    ModTags.Blocks.NEEDS_SILVER_TOOL, () -> Ingredient.of(ModItems.SILVER_INGOT.get())),
            new ResourceLocation(ProjectShadow.MOD_ID,"silver"), List.of(Tiers.IRON),List.of());
    public static final Tier STEEL = TierSortingRegistry.registerTier(
            new ForgeTier(2,826,6.0f,2f,15,
                    ModTags.Blocks.NEEDS_STEEL_TOOL, () -> Ingredient.of(ModItems.STEEL_INGOT.get())),
            new ResourceLocation(ProjectShadow.MOD_ID,"steel"), List.of(Tiers.IRON),List.of());

}

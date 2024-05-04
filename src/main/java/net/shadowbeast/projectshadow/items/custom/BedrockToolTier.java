package net.shadowbeast.projectshadow.items.custom;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import net.shadowbeast.projectshadow.ProjectShadow;
import net.shadowbeast.projectshadow.blocks.ModBlocks;
import net.shadowbeast.projectshadow.util.ModTags;

import java.util.List;

public class BedrockToolTier {
    public static final Tier BEDROCK = TierSortingRegistry.registerTier(
            new ForgeTier(5, 2530, 11.0f, 5f, 15,
                    ModTags.Blocks.NEEDS_BEDROCK_TOOL, () -> Ingredient.of(ModBlocks.BEDROCK.get())),
            new ResourceLocation(ProjectShadow.MOD_ID, "bedrock"), List.of(Tiers.NETHERITE), List.of());

}

//Class needed becuase bedrock can only be dropped by self from bedrock tools and for some reason it was not being registered in ToolStats. Still same stats as enderium
// TODO Change the stats a bit too be better than the Enderium tools

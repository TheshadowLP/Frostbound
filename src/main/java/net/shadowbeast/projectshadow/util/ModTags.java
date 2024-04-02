package net.shadowbeast.projectshadow.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.shadowbeast.projectshadow.ProjectShadow;
public class ModTags {
    public static class Items {
        public static final TagKey<Item> FROZEN_LOGS = tag("frozen_logs");
        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(ProjectShadow.MOD_ID, name));
        }
    }
    public static class Blocks {
        public static final TagKey<Block> FROZEN_LOGS = tag("frozen_logs");
        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(ProjectShadow.MOD_ID, name));
        }
    }
}

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
        public static final TagKey<Item> FROZEN_LOGS = tag();
        private static TagKey<Item> tag() {
            return ItemTags.create(new ResourceLocation(ProjectShadow.MOD_ID, "frozen_logs"));
        }
    }
    public static class Blocks {
        public static final TagKey<Block> FROZEN_LOGS = tag();
        private static TagKey<Block> tag() {
            return BlockTags.create(new ResourceLocation(ProjectShadow.MOD_ID, "frozen_logs"));
        }
    }
}

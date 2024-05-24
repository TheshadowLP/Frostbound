package net.shadowbeast.arcanemysteries.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;
public class ModTags {
    public static class Items {
        public static final TagKey<Item> FROZEN_LOGS = tag("frozen_logs");
        public static final TagKey<Item> SAW_BLADES = tag("saw_blades");
        public static final TagKey<Item> ALLOYING_FUEL = tag("alloying_fuel");
        public static final TagKey<Item> ALLOYING_FUEL_LARGE = tag("alloying_fuel_large");
        public static final TagKey<Item> ALLOYING_FUEL_MEDIUM = tag("alloying_fuel_medium");
        public static final TagKey<Item> ALLOYING_FUEL_SMALL = tag("alloying_fuel_small");
        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(ArcaneMysteries.MOD_ID, name));
        }
    }
    public static class Blocks {
        public static final TagKey<Block> FROZEN_LOGS = tag("frozen_logs");
        public static final TagKey<Block> NEEDS_BEDROCK_TOOL = tag("needs_bedrock_tool");
        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(ArcaneMysteries.MOD_ID, name));
        }
    }
}

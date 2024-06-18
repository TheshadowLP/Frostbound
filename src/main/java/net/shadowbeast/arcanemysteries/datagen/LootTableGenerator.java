package net.shadowbeast.arcanemysteries.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.shadowbeast.arcanemysteries.datagen.loot.BlockLootTableGenerator;

import java.util.List;
import java.util.Set;

public class LootTableGenerator {
    public static LootTableProvider create(PackOutput output) {
        return new LootTableProvider(output, Set.of(), List.of(
                new LootTableProvider.SubProviderEntry(BlockLootTableGenerator::new, LootContextParamSets.BLOCK)
        ));
    }
}

package net.shadowbeast.frostbound.datagen;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.shadowbeast.frostbound.Frostbound;
public class LootModifierGenerator extends GlobalLootModifierProvider {
    public LootModifierGenerator(PackOutput output) {
        super(output, Frostbound.MOD_ID);
    }
    @Override
    protected void start() {
    }
}
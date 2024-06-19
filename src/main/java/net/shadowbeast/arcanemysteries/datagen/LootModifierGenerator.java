package net.shadowbeast.arcanemysteries.datagen;


import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;

public class LootModifierGenerator extends GlobalLootModifierProvider {
    public LootModifierGenerator(PackOutput output) {
        super(output, ArcaneMysteries.MOD_ID);
    }

    @Override
    protected void start() {
    }
}
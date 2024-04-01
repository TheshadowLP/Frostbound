package net.shadowbeast.projectshadow.blocks.crops;

import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.shadowbeast.projectshadow.items.ModItems;
import org.jetbrains.annotations.NotNull;

//Credits for Kohlrabi textures to Kaupenjoe, in future custom texture will be added
public class KohlrabiCropBlock extends CropBlock {
    public static final int MAX_AGE = 6;
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 6);
    public KohlrabiCropBlock(BlockBehaviour.Properties pProperties) {
        super(pProperties);
    }
    @Override
    protected @NotNull ItemLike getBaseSeedId() {
        return ModItems.KOHLRABI_SEEDS.get();
    }
    @Override
    public @NotNull IntegerProperty getAgeProperty() {
        return AGE;
    }
    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AGE);
    }
}

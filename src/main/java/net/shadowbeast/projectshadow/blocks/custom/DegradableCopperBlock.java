package net.shadowbeast.projectshadow.blocks.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("deprecation")
public class DegradableCopperBlock extends Block implements GemDegradable {
    private final GemDegradationLevel degradationLevel;
    public DegradableCopperBlock(GemDegradationLevel degradationLevel, Properties pProperties) {
        super(pProperties);
        this.degradationLevel = degradationLevel;
    }
    @Override
    public void randomTick(@NotNull BlockState pState, @NotNull ServerLevel pLevel, @NotNull BlockPos pPos, @NotNull RandomSource pRandom) {
        this.onRandomTick(pState, pLevel, pPos, pRandom);
    }
    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return GemDegradable.getNext(pState.getBlock()).isPresent();
    }
    @Override
    public @NotNull GemDegradationLevel getAge() {
        return degradationLevel;
    }
}

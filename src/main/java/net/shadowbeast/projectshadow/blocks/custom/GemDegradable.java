package net.shadowbeast.projectshadow.blocks.custom;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChangeOverTimeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.shadowbeast.projectshadow.blocks.ModBlocks;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Supplier;

public interface GemDegradable extends ChangeOverTimeBlock<GemDegradable.GemDegradationLevel> {
    Supplier<BiMap<Block, Block>> NEXT_BY_BLOCK = Suppliers.memoize(() -> ImmutableBiMap.<Block, Block>builder()
            //.put(ModBlocks.COPPER_BRICK.get(), ModBlocks.EXPOSED_COPPER_BRICK.get())
            .put(ModBlocks.EXPOSED_COPPER_BRICK.get(), ModBlocks.WEATHERED_COPPER_BRICK.get()).build());
            //.put(ModBlocks.WEATHERED_COPPER_BRICK.get(), ModBlocks.OXIDIZED_COPPER_BRICK.get())
            //.put(ModBlocks.CUT_COPPER_BRICK.get(), ModBlocks.EXPOSED_CUT_COPPER_BRICK.get())
            //.put(ModBlocks.EXPOSED_CUT_COPPER_BRICK.get(), ModBlocks.WEATHERED_CUT_COPPER_BRICK.get())
            //.put(ModBlocks.WEATHERED_CUT_COPPER_BRICK.get(), ModBlocks.OXIDIZED_CUT_COPPER_BRICK.get())
    Supplier<BiMap<Block, Block>> PREVIOUS_BY_BLOCK = Suppliers.memoize(() -> NEXT_BY_BLOCK.get().inverse());
    static Optional<Block> getPrevious(Block pBlock) {
        return Optional.ofNullable(PREVIOUS_BY_BLOCK.get().get(pBlock));
    }
    static Block getFirst(Block pBlock) {
        Block block = pBlock;

        for(Block block1 = PREVIOUS_BY_BLOCK.get().get(pBlock); block1 != null; block1 = PREVIOUS_BY_BLOCK.get().get(block1)) {
            block = block1;
        }
        return block;
    }
    static Optional<BlockState> getPrevious(@NotNull BlockState pState) {
        return getPrevious(pState.getBlock()).map((p_154903_) -> p_154903_.withPropertiesOf(pState));
    }
    static Optional<Block> getNext(Block pBlock) {
        return Optional.ofNullable(NEXT_BY_BLOCK.get().get(pBlock));
    }
    static @NotNull BlockState getFirst(@NotNull BlockState pState) {
        return getFirst(pState.getBlock()).withPropertiesOf(pState);
    }
    default @NotNull Optional<BlockState> getNext(@NotNull BlockState pState) {
        return getNext(pState.getBlock()).map((p_154896_) -> p_154896_.withPropertiesOf(pState));
    }
    default float getChanceModifier() {
        return this.getAge() == GemDegradationLevel.UNAFFECTED ? 0.75F : 1.0F;
    }
    public static enum GemDegradationLevel {
        UNAFFECTED,
        EXPOSED,
        WEATHERED,
        DEGRADED
    }
}

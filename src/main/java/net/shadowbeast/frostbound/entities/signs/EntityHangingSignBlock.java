package net.shadowbeast.frostbound.entities.signs;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.shadowbeast.frostbound.registries.EntityRegistry;
import org.jetbrains.annotations.NotNull;

public class EntityHangingSignBlock extends HangingSignBlockEntity {
    public EntityHangingSignBlock(BlockPos blockPos, BlockState blockState) {
        super(blockPos, blockState);
    }
    @Override
    public @NotNull BlockEntityType<?> getType() { return EntityRegistry.MOD_HANGING_SIGN.get(); }
}
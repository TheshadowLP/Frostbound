package net.shadowbeast.arcanemysteries.entities.signs;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.shadowbeast.arcanemysteries.registries.EntityRegistry;
import org.jetbrains.annotations.NotNull;

public class EntitySignBlock extends SignBlockEntity {
    public EntitySignBlock(BlockPos pPos, BlockState pBlockState) {
        super(EntityRegistry.MOD_SIGN.get(), pPos, pBlockState);
    }
    @Override
    public @NotNull BlockEntityType<?> getType() {
        return EntityRegistry.MOD_SIGN.get();
    }
}
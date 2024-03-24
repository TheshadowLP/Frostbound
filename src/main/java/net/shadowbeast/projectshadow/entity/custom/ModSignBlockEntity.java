package net.shadowbeast.projectshadow.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.shadowbeast.projectshadow.entity.ModBlockEntities;
import org.jetbrains.annotations.NotNull;

public class ModSignBlockEntity extends SignBlockEntity {
    public ModSignBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.MOD_SIGN.get(), pPos, pBlockState);
    }
    @Override
    public @NotNull BlockEntityType<?> getType() {
        return ModBlockEntities.MOD_SIGN.get();
    }
}
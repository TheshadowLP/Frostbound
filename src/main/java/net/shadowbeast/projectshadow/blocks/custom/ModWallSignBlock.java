package net.shadowbeast.projectshadow.blocks.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.shadowbeast.projectshadow.entity.custom.ModSignBlockEntity;
import org.jetbrains.annotations.NotNull;

public class ModWallSignBlock extends WallSignBlock {
    public ModWallSignBlock(Properties pProperties, WoodType pType) {
        super(pProperties, pType);
    }
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pPos, @NotNull BlockState pState) {
        return new ModSignBlockEntity(pPos, pState);
    }
}

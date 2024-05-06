package net.shadowbeast.projectshadow.blocks.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@SuppressWarnings("deprecation")
public class SulfuricTNTBlock extends Block {
    public SulfuricTNTBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack handItem = pPlayer.getItemInHand(pHand);
        if (handItem.is(Items.FLINT_AND_STEEL)) {
            pLevel.setBlock(pPos, Blocks.AIR.defaultBlockState(), 3);
            pLevel.explode(pPlayer, pPos.getX() + 0.5, pPos.getY() + 0.5, pPos.getZ() + 0.5, 7.5f, true, Level.ExplosionInteraction.TNT);
            return InteractionResult.SUCCESS;
        } else {
            return InteractionResult.FAIL;
        }
    }

    @Override
    public void onBlockExploded(BlockState state, Level level, BlockPos pos, Explosion explosion) {
        level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
        level.explode(null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 7.5f, true, Level.ExplosionInteraction.TNT);
        super.onBlockExploded(state, level, pos, explosion);
    }
}

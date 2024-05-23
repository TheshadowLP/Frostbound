package net.shadowbeast.projectshadow.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.shadowbeast.projectshadow.levitation_staff.PlayerLevitationTagProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.concurrent.atomic.AtomicBoolean;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow protected abstract void checkFallDamage(double pY, boolean pOnGround, BlockState pState, BlockPos pPos);

    @Shadow public abstract void resetFallDistance();

    Entity entity = ((Entity)(Object)this);
    @Redirect(method = "move", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;checkFallDamage(DZLnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/BlockPos;)V"))
    private void cancelFallDamage(Entity instance, double pY, boolean pOnGround, BlockState pState, BlockPos pPos) {
        resetFallDistance();
        this.checkFallDamage(0, true, Blocks.AIR.defaultBlockState(), pPos);
    }
}
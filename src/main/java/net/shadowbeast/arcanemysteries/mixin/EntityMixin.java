package net.shadowbeast.arcanemysteries.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.state.BlockState;
import net.shadowbeast.arcanemysteries.util.interfaces.IFallDamageCancelable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin implements IFallDamageCancelable {
    @Unique
    Entity entity = ((Entity) (Object) this);

    @Unique
    boolean cancelFallDamage = false;

    @Shadow
    public float fallDistance;

    @Shadow
    public abstract void resetFallDistance();

    @Override
    public void setCancelFallDamage(boolean cancelFallDamage) {
        this.cancelFallDamage = cancelFallDamage;
    }

    @Override
    public boolean shouldCancelFallDamage() {
        return this.cancelFallDamage;
    }

    @Inject(method = "load", at = @At(value = "INVOKE", target = "Lnet/minecraft/nbt/CompoundTag;getFloat(Ljava/lang/String;)F", shift = At.Shift.BEFORE))
    private void addCancelFallDamageToNbt(CompoundTag pCompound, CallbackInfo ci) {
        this.cancelFallDamage = pCompound.getBoolean("CancelFallDamage");
    }

    @Inject(method = "saveWithoutId", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;getDeltaMovement()Lnet/minecraft/world/phys/Vec3;", shift = At.Shift.BEFORE))
    private void saveCancelFallDamageToNbt(CompoundTag pCompound, CallbackInfoReturnable<CompoundTag> cir) {
        pCompound.putBoolean("CancelFallDamage", this.cancelFallDamage);
    }

    @Inject(method = "checkFallDamage", at = @At(value = "HEAD"))
    private void cancelFallDamage(double pY, boolean pOnGround, BlockState pState, BlockPos pPos, CallbackInfo ci) {
        if (shouldCancelFallDamage() && pOnGround && this.fallDistance > 0.0F) {
            resetFallDistance();
            setCancelFallDamage(false);
        }
    }
}
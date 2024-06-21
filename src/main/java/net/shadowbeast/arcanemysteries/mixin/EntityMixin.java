package net.shadowbeast.arcanemysteries.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.shadowbeast.arcanemysteries.interfaces.util.IFallDamageCancelable;
import net.shadowbeast.arcanemysteries.interfaces.util.IRoastedEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(Entity.class)
public abstract class EntityMixin implements IRoastedEntity, IFallDamageCancelable {
    @Unique
    Entity arcaneMysteries$entity = ((Entity) (Object) this);

    @Unique
    boolean arcaneMysteries$cancelFallDamage = false;

    @Shadow
    public float fallDistance;

    @Shadow
    public abstract void resetFallDistance();

    @Shadow
    @Final
    protected SynchedEntityData entityData;
    @Shadow
    public boolean isInPowderSnow;
    @Shadow
    public int tickCount;

    @Shadow
    public boolean canFreeze() {
        return false;
    } // its either true or false, and is never happy

    @Shadow
    public abstract boolean hurt(DamageSource pSource, float pAmount);

    @Shadow
    public abstract EntityType<?> getType();

    @Shadow
    public abstract DamageSources damageSources();

    @Shadow
    public abstract boolean isSpectator();

    @Shadow
    public abstract Level level();

    @Inject(method = "<init>", at = @At("TAIL"))
    public void init_inject(CallbackInfo info) {
        this.entityData.define(DATA_TICKS_ROASTED, 0);
    }

    @Inject(method = "saveWithoutId", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;getUUID()Ljava/util/UUID;"), locals = LocalCapture.CAPTURE_FAILHARD)
    public void saveWithoutId_inject(CompoundTag pCompound, CallbackInfoReturnable<CompoundTag> cir) {
        int i = this.arcaneMysteries$getTicksRoasted();
        if (i > 0) {
            pCompound.putInt("TicksRoasted", this.arcaneMysteries$getTicksRoasted());
        }
    }

    @Inject(method = "load", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;setTicksFrozen(I)V"), locals = LocalCapture.CAPTURE_FAILHARD)
    public void load_inject(CompoundTag pCompound, CallbackInfo ci) {
        this.arcaneMysteries$setTicksRoasted(pCompound.getInt("TicksRoasted"));
    }

    @Override
    public int arcaneMysteries$getTicksRoasted() {
        return this.entityData.get(DATA_TICKS_ROASTED);
    }

    @Override
    public void arcaneMysteries$setTicksRoasted(int pTicksFrozen) {
        this.entityData.set(DATA_TICKS_ROASTED, pTicksFrozen);
    }

    @Override
    public void setArcaneMysteries$cancelFallDamage(boolean arcaneMysteries$cancelFallDamage) {
        this.arcaneMysteries$cancelFallDamage = arcaneMysteries$cancelFallDamage;
    }

    @Override
    public boolean isArcaneMysteries$cancelFallDamage() {
        return this.arcaneMysteries$cancelFallDamage;
    }

    @Override
    public float arcaneMysteries$getPercentRoasted() {
        int i = this.getTicksRequiredToRoast();
        return (float) Math.min(this.arcaneMysteries$getTicksRoasted(), i) / (float) i;
    }

    @Override
    public boolean arcaneMysteries$isFullyRoasted() {
        return this.arcaneMysteries$getTicksRoasted() >= this.getTicksRequiredToRoast();
    }

    @Override
    public boolean arcaneMysteries$canRoast() {
        return !this.getType().is(EntityTypeTags.FREEZE_IMMUNE_ENTITY_TYPES);
    }

    @Inject(method = "checkFallDamage", at = @At(value = "HEAD"))
    private void cancelFallDamage(double pY, boolean pOnGround, BlockState pState, BlockPos pPos, CallbackInfo ci) {
        if (isArcaneMysteries$cancelFallDamage()) {
            if (pOnGround && this.fallDistance > 0.0F) {
                resetFallDistance();
                setArcaneMysteries$cancelFallDamage(false);
            }
        }
    }
}
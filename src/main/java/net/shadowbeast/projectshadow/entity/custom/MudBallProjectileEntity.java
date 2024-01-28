package net.shadowbeast.projectshadow.entity.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.shadowbeast.projectshadow.entity.ModEntities;
import org.jetbrains.annotations.NotNull;


public class MudBallProjectileEntity extends ThrowableItemProjectile {
    public MudBallProjectileEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    public MudBallProjectileEntity(Level pLevel) {
        this(ModEntities.MUDBALL_PROJECTILE.get(), pLevel);
    }
    public MudBallProjectileEntity(Level pLevel, LivingEntity livingEntity) {
        super(ModEntities.MUDBALL_PROJECTILE.get(), livingEntity, pLevel);
    }
    @Override
    protected Item getDefaultItem() { return null; }
    @Override
    protected void onHitEntity(@NotNull EntityHitResult hitResult) {
        super.onHitEntity(hitResult);
        Entity hitEntity = hitResult.getEntity();
        Entity owner = this.getOwner();

        if(hitEntity == owner && this.level().isClientSide()) {
            return;
        }
        if(hitEntity instanceof LivingEntity livingHitEntity) {
            livingHitEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 220, 2), owner);
            livingHitEntity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 220, 1), owner);
        }
    }
    protected void onHit(@NotNull HitResult pResult) {
        super.onHit(pResult);
        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();
        }
    }
}
package net.shadowbeast.arcanemysteries.entities.projectile;

import net.minecraft.core.particles.ParticleTypes;
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
import net.minecraft.world.phys.Vec3;
import net.shadowbeast.arcanemysteries.registries.EntityRegistry;
import org.jetbrains.annotations.NotNull;

public class EntityMudBall extends ThrowableItemProjectile {

    public EntityMudBall(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public EntityMudBall(Level pLevel) {
        this(EntityRegistry.MUDBALL_PROJECTILE.get(), pLevel);
    }

    public EntityMudBall(Level pLevel, LivingEntity livingEntity) {
        super(EntityRegistry.MUDBALL_PROJECTILE.get(), livingEntity, pLevel);
    }

    @Override
    protected @NotNull Item getDefaultItem() {
        return null;
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult hitResult) {
        super.onHitEntity(hitResult);
        Entity hitEntity = hitResult.getEntity();
        Entity owner = this.getOwner();

        if(hitEntity == owner && this.level().isClientSide()) {
            return;
        }
        if(hitEntity instanceof LivingEntity livingHitEntity) {
            livingHitEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,350,2));
        }
    }

    protected void onHit(@NotNull HitResult pResult) {
        super.onHit(pResult);
        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte)3);
            createMudSplash(pResult.getLocation());
            this.discard();
        }
    }

    private void createMudSplash(Vec3 location) {
        for(int i = 0; i < 100; i++) {
            this.level().addParticle(ParticleTypes.ITEM_SNOWBALL, location.x, location.y, location.z, 0.0D, 0.0D, 0.0D);
        }
    }
}
package net.shadowbeast.projectshadow.entity.custom;

import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.shadowbeast.projectshadow.effect.ModEffects;
import net.shadowbeast.projectshadow.entity.ModEntities;
import net.shadowbeast.projectshadow.items.ModItems;

import static net.minecraft.world.item.enchantment.EnchantmentHelper.getEnchantmentLevel;

public class IceBeamProjectileEntity extends ThrowableItemProjectile {
    public IceBeamProjectileEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    public IceBeamProjectileEntity(Level pLevel) {
        super(ModEntities.ICE_BEAM_PROJECTILE.get(), pLevel);
    }  public IceBeamProjectileEntity(Level pLevel, LivingEntity livingEntity) {
        super(ModEntities.ICE_BEAM_PROJECTILE.get(), livingEntity, pLevel);
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) entity;
            int duration = 40;
            livingEntity.addEffect(new MobEffectInstance(ModEffects.FREEZE.get(), duration));
        }
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.ICE_BEAM.get();
    }
}

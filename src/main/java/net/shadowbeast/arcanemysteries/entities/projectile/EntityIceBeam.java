package net.shadowbeast.arcanemysteries.entities.projectile;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.shadowbeast.arcanemysteries.registries.EffectsRegistry;
import net.shadowbeast.arcanemysteries.registries.EntityRegistry;
import net.shadowbeast.arcanemysteries.registries.ItemRegistry;
import org.jetbrains.annotations.NotNull;
public class EntityIceBeam extends ThrowableItemProjectile {
    public EntityIceBeam(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    public EntityIceBeam(Level pLevel) {
        super(EntityRegistry.ICE_BEAM_PROJECTILE.get(), pLevel);
    }
    public EntityIceBeam(Level pLevel, LivingEntity livingEntity) {
        super(EntityRegistry.ICE_BEAM_PROJECTILE.get(), livingEntity, pLevel);
    }
    @Override
    protected void onHitEntity(@NotNull EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();
        if (entity instanceof LivingEntity livingEntity) {
            int duration = 40;
            livingEntity.addEffect(new MobEffectInstance(EffectsRegistry.FREEZE.get(), duration));
        }
    }
    @Override
    protected @NotNull Item getDefaultItem() {
        return ItemRegistry.ICE_BEAM.get();
    }
}

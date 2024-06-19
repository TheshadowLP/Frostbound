package net.shadowbeast.arcanemysteries.mixin;

import net.minecraft.tags.ItemTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.shadowbeast.arcanemysteries.temprature.TemperatureData;
import net.shadowbeast.arcanemysteries.temprature.util.EStats;
import net.shadowbeast.arcanemysteries.temprature.util.TUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends EEntityMixin {

    public LivingEntityMixin(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Shadow public abstract ItemStack getItemBySlot(EquipmentSlot pSlot);
    @Shadow
    public boolean hurt(DamageSource pSource, float pAmount) {return false;}

    @Redirect(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z"))
    public boolean aiStep_hurt_redirect(LivingEntity living, DamageSource pSource, float pAmount) {
        float amount = pAmount;

        if ((LivingEntity)(Object)this instanceof Player ) {
            double maxCold1 = TUtil.firstCold((Player)(Object)this);
            double maxCold2 = TUtil.secondCold((Player)(Object)this);
            double maxCold3 = TUtil.maxCold((Player)(Object)this);
            TemperatureData data = EStats.getTemperatureStats((LivingEntity)(Object)this);
            if (data.getTemperatureLevel() < maxCold1 && data.getTemperatureLevel() >= maxCold2) {
                amount *= 1;
            }
            else if (data.getTemperatureLevel() < maxCold2 && data.getTemperatureLevel() >= maxCold3) {
                amount *= 10;
            }
            else if (data.getTemperatureLevel() < maxCold3) {
                amount *= 100;
            }
        }

        return living.hurt(this.damageSources().freeze(), amount);
    }

    @Redirect(method = "aiStep", at = @At(value = "FIELD", target = "Lnet/minecraft/world/entity/LivingEntity;isInPowderSnow:Z"))
    public boolean aiStep_isInPowderSnow_redirect(LivingEntity living) {
        if ((LivingEntity)(Object)this instanceof Player) {
            TemperatureData data = EStats.getTemperatureStats((LivingEntity)(Object)this);
            return data.getTemperatureLevel() < TUtil.firstCold((Player)(Object)this) || this.isInPowderSnow;
        }
        return this.isInPowderSnow;
    }




    /**
     * @author everyone
     * @reason why do we need this
     */
    @Overwrite
    public boolean canFreeze() {
        if (this.isSpectator()) {
            return false;
        } else {
            if ((LivingEntity)(Object)this instanceof Player) {
                TemperatureData data = EStats.getTemperatureStats((LivingEntity)(Object)this);
                return data.getTemperatureLevel() < TUtil.firstCold((Player)(Object)this) && super.canFreeze();
            } else {
                boolean flag = !this.getItemBySlot(EquipmentSlot.HEAD).is(ItemTags.FREEZE_IMMUNE_WEARABLES) && !this.getItemBySlot(EquipmentSlot.CHEST).is(ItemTags.FREEZE_IMMUNE_WEARABLES) && !this.getItemBySlot(EquipmentSlot.LEGS).is(ItemTags.FREEZE_IMMUNE_WEARABLES) && !this.getItemBySlot(EquipmentSlot.FEET).is(ItemTags.FREEZE_IMMUNE_WEARABLES);
                return flag && super.canFreeze();
            }
        }
    }


}

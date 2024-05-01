package net.shadowbeast.projectshadow.enchantments;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.level.levelgen.feature.SnowAndFreezeFeature;
import net.shadowbeast.projectshadow.effect.ModEffects;

import java.beans.EventHandler;


public class IceAspectEnchantment extends Enchantment {
    protected IceAspectEnchantment(Rarity pRarity, EnchantmentCategory weapon, EquipmentSlot... pApplicableSlots) {
        super(pRarity, EnchantmentCategory.WEAPON, pApplicableSlots);
    }

    @Override
    public int getMinCost(int pEnchantmentLevel) {
        return 10 + 20 * (pEnchantmentLevel - 1);
    }

    @Override
    public int getMaxCost(int pEnchantmentLevel) {
        return super.getMinCost(pEnchantmentLevel) + 50;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean checkCompatibility(Enchantment pEnch) {
        return super.checkCompatibility(pEnch) && pEnch != Enchantments.FIRE_ASPECT;
    }

    @Override
    public void doPostAttack(LivingEntity user, Entity target, int level) {
        if (target instanceof LivingEntity) {
            ((LivingEntity) target).addEffect(new MobEffectInstance(ModEffects.FREEZE.get(), 200, level - 1));
        }
    }
}













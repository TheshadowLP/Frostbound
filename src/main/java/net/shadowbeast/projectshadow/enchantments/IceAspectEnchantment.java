package net.shadowbeast.projectshadow.enchantments;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

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
    public void doPostAttack(LivingEntity user, Entity target, int level) {
        if (target instanceof LivingEntity) {
            LivingEntity livingTarget = (LivingEntity) target;
            livingTarget.setIsInPowderSnow(true);
        }
    }
}











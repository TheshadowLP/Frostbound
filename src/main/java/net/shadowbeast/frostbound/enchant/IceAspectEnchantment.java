package net.shadowbeast.frostbound.enchant;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import net.shadowbeast.frostbound.registries.EffectsRegistry;
import org.jetbrains.annotations.NotNull;
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
        return 2;
    }

    @Override
    public boolean checkCompatibility(@NotNull Enchantment pEnch) {
        return super.checkCompatibility(pEnch) && pEnch != Enchantments.FIRE_ASPECT;
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return stack.getItem() instanceof SwordItem;
    }

    @Override
    public void doPostAttack(@NotNull LivingEntity user, @NotNull Entity target, int level) {
        if (target instanceof LivingEntity) {
            int duration = 100 * level; //TODO Rn level 1 is 6-7 seconds (dk fully) and level 2 is 10 seconds, we can always change tho
            ((LivingEntity) target).addEffect(new MobEffectInstance(EffectsRegistry.FREEZE.get(), duration, level - 1));
        }
    }
}













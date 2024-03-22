package net.shadowbeast.projectshadow.items.custom;

import com.google.common.collect.ImmutableMap;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.shadowbeast.projectshadow.enums.ArmorStats;

import java.util.Map;


public class ModArmorItem extends ArmorItem {
    public static class SteelMethods {
        public static final Map<ArmorMaterial, MobEffectInstance> MATERIAL_TO_EFFECT_MAP =
                (new ImmutableMap.Builder<ArmorMaterial, MobEffectInstance>())
                        .put(ArmorStats.STEEL, new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200,
                                1, false, false, false)).build();
        public static void onArmorTick(ItemStack stack, Level world, Player player) {
            if(!world.isClientSide()) {
                if(hasFullSuitOfArmorOn(player)) {
                    evaluateArmorEffects(player);
                }
            }
        }
        private static boolean hasFullSuitOfArmorOn(Player player) {
            ItemStack helmet = player.getInventory().getArmor(0);
            ItemStack chestplate = player.getInventory().getArmor(1);
            ItemStack leggings = player.getInventory().getArmor(2);
            ItemStack boots = player.getInventory().getArmor(3);

            return !helmet.isEmpty() && !chestplate.isEmpty()
                    && !leggings.isEmpty() && !boots.isEmpty();
        }
        private static void evaluateArmorEffects(Player player) {
            for(Map.Entry<ArmorMaterial, MobEffectInstance> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
                MobEffectInstance mapStatusEffect = entry.getValue();

                if(hasCorrectArmorOn(player)) {
                    addStatusEffectForMaterial(player, mapStatusEffect);
                }
            }
        }
        private static boolean hasCorrectArmorOn(Player player) {
            for(ItemStack armorStack : player.getInventory().armor) {
                if(!(armorStack.getItem() instanceof ArmorItem)) {
                    return false;
                }
            }
            ArmorItem helmet = ((ArmorItem) player.getInventory().getArmor(0).getItem());
            ArmorItem chestplate = ((ArmorItem) player.getInventory().getArmor(1).getItem());
            ArmorItem leggings = ((ArmorItem) player.getInventory().getArmor(2).getItem());
            ArmorItem boots = ((ArmorItem) player.getInventory().getArmor(3).getItem());

            return helmet.getMaterial() == ArmorStats.STEEL && chestplate.getMaterial() == ArmorStats.STEEL
                    && leggings.getMaterial() == ArmorStats.STEEL && boots.getMaterial() == ArmorStats.STEEL;
        }
        private static void addStatusEffectForMaterial(Player player, MobEffectInstance pEffect) {
            boolean hasPlayerEffect = player.hasEffect(pEffect.getEffect());

            if(hasCorrectArmorOn(player) && !hasPlayerEffect && !player.hasEffect(MobEffects.DAMAGE_BOOST)) {
                player.addEffect(pEffect);
            }
        }
    }
    public static class FireriteMethods {
        public static final Map<ArmorMaterial, MobEffectInstance> MATERIAL_TO_EFFECT_MAP =
                (new ImmutableMap.Builder<ArmorMaterial, MobEffectInstance>())
                        .put(ArmorStats.FIRERITE, new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200,
                                0, false, false, false)).build();
        public static void onArmorTick(ItemStack stack, Level world, Player player) {
            if(!world.isClientSide()) {
                if(hasFullSuitOfArmorOn(player)) {
                    evaluateArmorEffects(player);
                }
            }
        }
        private static boolean hasFullSuitOfArmorOn(Player player) {
            ItemStack helmet = player.getInventory().getArmor(0);
            ItemStack chestplate = player.getInventory().getArmor(1);
            ItemStack leggings = player.getInventory().getArmor(2);
            ItemStack boots = player.getInventory().getArmor(3);

            return !helmet.isEmpty() && !chestplate.isEmpty()
                    && !leggings.isEmpty() && !boots.isEmpty();
        }
        private static void evaluateArmorEffects(Player player) {
            for(Map.Entry<ArmorMaterial, MobEffectInstance> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
                MobEffectInstance mapStatusEffect = entry.getValue();

                if(hasCorrectArmorOn(player)) {
                    addStatusEffectForMaterial(player, mapStatusEffect);
                }
            }
        }
        private static boolean hasCorrectArmorOn(Player player) {
            for(ItemStack armorStack : player.getInventory().armor) {
                if(!(armorStack.getItem() instanceof ArmorItem)) {
                    return false;
                }
            }
            ArmorItem helmet = ((ArmorItem) player.getInventory().getArmor(0).getItem());
            ArmorItem chestplate = ((ArmorItem) player.getInventory().getArmor(1).getItem());
            ArmorItem leggings = ((ArmorItem) player.getInventory().getArmor(2).getItem());
            ArmorItem boots = ((ArmorItem) player.getInventory().getArmor(3).getItem());

            return helmet.getMaterial() == ArmorStats.FIRERITE && chestplate.getMaterial() == ArmorStats.FIRERITE
                    && leggings.getMaterial() == ArmorStats.FIRERITE && boots.getMaterial() == ArmorStats.FIRERITE;
        }
        private static void addStatusEffectForMaterial(Player player, MobEffectInstance pEffect) {
            boolean hasPlayerEffect = player.hasEffect(pEffect.getEffect());

            if(hasCorrectArmorOn(player) && !hasPlayerEffect) {
                player.addEffect(pEffect);
            }
        }
    }

    public ModArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }

    @SuppressWarnings("removal")
    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        if(this.material == ArmorStats.STEEL) {
            SteelMethods.onArmorTick(stack, world, player);
        } else if (this.material == ArmorStats.FIRERITE) {
            FireriteMethods.onArmorTick(stack, world, player);
        }
    }
}

package net.shadowbeast.projectshadow.items.custom;

import com.google.common.collect.ImmutableMap;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.shadowbeast.projectshadow.enums.ModArmorMaterial;

import java.util.Map;


public class ModArmorItem extends ArmorItem {
    public static final Map<ArmorMaterial, MobEffectInstance> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<ArmorMaterial, MobEffectInstance>())
                    .put(ModArmorMaterial.STEEL, new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200,
                            2, false, false, true)).build();
    public ModArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }

    @SuppressWarnings("removal")
    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        if(!world.isClientSide()) {
            if(hasFullSuitOfArmorOn(player)) {
                evaluateArmorEffects(player);
            }
        }
    }

    private void evaluateArmorEffects(Player player) {
        for(Map.Entry<ArmorMaterial, MobEffectInstance> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            ArmorMaterial mapArmorMaterial = entry.getKey();
            MobEffectInstance mapStatusEffect = entry.getValue();

            if(hasCorrectArmorOn(mapArmorMaterial, player)) {
                addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffect);
            }
        }
    }

    private void addStatusEffectForMaterial(Player player, ArmorMaterial pMaterial, MobEffectInstance pEffect) {
        boolean hasPlayerEffect = player.hasEffect(pEffect.getEffect());

        if(hasCorrectArmorOn(pMaterial, player) && !hasPlayerEffect && !player.hasEffect(MobEffects.DAMAGE_BOOST)) {
            player.addEffect(pEffect);
        }
    }

    private boolean hasFullSuitOfArmorOn(Player player) {
        ItemStack helmet = player.getInventory().getItem(0);
        ItemStack chestplate = player.getInventory().getItem(1);
        ItemStack leggings = player.getInventory().getItem(2);
        ItemStack boots = player.getInventory().getItem(3);

        return !helmet.isEmpty() && !chestplate.isEmpty()
                && !leggings.isEmpty() && !boots.isEmpty();
    }
    private boolean hasCorrectArmorOn(ArmorMaterial pMaterial, Player player) {
        for(ItemStack armorStack : player.getInventory().armor) {
            if(!(armorStack.getItem() instanceof ArmorItem)) {
                return false;
            }
        }
        ArmorItem helmet = ((ArmorItem) player.getInventory().getArmor(0).getItem());
        ArmorItem chestplate = ((ArmorItem) player.getInventory().getArmor(1).getItem());
        ArmorItem leggings = ((ArmorItem) player.getInventory().getArmor(2).getItem());
        ArmorItem boots = ((ArmorItem) player.getInventory().getArmor(3).getItem());

        return helmet.getMaterial() == pMaterial && chestplate.getMaterial() == pMaterial
                && leggings.getMaterial() == pMaterial && boots.getMaterial() == pMaterial;
    }
}

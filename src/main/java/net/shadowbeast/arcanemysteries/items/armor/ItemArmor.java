package net.shadowbeast.arcanemysteries.items.armor;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
public class ItemArmor extends ArmorItem {
    public ArmorMaterial mat;
    public final MobEffect[] supportedEffects;
    public final int[] amplifier;
    public ItemArmor(ArmorMaterial materialIn, Type slot) {
        super(materialIn, slot, new Properties());
        mat = materialIn;
        supportedEffects = null;
        amplifier = null;
    }
    @Override public int getEnchantmentValue() {return mat.getEnchantmentValue();}
}

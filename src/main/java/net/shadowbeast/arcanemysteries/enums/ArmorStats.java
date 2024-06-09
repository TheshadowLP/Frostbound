package net.shadowbeast.arcanemysteries.enums;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

import static net.minecraft.sounds.SoundEvents.*;
public enum ArmorStats implements ArmorMaterial {
    SILVER(1, 3, 5 , 2, 0, 0, 7, 25, ARMOR_EQUIP_GOLD, ToolStats.SILVER.getRepairIngredient()),
    COPPER(2, 5, 5, 2, 0, 0, 10, 8, ARMOR_EQUIP_IRON, ToolStats.COPPER.getRepairIngredient()),
    PLATINUM(2, 6, 7, 2, 0, 0, 10, 9, ARMOR_EQUIP_IRON, ToolStats.PLATINUM.getRepairIngredient()),
    STEEL(3, 6, 7, 3, 3, 0, 11, 9, ARMOR_EQUIP_IRON, ToolStats.STEEL.getRepairIngredient()),
    TITANIUM(3, 6, 7, 3, 0, 0, 10, 9, ARMOR_EQUIP_IRON, ToolStats.TITANIUM.getRepairIngredient()),
    LUMINITE(3, 6, 8, 3, 0, 0, 33, 11, ARMOR_EQUIP_DIAMOND, ToolStats.LUMINITE.getRepairIngredient()),
    AQUANIUM(3, 6, 8, 3, 0, 0, 33, 12, ARMOR_EQUIP_DIAMOND, ToolStats.AQUANIUM.getRepairIngredient()),
    FROZEN(3, 6, 8, 3, 2, 0.1F, 33, 13, ARMOR_EQUIP_DIAMOND, ToolStats.FROZEN.getRepairIngredient()),
    FIRERITE(3, 6, 8, 3, 2, 0.1F, 33, 13, ARMOR_EQUIP_DIAMOND, ToolStats.FIRERITE.getRepairIngredient()),
    ENDERIUM(3, 6, 8, 3, 3, 0.1F, 37, 15, ARMOR_EQUIP_NETHERITE, ToolStats.ENDERIUM.getRepairIngredient());

    final String textureLocation, name;
    final SoundEvent equipSound;
    final Ingredient repairIngredient;
    final int headArmor, chestArmor, legsArmor, feetArmor, durability, enchantability;
    final float toughness, knockbackResistance;
    ArmorStats(int headArmor, int chestArmor, int legsArmor, int feetArmor, float toughness, float resistance, int durability, int enchantability, SoundEvent equipSound, @Nullable Ingredient repairIngredient) {
        this(null, null, headArmor, chestArmor, legsArmor, feetArmor, toughness, resistance, durability, enchantability, equipSound, repairIngredient);
    }
    ArmorStats(@Nullable String customTextureLocation, @Nullable String name, int headArmor, int chestArmor, int legsArmor, int feetArmor, float toughness, float resistance, int durability, int enchantability, @Nullable SoundEvent equipSound, @Nullable Ingredient repairIngredient) {
        String nonnullName = name == null ? toString().toLowerCase() : name;
        textureLocation = customTextureLocation == null ? "ps_" + nonnullName + "armor" : customTextureLocation;
        this.name = ArcaneMysteries.MOD_ID + ":" + nonnullName;
        this.headArmor = headArmor;
        this.chestArmor = chestArmor;
        this.legsArmor = legsArmor;
        this.feetArmor = feetArmor;
        this.toughness = toughness;
        knockbackResistance = resistance;
        this.durability = durability;
        this.enchantability = enchantability;
        this.equipSound = equipSound == null ? ARMOR_EQUIP_GENERIC : equipSound;
        this.repairIngredient = repairIngredient == null ? Ingredient.EMPTY : repairIngredient;
    }
    @Override public int getDurabilityForType(ArmorItem.@NotNull Type type) {
        if(type == ArmorItem.Type.HELMET) {
            return durability * 11;
        } else if(type == ArmorItem.Type.CHESTPLATE) {
            return durability * 16;
        } else if(type == ArmorItem.Type.LEGGINGS) {
            return durability * 15;
        } else return durability * 13;
    }
    @Override public int getEnchantmentValue() {return enchantability;}
    @Override public @NotNull SoundEvent getEquipSound() {return equipSound;}
    @Override public @NotNull Ingredient getRepairIngredient() {return repairIngredient;}
    @Override public @NotNull String getName() {return name;}
    @Override public float getToughness() {return toughness;}
    @Override public float getKnockbackResistance() {return knockbackResistance;}
    @Override public int getDefenseForType(ArmorItem.Type type) {
        return switch(type) {
            case HELMET -> headArmor;
            case CHESTPLATE -> chestArmor;
            case LEGGINGS -> legsArmor;
            default -> feetArmor;
        };
    }
}

package net.shadowbeast.arcanemysteries.enums;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;

@MethodsReturnNonnullByDefault
public enum ArmorStats implements ArmorMaterial {
    STEEL("steel", 15, new int[] {3, 6, 7, 3}, ToolStats.STEEL.getEnchantmentValue(),
            SoundEvents.ARMOR_EQUIP_IRON, 3.0f,
            0.0f, ToolStats.STEEL.getRepairIngredient()),
    FIRERITE("firerite", 33, new int[] {3, 6, 8, 3}, ToolStats.FIRERITE.getEnchantmentValue(),
            SoundEvents.ARMOR_EQUIP_IRON, 2.0f,
            0.1f, ToolStats.FIRERITE.getRepairIngredient()),
    FROZEN("frozen", 33, new int[] {3, 6, 8, 3}, ToolStats.FROZEN.getEnchantmentValue(),
            SoundEvents.ARMOR_EQUIP_IRON, 2.0f,
            0.1f, ToolStats.FROZEN.getRepairIngredient()),
    AQUANIUM("aquanium", 33, new int[] {3, 6, 8, 3}, ToolStats.AQUANIUM.getEnchantmentValue(),
            SoundEvents.ARMOR_EQUIP_IRON, 2.0f,
            0.1f, ToolStats.AQUANIUM.getRepairIngredient()),
    COPPER("copper", 15, new int[] {2, 5, 5, 2}, ToolStats.COPPER.getEnchantmentValue(),
            SoundEvents.ARMOR_EQUIP_IRON, 0.0f,
            0.0f, ToolStats.COPPER.getRepairIngredient()),
    ENDERIUM("enderium", 37, new int[] {3, 6, 8, 3}, ToolStats.ENDERIUM.getEnchantmentValue(),
    SoundEvents.ARMOR_EQUIP_NETHERITE, 3.0f,
            0.1f, ToolStats.ENDERIUM.getRepairIngredient()),
    LUMINITE("luminite", 33, new int[] {3, 6, 8, 3}, ToolStats.LUMINITE.getEnchantmentValue(),
            SoundEvents.ARMOR_EQUIP_IRON, 2.0f,
            0.1f, ToolStats.LUMINITE.getRepairIngredient()),
    PLATINUM("platinum", 15, new int[] {2, 6, 7, 2}, ToolStats.PLATINUM.getEnchantmentValue(),
    SoundEvents.ARMOR_EQUIP_IRON, 0f,
            0f, ToolStats.PLATINUM.getRepairIngredient()),
    SILVER("silver", 7, new int[] {1, 3, 5, 2}, ToolStats.SILVER.getEnchantmentValue(),
            SoundEvents.ARMOR_EQUIP_GOLD, 0f,
            0f, ToolStats.SILVER.getRepairIngredient()),
    TITANIUM("titanium", 15, new int[] {3, 6, 7, 3}, ToolStats.TITANIUM.getEnchantmentValue(),
            SoundEvents.ARMOR_EQUIP_GOLD, 0f,
            0f, ToolStats.TITANIUM.getRepairIngredient());
    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectAmounts;
    private final int enchantmentValue;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Ingredient repairIngredient;
    private static final int[] BASE_DURABILITY = {11, 16, 15, 13};
    ArmorStats(String name, int durabilityMultiplier, int[] protectAmounts, int enchantmentValue,
                     SoundEvent equipSound, float toughness, float knockbackResistance,
                     Ingredient repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectAmounts = protectAmounts;
        this.enchantmentValue = enchantmentValue;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }
    @Override
    public int getDurabilityForType(ArmorItem.Type pType) {
        return BASE_DURABILITY[pType.ordinal()] * this.durabilityMultiplier;
    }
    @Override
    public int getDefenseForType(ArmorItem.Type pType) {
        return this.protectAmounts[pType.ordinal()];
    }
    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }
    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }
    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient;
    }
    @Override
    public String getName() {
        return ArcaneMysteries.MOD_ID + ":" + this.name;
    }
    @Override
    public float getToughness() {
        return this.toughness;
    }
    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}

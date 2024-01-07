package net.shadowbeast.projectshadow.enums;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.ForgeRegistries;
import net.shadowbeast.projectshadow.ProjectShadow;

public enum ToolStats implements Tier {

    STEEL(2, 826, 6.0F, 2.0F, 15, "steel_ingot"),
    PLATINUM(2, 510, 6.0F, 2.0F, 14, "platinum_ingot"),
    TITANIUM(2, 1120, 6.0F, 2.0F, 15, "titanium_ingot"),
    SILVER(0, 210, 12.0F, 2.0F, 20, "silver_ingot"),
    ENDERIUM(5, 2530, 10.0F, 5.0F, 15, "enderium_ingot"),
    COPPER(2, 185, 8.0F, 2.0F, 15, "copper_ingot"),
    LUMINITE(0, 210, 12.0F, 2.0F, 20, "luminite_ingot"), //TODO
    AQUANIUM(0, 210, 12.0F, 2.0F, 20, "aquanium_ingot"); //TODO

    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final Ingredient repairMaterial;

    ToolStats(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability) {
        this(harvestLevel, maxUses, efficiency, attackDamage, enchantability, Items.AIR);
    }
    ToolStats(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, String repairMaterial) {
        this(harvestLevel, maxUses, efficiency, attackDamage, enchantability, ForgeRegistries.ITEMS.getValue(new ResourceLocation(ProjectShadow.MOD_ID, repairMaterial)));
    }
    ToolStats(int harvestLevelIn, int maxUsesIn, float efficiencyIn, float attackDamageIn, int enchantabilityIn, Item repairMaterialIn)
    {
        this.harvestLevel = harvestLevelIn;
        this.maxUses = maxUsesIn;
        this.efficiency = efficiencyIn;
        this.attackDamage = attackDamageIn;
        this.enchantability = enchantabilityIn;
        this.repairMaterial = Ingredient.of(repairMaterialIn);
    }

    @Override
    public int getUses() {
        return maxUses;
    }

    @Override
    public float getSpeed() {
        return efficiency;
    }

    @Override
    public float getAttackDamageBonus() {
        return attackDamage;
    }

    @Override
    public int getLevel() {
        return harvestLevel;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairMaterial;
    }
}
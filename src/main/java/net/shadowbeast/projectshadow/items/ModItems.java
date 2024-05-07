package net.shadowbeast.projectshadow.items;

import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.*;
import net.shadowbeast.projectshadow.ProjectShadow;
import net.shadowbeast.projectshadow.blocks.ModBlocks;
import net.shadowbeast.projectshadow.entity.ModEntities;
import net.shadowbeast.projectshadow.entity.custom.ModBoatEntity;
import net.shadowbeast.projectshadow.enums.ToolStats;
import net.shadowbeast.projectshadow.food.ModFood;
import net.shadowbeast.projectshadow.items.custom.*;
import net.shadowbeast.projectshadow.items.custom.armor.*;


@SuppressWarnings("unused")
public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ProjectShadow.MOD_ID);

    //FOOD
    public static final RegistryObject<Item> MILK_BOTTLE = ITEMS.register("milk_bottle", () -> new MilkBottle(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> STACKED_BAKED_POTATO = ITEMS.register("stacked_baked_potato", () -> new Item(new Item.Properties().food(ModFood.STACKED_BAKED_POTATO)));
    public static final RegistryObject<Item> STACKED_POTATO = ITEMS.register("stacked_potato", () -> new Item(new Item.Properties().food(ModFood.STACKED_POTATO)));
    public static final RegistryObject<Item> KOHLRABI = ITEMS.register("kohlrabi", () -> new Item(new Item.Properties().food(ModFood.KOHLRABI)));
    public static final RegistryObject<Item> PEPPER = ITEMS.register("pepper", () -> new Item(new Item.Properties().food(ModFood.PEPPER)));

    //SEEDS
    public static final RegistryObject<Item> KOHLRABI_SEEDS = ITEMS.register("kohlrabi_seeds", () -> new ItemNameBlockItem(ModBlocks.KOHLRABI_CROP.get(), new Item.Properties()));
    public static final RegistryObject<Item> PEPPER_SEEDS = ITEMS.register("pepper_seeds", () -> new ItemNameBlockItem(ModBlocks.PEPPER_CROP.get(), new Item.Properties()));

    //INGOTS
    public static final RegistryObject<Item> AQUANIUM_INGOT = ITEMS.register("aquanium_ingot", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ENDER_INGOT = ITEMS.register("ender_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ENDERIUM_INGOT = ITEMS.register("enderium_ingot", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_INGOT = ITEMS.register("platinum_ingot", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LUMINITE_INGOT = ITEMS.register("luminite_ingot", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SILVER_INGOT = ITEMS.register("silver_ingot", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM_INGOT = ITEMS.register("titanium_ingot", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ALUMINIUM_INGOT = ITEMS.register("aluminium_ingot", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> END_GOLD_INGOT = ITEMS.register("end_gold_ingot", ()-> new Item(new Item.Properties()));

    //RAW INGOTS
    public static final RegistryObject<Item> RAW_ENDERIUM = ITEMS.register("raw_enderium", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_LUMINITE = ITEMS.register("raw_luminite", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_PLATINUM = ITEMS.register("raw_platinum", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_SILVER = ITEMS.register("raw_silver", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_TITANIUM = ITEMS.register("raw_titanium", ()-> new Item(new Item.Properties()));

    //SHARDS
    public static final RegistryObject<Item> AQUANIUM_SHARD = ITEMS.register("aquanium_shard", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_SHARD = ITEMS.register("diamond_shard", ()-> new Item(new Item.Properties()));

    //STICKS
    public static final RegistryObject<Item> GOLD_STICK = ITEMS.register("gold_stick", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SILVER_STICK = ITEMS.register("silver_stick", ()-> new Item(new Item.Properties()));

    //GEMS & MORE
    public static final RegistryObject<Item> FIRERITE_GEM = ITEMS.register("firerite_gem", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FROZEN_GEM = ITEMS.register("frozen_gem", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RUBY = ITEMS.register("ruby", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SULFUR = ITEMS.register("sulfur", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BEDROCK_CHUNK = ITEMS.register("bedrock_chunk", ()-> new Item(new Item.Properties()));

    //TECH STUFF
    public static final RegistryObject<Item> IRON_PLATE = ITEMS.register("iron_plate", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MOLTEN_SULFUR_BUCKET = ITEMS.register("molten_sulfur_bucket", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> IRON_SAW_BLADE = ITEMS.register("iron_saw_blade", () -> new SawbladeItem(28));
    public static final RegistryObject<Item> PLATINUM_SAW_BLADE = ITEMS.register("platinum_saw_blade", () -> new SawbladeItem(43)); //Increments of 64 change if unbalanced
    public static final RegistryObject<Item> TITANIUM_SAW_BLADE = ITEMS.register("titanium_saw_blade", () -> new SawbladeItem(36)); //Increments of 64 change if unbalanced
    public static final RegistryObject<Item> DIAMOND_SAW_BLADE = ITEMS.register("diamond_saw_blade", () -> new SawbladeItem(57)); //Increments of 64 change if unbalanced
    public static final RegistryObject<Item> STRONGHOLD_COMPASS = ITEMS.register("stronghold_compass", () -> new StrongholdCompass(new Item.Properties().stacksTo(1)));

    //ENDER ITEMS
    public static final RegistryObject<Item> ENDER_ARCH = ITEMS.register("ender_arch", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ENDER_ARCH_FRAGMENT = ITEMS.register("ender_arch_fragment", () -> new Item(new Item.Properties()));

    //POWDER
    //public static final RegistryObject<Item> AQUANIUM_POWDER = ITEMS.register("aquanium_powder", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COPPER_POWDER = ITEMS.register("copper_powder", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_POWDER = ITEMS.register("diamond_powder", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ENDER_PEARL_POWDER = ITEMS.register("ender_pearl_powder", ()-> new Item(new Item.Properties()));
    //public static final RegistryObject<Item> ENDERIUM_POWDER = ITEMS.register("enderium_powder", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GOLD_POWDER = ITEMS.register("gold_powder", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> IRON_POWDER = ITEMS.register("iron_powder", ()-> new Item(new Item.Properties()));
    //public static final RegistryObject<Item> LUMINITE_POWDER = ITEMS.register("luminite_powder", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_POWDER = ITEMS.register("platinum_powder", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SILVER_POWDER = ITEMS.register("silver_powder", ()-> new Item(new Item.Properties()));
    //public static final RegistryObject<Item> STEEL_POWDER = ITEMS.register("steel_powder", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SULFUR_POWDER = ITEMS.register("sulfur_powder", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM_POWDER = ITEMS.register("titanium_powder", ()-> new Item(new Item.Properties()));

    //NUGGETS
    public static final RegistryObject<Item> AQUANIUM_NUGGET = ITEMS.register("aquanium_nugget", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COPPER_NUGGET = ITEMS.register("copper_nugget", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ENDERIUM_NUGGET = ITEMS.register("enderium_nugget", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LUMINITE_NUGGET = ITEMS.register("luminite_nugget", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_NUGGET= ITEMS.register("platinum_nugget", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SILVER_NUGGET = ITEMS.register("silver_nugget", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STEEL_NUGGET = ITEMS.register("steel_nugget", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM_NUGGET = ITEMS.register("titanium_nugget", ()-> new Item(new Item.Properties()));

    //SHIELDS
    public static final RegistryObject<Item> AQUANIUM_SHIELD = ITEMS.register("aquanium_shield", () -> new ShieldItem(new Item.Properties().durability(1105)));
    public static final RegistryObject<Item> COPPER_SHIELD = ITEMS.register("copper_shield", () -> new ShieldItem(new Item.Properties().durability(185)));
    public static final RegistryObject<Item> ENDERIUM_SHIELD = ITEMS.register("enderium_shield", () -> new ShieldItem(new Item.Properties().durability(1265)));
    public static final RegistryObject<Item> FIRERITE_SHIELD = ITEMS.register("firerite_shield", () -> new ShieldItem(new Item.Properties().durability(890)));
    public static final RegistryObject<Item> FROZEN_SHIELD = ITEMS.register("frozen_shield", () -> new ShieldItem(new Item.Properties().durability(910)));
    public static final RegistryObject<Item> GOLD_SHIELD = ITEMS.register("gold_shield", () -> new ShieldItem(new Item.Properties().durability(220)));
    public static final RegistryObject<Item> LUMINITE_SHIELD = ITEMS.register("luminite_shield", () -> new ShieldItem(new Item.Properties().durability(860)));
    public static final RegistryObject<Item> PLATINUM_SHIELD = ITEMS.register("platinum_shield", () -> new ShieldItem(new Item.Properties().durability(255)));
    public static final RegistryObject<Item> SILVER_SHIELD = ITEMS.register("silver_shield", () -> new ShieldItem(new Item.Properties().durability(210)));
    public static final RegistryObject<Item> STEEL_SHIELD = ITEMS.register("steel_shield", () -> new ShieldItem(new Item.Properties().durability(500)));
    public static final RegistryObject<Item> TITANIUM_SHIELD = ITEMS.register("titanium_shield", () -> new ShieldItem(new Item.Properties().durability(560)));

    //SWORDS
    public static final RegistryObject<Item> AQUANIUM_SWORD = ITEMS.register("aquanium_sword", ()-> new SwordItem(ToolStats.AQUANIUM,3,-2.4F, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_SWORD = ITEMS.register("copper_sword", ()-> new SwordItem(ToolStats.COPPER,3,-2.4F, new Item.Properties()));
    public static final RegistryObject<Item> ENDERIUM_SWORD = ITEMS.register("enderium_sword", ()-> new SwordItem(ToolStats.ENDERIUM,3,-2.6F, new Item.Properties()));
    public static final RegistryObject<Item> LUMINITE_SWORD = ITEMS.register("luminite_sword", ()-> new SwordItem(ToolStats.LUMINITE,3,-2.4F, new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_SWORD = ITEMS.register("platinum_sword", ()-> new SwordItem(ToolStats.PLATINUM,3,-2.4F, new Item.Properties()));
    public static final RegistryObject<Item> SILVER_SWORD = ITEMS.register("silver_sword", ()-> new SwordItem(ToolStats.SILVER,1,-2.4F, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_SWORD = ITEMS.register("steel_sword", ()-> new SwordItem(ToolStats.STEEL,3,-2.6F, new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM_SWORD = ITEMS.register("titanium_sword", ()-> new SwordItem(ToolStats.TITANIUM,4,-2.4F, new Item.Properties()));
    public static final RegistryObject<Item> FIRERITE_SWORD = ITEMS.register("firerite_sword", () -> new SwordItem(ToolStats.FIRERITE, 3, -2.4F, new Item.Properties()));
    public static final RegistryObject<Item> FROZEN_SWORD = ITEMS.register("frozen_sword", () -> new SwordItem(ToolStats.FROZEN, 3, -2.4F, new Item.Properties()));
    public static final RegistryObject<Item> BEDROCK_SWORD = ITEMS.register("bedrock_sword", ()-> new SwordItem(BedrockToolTier.BEDROCK,3,-2.6F, new Item.Properties()));

    //AXES
    public static final RegistryObject<Item> AQUANIUM_AXE = ITEMS.register("aquanium_axe", ()-> new AxeItem(ToolStats.AQUANIUM,5.0F,-3.0F, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_AXE = ITEMS.register("copper_axe", ()-> new AxeItem(ToolStats.COPPER,6.0F,-3.1F, new Item.Properties()));
    public static final RegistryObject<Item> ENDERIUM_AXE = ITEMS.register("enderium_axe", ()-> new AxeItem(ToolStats.ENDERIUM,5.0F,-3.2F, new Item.Properties()));
    public static final RegistryObject<Item> LUMINITE_AXE = ITEMS.register("luminite_axe", ()-> new AxeItem(ToolStats.LUMINITE,5.0F,-3.0F, new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_AXE = ITEMS.register("platinum_axe", ()-> new AxeItem(ToolStats.PLATINUM,6F,-3.1F, new Item.Properties()));
    public static final RegistryObject<Item> SILVER_AXE = ITEMS.register("silver_axe", ()-> new AxeItem(ToolStats.SILVER,5.0F,-3.0F, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_AXE = ITEMS.register("steel_axe", ()-> new AxeItem(ToolStats.STEEL,6F,-3.2F, new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM_AXE = ITEMS.register("titanium_axe", ()-> new AxeItem(ToolStats.TITANIUM,6.0F,-3.0F, new Item.Properties()));
    public static final RegistryObject<Item> FIRERITE_AXE = ITEMS.register("firerite_axe", ()-> new AxeItem(ToolStats.FIRERITE,5.0F,-2.8F, new Item.Properties()));
    public static final RegistryObject<Item> FROZEN_AXE = ITEMS.register("frozen_axe", ()-> new AxeItem(ToolStats.FROZEN,5.0F,-2.8F, new Item.Properties()));
    public static final RegistryObject<Item> BEDROCK_AXE = ITEMS.register("bedrock_axe", ()-> new AxeItem(BedrockToolTier.BEDROCK,5.0F,-3.2F, new Item.Properties()));

    //PICKAXES
    public static final RegistryObject<Item> AQUANIUM_PICKAXE = ITEMS.register("aquanium_pickaxe", ()-> new PickaxeItem(ToolStats.AQUANIUM,1,-2.8F, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_PICKAXE = ITEMS.register("copper_pickaxe", ()-> new PickaxeItem(ToolStats.COPPER,1,-2.8F, new Item.Properties()));
    public static final RegistryObject<Item> ENDERIUM_PICKAXE = ITEMS.register("enderium_pickaxe", ()-> new PickaxeItem(ToolStats.ENDERIUM,1,-3.0F, new Item.Properties()));
    public static final RegistryObject<Item> LUMINITE_PICKAXE = ITEMS.register("luminite_pickaxe", ()-> new PickaxeItem(ToolStats.LUMINITE,1,-2.8F, new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_PICKAXE = ITEMS.register("platinum_pickaxe", ()-> new PickaxeItem(ToolStats.PLATINUM,1,-2.8F, new Item.Properties()));
    public static final RegistryObject<Item> SILVER_PICKAXE = ITEMS.register("silver_pickaxe", ()-> new PickaxeItem(ToolStats.SILVER,-1,-2.8F, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_PICKAXE = ITEMS.register("steel_pickaxe", ()-> new PickaxeItem(ToolStats.STEEL,1,-3F, new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM_PICKAXE = ITEMS.register("titanium_pickaxe", ()-> new PickaxeItem(ToolStats.TITANIUM,2,-2.8F, new Item.Properties()));
    public static final RegistryObject<Item> FIRERITE_PICKAXE = ITEMS.register("firerite_pickaxe", ()-> new PickaxeItem(ToolStats.FIRERITE,1,-2.8F, new Item.Properties()));
    public static final RegistryObject<Item> FROZEN_PICKAXE = ITEMS.register("frozen_pickaxe", ()-> new PickaxeItem(ToolStats.FROZEN,1,-2.8F, new Item.Properties()));
    public static final RegistryObject<Item> BEDROCK_PICKAXE = ITEMS.register("bedrock_pickaxe", ()-> new PickaxeItem(BedrockToolTier.BEDROCK,1,-3.0F, new Item.Properties()));

    //SHOVELS
    public static final RegistryObject<Item> AQUANIUM_SHOVEL = ITEMS.register("aquanium_shovel", ()-> new ShovelItem(ToolStats.AQUANIUM,1.5F,-3.0F, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_SHOVEL = ITEMS.register("copper_shovel", ()-> new ShovelItem(ToolStats.COPPER,1.5F,-3.0F, new Item.Properties()));
    public static final RegistryObject<Item> ENDERIUM_SHOVEL = ITEMS.register("enderium_shovel", ()-> new ShovelItem(ToolStats.ENDERIUM,1.5F,-3.2F, new Item.Properties()));
    public static final RegistryObject<Item> LUMINITE_SHOVEL = ITEMS.register("luminite_shovel", ()-> new ShovelItem(ToolStats.LUMINITE,1.5F,-3.0F, new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_SHOVEL = ITEMS.register("platinum_shovel", ()-> new ShovelItem(ToolStats.PLATINUM,1.5F,-3F, new Item.Properties()));
    public static final RegistryObject<Item> SILVER_SHOVEL = ITEMS.register("silver_shovel", ()-> new ShovelItem(ToolStats.SILVER,-0.5F,-3.0F, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_SHOVEL = ITEMS.register("steel_shovel", ()-> new ShovelItem(ToolStats.STEEL,1.5F,-3F, new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM_SHOVEL = ITEMS.register("titanium_shovel", ()-> new ShovelItem(ToolStats.TITANIUM,2.5F,-3.0F, new Item.Properties()));
    public static final RegistryObject<Item> FIRERITE_SHOVEL = ITEMS.register("firerite_shovel", ()-> new ShovelItem(ToolStats.FIRERITE,1.5F,-3.0F, new Item.Properties()));
    public static final RegistryObject<Item> FROZEN_SHOVEL = ITEMS.register("frozen_shovel", ()-> new ShovelItem(ToolStats.FROZEN,1.5F,-3.0F, new Item.Properties()));
    public static final RegistryObject<Item> BEDROCK_SHOVEL = ITEMS.register("bedrock_shovel", ()-> new ShovelItem(BedrockToolTier.BEDROCK,1.5F,-3.2F, new Item.Properties()));

    //HOES
    public static final RegistryObject<Item> AQUANIUM_HOE = ITEMS.register("aquanium_hoe", ()-> new HoeItem(ToolStats.AQUANIUM,-4,0.0F, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_HOE = ITEMS.register("copper_hoe", ()-> new HoeItem(ToolStats.COPPER,-2,-1.0F, new Item.Properties()));
    public static final RegistryObject<Item> ENDERIUM_HOE = ITEMS.register("enderium_hoe", ()-> new HoeItem(ToolStats.ENDERIUM,-4,-0.2F, new Item.Properties()));
    public static final RegistryObject<Item> LUMINITE_HOE = ITEMS.register("luminite_hoe", ()-> new HoeItem(ToolStats.LUMINITE,-3,0.0F, new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_HOE = ITEMS.register("platinum_hoe", ()-> new HoeItem(ToolStats.PLATINUM,-2,-1.0F, new Item.Properties()));
    public static final RegistryObject<Item> SILVER_HOE = ITEMS.register("silver_hoe", ()-> new HoeItem(ToolStats.SILVER,-2,-3.0F, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_HOE = ITEMS.register("steel_hoe", ()-> new HoeItem(ToolStats.STEEL,-2,-1.0F, new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM_HOE = ITEMS.register("titanium_hoe", ()-> new HoeItem(ToolStats.TITANIUM,-2,0.0F, new Item.Properties()));
    public static final RegistryObject<Item> FIRERITE_HOE = ITEMS.register("firerite_hoe", ()-> new HoeItem(ToolStats.FIRERITE,-2,0.0F, new Item.Properties()));
    public static final RegistryObject<Item> FROZEN_HOE = ITEMS.register("frozen_hoe", ()-> new HoeItem(ToolStats.FROZEN,-2,0.0F, new Item.Properties()));
    public static final RegistryObject<Item> BEDROCK_HOE = ITEMS.register("bedrock_hoe", ()-> new HoeItem(BedrockToolTier.BEDROCK,-4,-0.2F, new Item.Properties()));

    //HAMMERS
    public static final RegistryObject<Item> PLATINUM_HAMMER = ITEMS.register("platinum_hammer", ()-> new HammerItem(ToolStats.PLATINUM,6F,-3.4F, new Item.Properties().durability(1020)));
    public static final RegistryObject<Item> SILVER_HAMMER = ITEMS.register("silver_hammer", ()-> new HammerItem(ToolStats.SILVER,5.0F,-3.4F, new Item.Properties().durability(420)));
    public static final RegistryObject<Item> STEEL_HAMMER = ITEMS.register("steel_hammer", ()-> new HammerItem(ToolStats.STEEL,6F,-3.4f, new Item.Properties().durability(1654)));
    public static final RegistryObject<Item> TITANIUM_HAMMER = ITEMS.register("titanium_hammer", ()-> new HammerItem(ToolStats.TITANIUM,6F,-3.4F, new Item.Properties().durability(2240)));

    //STEEL ARMOR
    public static final RegistryObject<Item> STEEL_HELMET = ITEMS.register("steel_helmet", () -> SteelArmor.getInstance(ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> STEEL_CHESTPLATE = ITEMS.register("steel_chestplate", () -> SteelArmor.getInstance(ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> STEEL_LEGGINGS = ITEMS.register("steel_leggings",() -> SteelArmor.getInstance(ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> STEEL_BOOTS = ITEMS.register("steel_boots", () -> SteelArmor.getInstance(ArmorItem.Type.BOOTS));

    //FIRERITE ARMOR
    public static final RegistryObject<Item> FIRERITE_HELMET = ITEMS.register("firerite_helmet",() -> FireriteArmor.getInstance(ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> FIRERITE_CHESTPLATE = ITEMS.register("firerite_chestplate", () -> FireriteArmor.getInstance(ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> FIRERITE_LEGGINGS = ITEMS.register("firerite_leggings", () -> FireriteArmor.getInstance(ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> FIRERITE_BOOTS = ITEMS.register("firerite_boots", () -> FireriteArmor.getInstance(ArmorItem.Type.BOOTS));

    //FROZEN ARMOR
    public static final RegistryObject<Item> FROZEN_HELMET = ITEMS.register("frozen_helmet", () -> FrozenArmor.getInstance(ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> FROZEN_CHESTPLATE = ITEMS.register("frozen_chestplate", () -> FrozenArmor.getInstance(ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> FROZEN_LEGGINGS = ITEMS.register("frozen_leggings", () -> FrozenArmor.getInstance(ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> FROZEN_BOOTS = ITEMS.register("frozen_boots", () -> FrozenArmor.getInstance(ArmorItem.Type.BOOTS));

    //AQUANIUM ARMOR
    public static final RegistryObject<Item> AQUANIUM_HELMET = ITEMS.register("aquanium_helmet", () -> AquaniumArmor.getInstance(ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> AQUANIUM_CHESTPLATE = ITEMS.register("aquanium_chestplate", () -> AquaniumArmor.getInstance(ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> AQUANIUM_LEGGINGS = ITEMS.register("aquanium_leggings", () -> AquaniumArmor.getInstance(ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> AQUANIUM_BOOTS = ITEMS.register("aquanium_boots", () -> AquaniumArmor.getInstance(ArmorItem.Type.BOOTS));

    //COPPER ARMOR
    public static final RegistryObject<Item> COPPER_HELMET = ITEMS.register("copper_helmet", () -> CopperArmor.getInstance(ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> COPPER_CHESTPLATE = ITEMS.register("copper_chestplate", () -> CopperArmor.getInstance(ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> COPPER_LEGGINGS = ITEMS.register("copper_leggings", () -> CopperArmor.getInstance(ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> COPPER_BOOTS = ITEMS.register("copper_boots", () -> CopperArmor.getInstance(ArmorItem.Type.BOOTS));

    //ENDERIUM ARMOR
    public static final RegistryObject<Item> ENDERIUM_HELMET = ITEMS.register("enderium_helmet", () -> EnderiumArmor.getInstance(ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> ENDERIUM_CHESTPLATE = ITEMS.register("enderium_chestplate", () -> EnderiumArmor.getInstance(ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> ENDERIUM_LEGGINGS = ITEMS.register("enderium_leggings", () -> EnderiumArmor.getInstance(ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> ENDERIUM_BOOTS = ITEMS.register("enderium_boots", () -> EnderiumArmor.getInstance(ArmorItem.Type.BOOTS));

    //LUMINITE ARMOR
    public static final RegistryObject<Item> LUMINITE_HELMET = ITEMS.register("luminite_helmet", () -> LuminiteArmor.getInstance(ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> LUMINITE_CHESTPLATE = ITEMS.register("luminite_chestplate", () -> LuminiteArmor.getInstance(ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> LUMINITE_LEGGINGS = ITEMS.register("luminite_leggings", () -> LuminiteArmor.getInstance(ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> LUMINITE_BOOTS = ITEMS.register("luminite_boots", () -> LuminiteArmor.getInstance(ArmorItem.Type.BOOTS));

    //PLATINUM ARMOR
    public static final RegistryObject<Item> PLATINUM_HELMET = ITEMS.register("platinum_helmet", () -> PlatinumArmor.getInstance(ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> PLATINUM_CHESTPLATE = ITEMS.register("platinum_chestplate", () -> PlatinumArmor.getInstance(ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> PLATINUM_LEGGINGS = ITEMS.register("platinum_leggings", () -> PlatinumArmor.getInstance(ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> PLATINUM_BOOTS = ITEMS.register("platinum_boots", () -> PlatinumArmor.getInstance(ArmorItem.Type.BOOTS));

    // SILVER ARMOR

    public static final RegistryObject<Item> SILVER_HELMET = ITEMS.register("silver_helmet", () -> SilverArmor.getInstance(ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> SILVER_CHESTPLATE = ITEMS.register("silver_chestplate", () -> SilverArmor.getInstance(ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> SILVER_LEGGINGS = ITEMS.register("silver_leggings", () -> SilverArmor.getInstance(ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> SILVER_BOOTS = ITEMS.register("silver_boots", () -> SilverArmor.getInstance(ArmorItem.Type.BOOTS));

    //
    public static final RegistryObject<Item> TITANIUM_HELMET = ITEMS.register("titanium_helmet", () -> TitaniumArmor.getInstance(ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> TITANIUM_CHESTPLATE = ITEMS.register("titanium_chestplate", () -> TitaniumArmor.getInstance(ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> TITANIUM_LEGGINGS = ITEMS.register("titanium_leggings", () -> TitaniumArmor.getInstance(ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> TITANIUM_BOOTS = ITEMS.register("titanium_boots", () -> TitaniumArmor.getInstance(ArmorItem.Type.BOOTS));

    //ADVANCED ITEMS
    public static final RegistryObject<Item> HEAL_STAFF = ITEMS.register("heal_staff", ()-> new HealStaffItem(new Item.Properties().durability(10)));
    public static final RegistryObject<Item> LEVITATION_STAFF = ITEMS.register("levitation_staff", ()-> new LevitationStaffItem(new Item.Properties().durability(18)));
    public static final RegistryObject<Item> TELEPORTATION_STAFF = ITEMS.register("teleportation_staff", () -> new TeleportationStaffItem(new Item.Properties().durability(8)));

    //PROJECTILES
    public static final RegistryObject<Item> MUD_BALL = ITEMS.register("mud_ball", () -> new MudItem(new Item.Properties()));

    //SIGNS
    public static final RegistryObject<Item> FROZEN_SIGN = ITEMS.register("frozen_sign", () -> new SignItem(new Item.Properties().stacksTo(16), ModBlocks.FROZEN_SIGN.get(), ModBlocks.FROZEN_WALL_SIGN.get()));
    public static final RegistryObject<Item> FROZEN_HANGING_SIGN = ITEMS.register("frozen_hanging_sign", () -> new HangingSignItem(ModBlocks.FROZEN_HANGING_SIGN.get(), ModBlocks.FROZEN_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));

    //BOATS
    public static final RegistryObject<Item> FROZEN_BOAT = ITEMS.register("frozen_boat", () -> new ModBoatItem(false, ModBoatEntity.Type.FROZEN, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> FROZEN_CHEST_BOAT = ITEMS.register("frozen_chest_boat", () -> new ModBoatItem(true, ModBoatEntity.Type.FROZEN, new Item.Properties().stacksTo(1)));

    //SPAWN EGGS
    public static final RegistryObject<ForgeSpawnEggItem> DUNGEON_ICE_SPAWN_EGG = ITEMS.register("dungeon_ice_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.DUNGEON_ICE, 0x34dbeb, 0xFFFFFF, new Item.Properties()));

    //MISC
    public static final RegistryObject<Item> ICE_BEAM = ITEMS.register("ice_beam",() -> new IceBeam(new Item.Properties()));
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}


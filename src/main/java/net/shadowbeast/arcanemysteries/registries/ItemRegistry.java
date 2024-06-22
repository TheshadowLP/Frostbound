package net.shadowbeast.arcanemysteries.registries;

import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.shadowbeast.arcanemysteries.entities.boats.EntityBoat;
import net.shadowbeast.arcanemysteries.entities.projectile.IceBeam;
import net.shadowbeast.arcanemysteries.enums.ArmorStats;
import net.shadowbeast.arcanemysteries.enums.ToolStats;
import net.shadowbeast.arcanemysteries.fluid.FluidsMod;
import net.shadowbeast.arcanemysteries.items.*;
import net.shadowbeast.arcanemysteries.items.armor.*;
import net.shadowbeast.arcanemysteries.items.staffs.ItemHealStaff;
import net.shadowbeast.arcanemysteries.items.staffs.ItemLevitationStaff;
import net.shadowbeast.arcanemysteries.items.staffs.ItemTeleportationStaff;
import net.shadowbeast.arcanemysteries.items.tools.*;

import java.util.function.Supplier;

import static net.shadowbeast.arcanemysteries.ArcaneMysteries.MOD_ID;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    public static final RegistryObject<Item>
            //FOOD
            KOHLRABI = registerItem("kohlrabi", () -> new ItemModFood(FoodListRegistry.KOHLRABI)),
            RAW_YAK_MEAT = registerItem("raw_yak_meat", ()-> new ItemModFood(FoodListRegistry.RAW_YAK_MEAT)),
            COOKED_YAK_MEAT = registerItem("cooked_yak_meat", ()-> new ItemModFood(FoodListRegistry.COOKED_YAK_MEAT)),
            PEPPER = registerItem("pepper", () -> new ItemModFood(FoodListRegistry.PEPPER)),
            STACKED_POTATO = registerItem("stacked_potato", () -> new ItemModFood(FoodListRegistry.STACKED_POTATO)),
            STACKED_BAKED_POTATO = registerItem("stacked_baked_potato", () -> new ItemModFood(FoodListRegistry.STACKED_BAKED_POTATO)),
            MILK_BOTTLE = registerItem("milk_bottle", ItemMilkBottle::new),
            RAW_GOAT_MUTTON = registerItem("raw_goat_mutton", () -> new ItemModFood(FoodListRegistry.RAW_GOAT_MUTTON)),
            COOKED_GOAT_MUTTON = registerItem("cooked_goat_mutton", () -> new ItemModFood(FoodListRegistry.COOKED_GOAT_MUTTON)),

            //SEEDS
            KOHLRABI_SEEDS = registerItem("kohlrabi_seeds", () -> new ItemNameBlockItem(ModBlocks.KOHLRABI_CROP.get(),new Item.Properties())),
            PEPPER_SEEDS = registerItem("pepper_seeds", () -> new ItemNameBlockItem(ModBlocks.PEPPER_CROP.get(),new Item.Properties())),

            //ORES
            RAW_SILVER = registerItem("raw_silver"),
            RAW_PLATINUM = registerItem("raw_platinum"),
            RAW_TITANIUM = registerItem("raw_titanium"),
            RAW_LUMINITE = registerItem("raw_luminite"),
            RAW_ENDERIUM = registerItem("raw_enderium"),
            SULFUR = registerItem("sulfur"),
            AQUANIUM_INGOT = registerItem("aquanium_ingot"),
            ENDER_INGOT = registerItem("ender_ingot"),
            ENDERIUM_INGOT = registerItem("enderium_ingot"),
            END_GOLD_INGOT = registerItem("end_gold_ingot"),
            LUMINITE_INGOT = registerItem("luminite_ingot"),
            PLATINUM_INGOT = registerItem("platinum_ingot"),
            SILVER_INGOT = registerItem("silver_ingot"),
            STEEL_INGOT = registerItem("steel_ingot"),
            TITANIUM_INGOT = registerItem("titanium_ingot"),
            ALUMINIUM_INGOT = registerItem("aluminium_ingot"),
            AQUANIUM_NUGGET = registerItem("aquanium_nugget"),
            COPPER_NUGGET = registerItem("copper_nugget"),
            ENDERIUM_NUGGET = registerItem("enderium_nugget"),
            LUMINITE_NUGGET = registerItem("luminite_nugget"),
            PLATINUM_NUGGET = registerItem("platinum_nugget"),
            SILVER_NUGGET = registerItem("silver_nugget"),
            STEEL_NUGGET = registerItem("steel_nugget"),
            TITANIUM_NUGGET = registerItem("titanium_nugget"),

            //SHARDS
            RUBY = registerItem("ruby"),
            FIRERITE_GEM = registerItem("firerite_gem"),
            FROZEN_GEM = registerItem("frozen_gem"),
            SHARD = registerItem("aquanium_shard"),
            DIAMOND_SHARD = registerItem("diamond_shard"),
            BEDROCK_CHUNK = registerItem("bedrock_chunk"),

            //TECH STUFF
            ENDER_ARCH = registerItem("ender_arch"),
            ENDER_ARCH_FRAGMENT = registerItem("ender_arch_fragment"),
            GOLD_STICK = registerItem("gold_stick"),
            SILVER_STICK = registerItem("silver_stick"),
            IRON_PLATE = registerItem("iron_plate"),
            IRON_SAW_BLADE = registerItem("iron_saw_blade", () -> new ItemSawBlade(28)), //Divide max by 9
            PLATINUM_SAW_BLADE = registerItem("platinum_saw_blade", () -> new ItemSawBlade(43)), //Divide max by 9
            TITANIUM_SAW_BLADE = registerItem("titanium_saw_blade", () -> new ItemSawBlade(36)), //Divide max by 9
            DIAMOND_SAW_BLADE = registerItem("diamond_saw_blade", () -> new ItemSawBlade(57)), //Divide max by 9

            //DUSTS
            COPPER_DUST = registerItem("copper_dust"),
            DIAMOND_DUST = registerItem("diamond_dust"),
            ENDER_PEARL_DUST = registerItem("ender_pearl_dust"),
            GOLD_DUST = registerItem("gold_dust"),
            IRON_DUST = registerItem("iron_dust"),
            PLATINUM_DUST = registerItem("platinum_dust"),
            SILVER_DUST = registerItem("silver_dust"),
            SULFUR_DUST = registerItem("sulfur_dust"),
            TITANIUM_DUST = registerItem("titanium_dust"),

            //PROJECTILES
            MUD_BALL = registerItem("mud_ball", () -> new ItemMud(new Item.Properties())),

            //ENTITIES
            FROZEN_BOAT = registerItem("frozen_boat", () -> new ItemModBoat(false,EntityBoat.Type.FROZEN, new Item.Properties().stacksTo(1))),
            FROZEN_CHEST_BOAT = registerItem("frozen_chest_boat", () -> new ItemModBoat(true,EntityBoat.Type.FROZEN, new Item.Properties().stacksTo(1))),
            FROZEN_SIGN = registerItem("frozen_sign", () -> new SignItem(new Item.Properties().stacksTo(16), ModBlocks.FROZEN_SIGN.get(), ModBlocks.FROZEN_WALL_SIGN.get())),
            FROZEN_HANGING_SIGN = registerItem("frozen_hanging_sign", () -> new HangingSignItem(ModBlocks.FROZEN_HANGING_SIGN.get(), ModBlocks.FROZEN_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16))),

            //ADVANCED
            HEAL_STAFF = registerItem("heal_staff", ItemHealStaff::new),
            TELEPORTATION_STAFF = registerItem("teleportation_staff", ItemTeleportationStaff::new),
            LEVITATION_STAFF = registerItem("levitation_staff", ItemLevitationStaff::new),
            ENDER_ELYTRA = registerItem("ender_elytra",() -> new ElytraItem(new Item.Properties().durability(10).rarity(Rarity.RARE))),

                //HAMMERS
                PLATINUM_HAMMER = registerItem("platinum_hammer", () -> new ItemHammer(ToolStats.PLATINUM,6F,-3.4F, new Item.Properties().durability(1020))),
            SILVER_HAMMER = registerItem("silver_hammer", () -> new ItemHammer(ToolStats.SILVER,5F,-3.4F, new Item.Properties().durability(420))),
            STEEL_HAMMER = registerItem("steel_hammer", () -> new ItemHammer(ToolStats.STEEL,6F,-3.4F, new Item.Properties().durability(1654))),
            TITANIUM_HAMMER = registerItem("titanium_hammer", () -> new ItemHammer(ToolStats.TITANIUM,6F,-3.4F, new Item.Properties().durability(2240))),

            //SHIELDS
            AQUANIUM_SHIELD = registerItem("aquanium_shield", () -> new ShieldItem(new Item.Properties().durability(1105))),
            ENDERIUM_SHIELD = registerItem("enderium_shield", () -> new ShieldItem(new Item.Properties().durability(1265))),
            LUMINITE_SHIELD = registerItem("luminite_shield", () -> new ShieldItem(new Item.Properties().durability(860))),
            PLATINUM_SHIELD = registerItem("platinum_shield", () -> new ShieldItem(new Item.Properties().durability(225))),
            SILVER_SHIELD = registerItem("silver_shield", () -> new ShieldItem(new Item.Properties().durability(210))),
            STEEL_SHIELD = registerItem("steel_shield", () -> new ShieldItem(new Item.Properties().durability(500))),
            TITANIUM_SHIELD = registerItem("titanium_shield", () -> new ShieldItem(new Item.Properties().durability(560))),
            FIRERITE_SHIELD = registerItem("firerite_shield", () -> new ShieldItem(new Item.Properties().durability(890))),
            FROZEN_SHIELD = registerItem("frozen_shield", () -> new ShieldItem(new Item.Properties().durability(910))),
            COPPER_SHIELD = registerItem("copper_shield", () -> new ShieldItem(new Item.Properties().durability(185))),
            GOLD_SHIELD = registerItem("gold_shield", () -> new ShieldItem(new Item.Properties().durability(220))),

            //SWORDS
            AQUANIUM_SWORD = registerItem("aquanium_sword", () -> new ItemModSword(ToolStats.AQUANIUM)),
            ENDERIUM_SWORD = registerItem("enderium_sword", () -> new ItemModSword(ToolStats.ENDERIUM)),
            LUMINITE_SWORD = registerItem("luminite_sword", () -> new ItemModSword(ToolStats.LUMINITE)),
            PLATINUM_SWORD = registerItem("platinum_sword", () -> new ItemModSword(ToolStats.PLATINUM)),
            SILVER_SWORD = registerItem("silver_sword", () -> new ItemModSword(ToolStats.SILVER)),
            STEEL_SWORD = registerItem("steel_sword", () -> new ItemModSword(ToolStats.STEEL)),
            TITANIUM_SWORD = registerItem("titanium_sword", () -> new ItemModSword(ToolStats.TITANIUM)),
            FIRERITE_SWORD = registerItem("firerite_sword", () -> new ItemModSword(ToolStats.FIRERITE)),
            FROZEN_SWORD = registerItem("frozen_sword", () -> new ItemModSword(ToolStats.FROZEN)),
            COPPER_SWORD = registerItem("copper_sword", () -> new ItemModSword(ToolStats.COPPER)),
            BEDROCK_SWORD = registerItem("bedrock_sword", () -> new ItemModSword(ToolStats.BEDROCK)),

            //TOOLS
            AQUANIUM_SHOVEL = registerItem("aquanium_shovel", () -> new ItemModShovel(ToolStats.AQUANIUM)),
                    AQUANIUM_PICKAXE = registerItem("aquanium_pickaxe", () -> new ItemModPickaxe(ToolStats.AQUANIUM)),
                    AQUANIUM_AXE = registerItem("aquanium_axe", () -> new ItemModAxe(ToolStats.AQUANIUM, -2.9F)), //TODO SPEED AND DAMAGE MODIFIER
                    AQUANIUM_HOE = registerItem("aquanium_hoe", () -> new ItemModHoe(ToolStats.AQUANIUM, -2.9F)),

                    ENDERIUM_SHOVEL = registerItem("enderium_shovel", () -> new ItemModShovel(ToolStats.ENDERIUM)),
                    ENDERIUM_PICKAXE = registerItem("enderium_pickaxe", () -> new ItemModPickaxe(ToolStats.ENDERIUM)),
                    ENDERIUM_AXE = registerItem("enderium_axe", () -> new ItemModAxe(ToolStats.ENDERIUM, -2.9F)), //TODO SPEED AND DAMAGE MODIFIER
                    ENDERIUM_HOE = registerItem("enderium_hoe", () -> new ItemModHoe(ToolStats.ENDERIUM, -2.9F)),

            LUMINITE_SHOVEL = registerItem("luminite_shovel", () -> new ItemModShovel(ToolStats.LUMINITE)),
                    LUMINITE_PICKAXE = registerItem("luminite_pickaxe", () -> new ItemModPickaxe(ToolStats.LUMINITE)),
                    LUMINITE_AXE = registerItem("luminite_axe", () -> new ItemModAxe(ToolStats.LUMINITE, -2.9F)), //TODO SPEED AND DAMAGE MODIFIER
                    LUMINITE_HOE = registerItem("luminite_hoe", () -> new ItemModHoe(ToolStats.LUMINITE, -2.9F)),

            PLATINUM_SHOVEL = registerItem("platinum_shovel", () -> new ItemModShovel(ToolStats.PLATINUM)),
                    PLATINUM_PICKAXE = registerItem("platinum_pickaxe", () -> new ItemModPickaxe(ToolStats.PLATINUM)),
                    PLATINUM_AXE = registerItem("platinum_axe", () -> new ItemModAxe(ToolStats.PLATINUM, -2.9F)), //TODO SPEED AND DAMAGE MODIFIER
                    PLATINUM_HOE = registerItem("platinum_hoe", () -> new ItemModHoe(ToolStats.PLATINUM, -2.9F)),

            SILVER_SHOVEL = registerItem("silver_shovel", () -> new ItemModShovel(ToolStats.SILVER)),
                    SILVER_PICKAXE = registerItem("silver_pickaxe", () -> new ItemModPickaxe(ToolStats.SILVER)),
                    SILVER_AXE = registerItem("silver_axe", () -> new ItemModAxe(ToolStats.SILVER, -2.9F)), //TODO SPEED AND DAMAGE MODIFIER
                    SILVER_HOE = registerItem("silver_hoe", () -> new ItemModHoe(ToolStats.SILVER, -2.9F)),

            STEEL_SHOVEL = registerItem("steel_shovel", () -> new ItemModShovel(ToolStats.STEEL)),
                    STEEL_PICKAXE = registerItem("steel_pickaxe", () -> new ItemModPickaxe(ToolStats.STEEL)),
                    STEEL_AXE = registerItem("steel_axe", () -> new ItemModAxe(ToolStats.STEEL, -2.9F)), //TODO SPEED AND DAMAGE MODIFIER
                    STEEL_HOE = registerItem("steel_hoe", () -> new ItemModHoe(ToolStats.STEEL, -2.9F)),

            TITANIUM_SHOVEL = registerItem("titanium_shovel", () -> new ItemModShovel(ToolStats.TITANIUM)),
                    TITANIUM_PICKAXE = registerItem("titanium_pickaxe", () -> new ItemModPickaxe(ToolStats.TITANIUM)),
                    TITANIUM_AXE = registerItem("titanium_axe", () -> new ItemModAxe(ToolStats.TITANIUM, -2.9F)), //TODO SPEED AND DAMAGE MODIFIER
                    TITANIUM_HOE = registerItem("titanium_hoe", () -> new ItemModHoe(ToolStats.TITANIUM, -2.9F)),

            FIRERITE_SHOVEL = registerItem("firerite_shovel", () -> new ItemModShovel(ToolStats.FIRERITE)),
                    FIRERITE_PICKAXE = registerItem("firerite_pickaxe", () -> new ItemModPickaxe(ToolStats.FIRERITE)),
                    FIRERITE_AXE = registerItem("firerite_axe", () -> new ItemModAxe(ToolStats.FIRERITE, -2.9F)), //TODO SPEED AND DAMAGE MODIFIER
                    FIRERITE_HOE = registerItem("firerite_hoe", () -> new ItemModHoe(ToolStats.FIRERITE, -2.9F)),

            FROZEN_SHOVEL = registerItem("frozen_shovel", () -> new ItemModShovel(ToolStats.FROZEN)),
                    FROZEN_PICKAXE = registerItem("frozen_pickaxe", () -> new ItemModPickaxe(ToolStats.FROZEN)),
                    FROZEN_AXE = registerItem("frozen_axe", () -> new ItemModAxe(ToolStats.FROZEN, -2.9F)), //TODO SPEED AND DAMAGE MODIFIER
                    FROZEN_HOE = registerItem("frozen_hoe", () -> new ItemModHoe(ToolStats.FROZEN, -2.9F)),

            COPPER_SHOVEL = registerItem("copper_shovel", () -> new ItemModShovel(ToolStats.COPPER)),
                    COPPER_PICKAXE = registerItem("copper_pickaxe", () -> new ItemModPickaxe(ToolStats.COPPER)),
                    COPPER_AXE = registerItem("copper_axe", () -> new ItemModAxe(ToolStats.COPPER, -2.9F)), //TODO SPEED AND DAMAGE MODIFIER
                    COPPER_HOE = registerItem("copper_hoe", () -> new ItemModHoe(ToolStats.COPPER, -2.9F)),

            BEDROCK_SHOVEL = registerItem("bedrock_shovel", () -> new ItemModShovel(ToolStats.BEDROCK)),
                    BEDROCK_PICKAXE = registerItem("bedrock_pickaxe", () -> new ItemModPickaxe(ToolStats.BEDROCK)),
                    BEDROCK_AXE = registerItem("bedrock_axe", () -> new ItemModAxe(ToolStats.BEDROCK, -2.9F)), //TODO SPEED AND DAMAGE MODIFIER
                    BEDROCK_HOE = registerItem("bedrock_hoe", () -> new ItemModHoe(ToolStats.BEDROCK, -2.9F)),

            //ARMOR
            AQUANIUM_HELMET = registerItem("aquanium_helmet", () -> ArmorAquanium.getInstance(ArmorItem.Type.HELMET)),
                    AQUANIUM_CHESTPLATE = registerItem("aquanium_chestplate", () -> ArmorAquanium.getInstance(ArmorItem.Type.CHESTPLATE)),
                    AQUANIUM_LEGGINGS = registerItem("aquanium_leggings", () -> ArmorAquanium.getInstance(ArmorItem.Type.LEGGINGS)),
                    AQUANIUM_BOOTS = registerItem("aquanium_boots", () -> ArmorAquanium.getInstance(ArmorItem.Type.BOOTS)),

            ENDERIUM_HELMET = registerItem("enderium_helmet", () -> ArmorEnderium.getInstance(ArmorItem.Type.HELMET)),
                    ENDERIUM_CHESTPLATE = registerItem("enderium_chestplate", () -> ArmorEnderium.getInstance(ArmorItem.Type.CHESTPLATE)),
                    ENDERIUM_LEGGINGS = registerItem("enderium_leggings", () -> ArmorEnderium.getInstance(ArmorItem.Type.LEGGINGS)),
                    ENDERIUM_BOOTS = registerItem("enderium_boots", () -> ArmorEnderium.getInstance(ArmorItem.Type.BOOTS)),

            LUMINITE_HELMET = registerItem("luminite_helmet", () -> new ItemArmor(ArmorStats.LUMINITE, ArmorItem.Type.HELMET)),
                    LUMINITE_CHESTPLATE = registerItem("luminite_chestplate", () -> new ItemArmor(ArmorStats.LUMINITE, ArmorItem.Type.CHESTPLATE)),
                    LUMINITE_LEGGINGS = registerItem("luminite_leggings", () -> new ItemArmor(ArmorStats.LUMINITE, ArmorItem.Type.LEGGINGS)),
                    LUMINITE_BOOTS = registerItem("luminite_boots", () -> new ItemArmor(ArmorStats.LUMINITE, ArmorItem.Type.BOOTS)),

            PLATINUM_HELMET = registerItem("platinum_helmet", () -> new ItemArmor(ArmorStats.PLATINUM, ArmorItem.Type.HELMET)),
                    PLATINUM_CHESTPLATE = registerItem("platinum_chestplate", () -> new ItemArmor(ArmorStats.PLATINUM, ArmorItem.Type.CHESTPLATE)),
                    PLATINUM_LEGGINGS = registerItem("platinum_leggings", () -> new ItemArmor(ArmorStats.PLATINUM, ArmorItem.Type.LEGGINGS)),
                    PLATINUM_BOOTS = registerItem("platinum_boots", () -> new ItemArmor(ArmorStats.PLATINUM, ArmorItem.Type.BOOTS)),

            SILVER_HELMET = registerItem("silver_helmet", () -> new ItemArmor(ArmorStats.SILVER, ArmorItem.Type.HELMET)),
                   SILVER_CHESTPLATE = registerItem("silver_chestplate", () -> new ItemArmor(ArmorStats.SILVER, ArmorItem.Type.CHESTPLATE)),
                   SILVER_LEGGINGS = registerItem("silver_leggings", () -> new ItemArmor(ArmorStats.SILVER, ArmorItem.Type.LEGGINGS)),
                   SILVER_BOOTS = registerItem("silver_boots", () -> new ItemArmor(ArmorStats.SILVER, ArmorItem.Type.BOOTS)),

            STEEL_HELMET = registerItem("steel_helmet", () -> ArmorSteel.getInstance(ArmorItem.Type.HELMET)),
                   STEEL_CHESTPLATE = registerItem("steel_chestplate", () -> ArmorSteel.getInstance(ArmorItem.Type.CHESTPLATE)),
                   STEEL_LEGGINGS = registerItem("steel_leggings", () -> ArmorSteel.getInstance(ArmorItem.Type.LEGGINGS)),
                   STEEL_BOOTS = registerItem("steel_boots", () -> ArmorSteel.getInstance(ArmorItem.Type.BOOTS)),

            TITANIUM_HELMET = registerItem("titanium_helmet", () -> new ItemArmor(ArmorStats.TITANIUM, ArmorItem.Type.HELMET)),
                   TITANIUM_CHESTPLATE = registerItem("titanium_chestplate", () -> new ItemArmor(ArmorStats.TITANIUM, ArmorItem.Type.CHESTPLATE)),
                   TITANIUM_LEGGINGS = registerItem("titanium_leggings", () -> new ItemArmor(ArmorStats.TITANIUM, ArmorItem.Type.LEGGINGS)),
                   TITANIUM_BOOTS = registerItem("titanium_boots", () -> new ItemArmor(ArmorStats.TITANIUM, ArmorItem.Type.BOOTS)),

            FIRERITE_HELMET = registerItem("firerite_helmet", () -> ArmorFirerite.getInstance(ArmorItem.Type.HELMET)),
                   FIRERITE_CHESTPLATE = registerItem("firerite_chestplate", () -> ArmorFirerite.getInstance(ArmorItem.Type.CHESTPLATE)),
                   FIRERITE_LEGGINGS = registerItem("firerite_leggings", () -> ArmorFirerite.getInstance(ArmorItem.Type.LEGGINGS)),
                   FIRERITE_BOOTS = registerItem("firerite_boots", () -> ArmorFirerite.getInstance(ArmorItem.Type.BOOTS)),

            FROZEN_HELMET = registerItem("frozen_helmet", () -> ArmorFrozen.getInstance(ArmorItem.Type.HELMET)),
                   FROZEN_CHESTPLATE = registerItem("frozen_chestplate", () -> ArmorFrozen.getInstance(ArmorItem.Type.CHESTPLATE)),
                   FROZEN_LEGGINGS = registerItem("frozen_leggings", () -> ArmorFrozen.getInstance(ArmorItem.Type.LEGGINGS)),
                   FROZEN_BOOTS = registerItem("frozen_boots", () -> ArmorFrozen.getInstance(ArmorItem.Type.BOOTS)),

            COPPER_HELMET = registerItem("copper_helmet", () -> new ItemArmor(ArmorStats.COPPER, ArmorItem.Type.HELMET)),
                   COPPER_CHESTPLATE = registerItem("copper_chestplate", () -> new ItemArmor(ArmorStats.COPPER, ArmorItem.Type.CHESTPLATE)),
                   COPPER_LEGGINGS = registerItem("copper_leggings", () -> new ItemArmor(ArmorStats.COPPER, ArmorItem.Type.LEGGINGS)),
                   COPPER_BOOTS = registerItem("copper_boots", () -> new ItemArmor(ArmorStats.COPPER, ArmorItem.Type.BOOTS)),

            //MISC
            MOLTEN_SULFUR_BUCKET = registerItem("molten_sulfur_bucket"),  //TODO
            END_LAVA_BUCKET = registerItem("end_lava_bucket", () -> new BucketItem(FluidsMod.END_LAVA_FLUID, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1))),
            ICE_BEAM = registerItem("ice_beam", () -> new IceBeam(new Item.Properties())),
                STRONGHOLD_COMPASS = registerItem("stronghold_compass",
                        ()-> new ItemStrongholdCompass(new Item.Properties().stacksTo(1))),


            //EGGS
            DUNGEON_ICE_SPAWN_EGG = registerItem("dungeon_ice_spawn_egg", () -> new ForgeSpawnEggItem(EntityRegistry.DUNGEON_ICE, 0x34dbeb, 0xFFFFFF, new Item.Properties())),
            YAK_SPAWN_EGG = registerItem("yak_spawn_egg", () -> new ForgeSpawnEggItem(EntityRegistry.YAK, 2367000, 1183757, new Item.Properties()));

    private static RegistryObject<Item> registerItem(String registryId) {
        return ITEMS.register(registryId, ItemMod::new);
    }
    private static <T extends Item> RegistryObject<T> registerItem(String registryId, Supplier<T> item) {
        return ITEMS.register(registryId, item);
    }
}
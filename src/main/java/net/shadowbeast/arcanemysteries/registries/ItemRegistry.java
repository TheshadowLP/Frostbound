package net.shadowbeast.arcanemysteries.registries;

import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.shadowbeast.arcanemysteries.entities.boats.EntityBoat;
import net.shadowbeast.arcanemysteries.enums.ArmorStats;
import net.shadowbeast.arcanemysteries.enums.ToolStats;
import net.shadowbeast.arcanemysteries.fluid.FluidsMod;
import net.shadowbeast.arcanemysteries.items.*;
import net.shadowbeast.arcanemysteries.items.armor.*;
import net.shadowbeast.arcanemysteries.sorting.IceBeam;

import java.util.function.Supplier;

import static net.shadowbeast.arcanemysteries.ArcaneMysteries.MODID;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final RegistryObject<Item>
            //FOOD
            kohlrabi = registerItem("kohlrabi", () -> new ItemModFood(FoodListRegistry.KOHLRABI)),
            pepper = registerItem("pepper", () -> new ItemModFood(FoodListRegistry.PEPPER)),
            stacked_potato = registerItem("stacked_potato", () -> new ItemModFood(FoodListRegistry.STACKED_POTATO)),
            stacked_baked_potato = registerItem("stacked_baked_potato", () -> new ItemModFood(FoodListRegistry.STACKED_BAKED_POTATO)),
            milk_bottle = registerItem("milk_bottle", ItemMilkBottle::new),

            //SEEDS
            kohlrabi_seeds = registerItem("kohlrabi_seeds", () -> new ItemNameBlockItem(ModBlocks.KOHLRABI_CROP.get(),new Item.Properties())),
            pepper_seeds = registerItem("pepper_seeds", () -> new ItemNameBlockItem(ModBlocks.PEPPER_CROP.get(),new Item.Properties())),

            //ORES
            raw_silver = registerItem("raw_silver"),
            raw_platinum = registerItem("raw_platinum"),
            raw_titanium = registerItem("raw_titanium"),
            raw_luminite = registerItem("raw_luminite"),
            raw_enderium = registerItem("raw_enderium"),
            sulfur = registerItem("sulfur"),
            aquanium_ingot = registerItem("aquanium_ingot"),
            ender_ingot = registerItem("ender_ingot"),
            enderium_ingot = registerItem("enderium_ingot"),
            end_gold_ingot = registerItem("end_gold_ingot"),
            luminite_ingot = registerItem("luminite_ingot"),
            platinum_ingot = registerItem("platinum_ingot"),
            silver_ingot = registerItem("silver_ingot"),
            steel_ingot = registerItem("steel_ingot"),
            titanium_ingot = registerItem("titanium_ingot"),
            aluminium_ingot = registerItem("aluminium_ingot"),
            aquanium_nugget = registerItem("aquanium_nugget"),
            copper_nugget = registerItem("copper_nugget"),
            enderium_nugget = registerItem("enderium_nugget"),
            luminite_nugget = registerItem("luminite_nugget"),
            platinum_nugget = registerItem("platinum_nugget"),
            silver_nugget = registerItem("silver_nugget"),
            steel_nugget = registerItem("steel_nugget"),
            titanium_nugget = registerItem("titanium_nugget"),

            //SHARDS
            ruby = registerItem("ruby"),
            firerite_gem = registerItem("firerite_gem"),
            frozen_gem = registerItem("frozen_gem"),
            aquanium_shard = registerItem("aquanium_shard"),
            diamond_shard = registerItem("diamond_shard"),
            bedrock_chunk = registerItem("bedrock_chunk"),

            //TECH STUFF
            ender_arch = registerItem("ender_arch"),
            ender_arch_fragment = registerItem("ender_arch_fragment"),
            gold_stick = registerItem("gold_stick"),
            silver_stick = registerItem("silver_stick"),
            iron_plate = registerItem("iron_plate"),
            iron_saw_blade = registerItem("iron_saw_blade", () -> new ItemSawBlade(28)), //Divide max by 9
            platinum_saw_blade = registerItem("platinum_saw_blade", () -> new ItemSawBlade(43)), //Divide max by 9
            titanium_saw_blade = registerItem("titanium_saw_blade", () -> new ItemSawBlade(36)), //Divide max by 9
            diamond_saw_blade = registerItem("diamond_saw_blade", () -> new ItemSawBlade(57)), //Divide max by 9
            stronghold_compass = registerItem("stronghold_compass",() -> new Item(new Item.Properties().stacksTo(1))),

            //DUSTS
            copper_dust = registerItem("copper_dust"),
            diamond_dust = registerItem("diamond_dust"),
            ender_pearl_dust = registerItem("ender_pearl_dust"),
            gold_dust = registerItem("gold_dust"),
            iron_dust = registerItem("iron_dust"),
            platinum_dust = registerItem("platinum_dust"),
            silver_dust = registerItem("silver_dust"),
            sulfur_dust = registerItem("sulfur_dust"),
            titanium_dust = registerItem("titanium_dust"),

            //PROJECTILES
            mud_ball = registerItem("mud_ball", () -> new ItemMud(new Item.Properties())),

            //ENTITIES
            frozen_boat = registerItem("frozen_boat", () -> new ItemModBoat(false,EntityBoat.Type.FROZEN, new Item.Properties().stacksTo(1))),
            frozen_chest_boat = registerItem("frozen_chest_boat", () -> new ItemModBoat(true,EntityBoat.Type.FROZEN, new Item.Properties().stacksTo(1))),
            frozen_sign = registerItem("frozen_sign", () -> new SignItem(new Item.Properties().stacksTo(16), ModBlocks.FROZEN_SIGN.get(), ModBlocks.FROZEN_WALL_SIGN.get())),
            frozen_hanging_sign = registerItem("frozen_hanging_sign", () -> new HangingSignItem(ModBlocks.FROZEN_HANGING_SIGN.get(), ModBlocks.FROZEN_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16))),

            //ADVANCED
            heal_staff = registerItem("heal_staff", ItemHealStaff::new),
            teleportation_staff = registerItem("teleportation_staff", ItemTeleportationStaff::new),
            levitation_staff = registerItem("levitation_staff", ItemLevitationStaff::new),
            ender_elytra = registerItem("ender_elytra",() -> new ElytraItem(new Item.Properties().durability(600).rarity(Rarity.RARE))),

            //HAMMERS
            platinum_hammer = registerItem("platinum_hammer", () -> new ItemHammer(ToolStats.PLATINUM,6F,-3.4F, new Item.Properties().durability(1020))),
            silver_hammer = registerItem("silver_hammer", () -> new ItemHammer(ToolStats.SILVER,5F,-3.4F, new Item.Properties().durability(420))),
            steel_hammer = registerItem("steel_hammer", () -> new ItemHammer(ToolStats.STEEL,6F,-3.4F, new Item.Properties().durability(1654))),
            titanium_hammer = registerItem("titanium_hammer", () -> new ItemHammer(ToolStats.TITANIUM,6F,-3.4F, new Item.Properties().durability(2240))),

            //SHIELDS
            aquanium_shield = registerItem("aquanium_shield", () -> new ShieldItem(new Item.Properties().durability(1105))),
            enderium_shield = registerItem("enderium_shield", () -> new ShieldItem(new Item.Properties().durability(1265))),
            luminite_shield = registerItem("luminite_shield", () -> new ShieldItem(new Item.Properties().durability(860))),
            platinum_shield = registerItem("platinum_shield", () -> new ShieldItem(new Item.Properties().durability(225))),
            silver_shield = registerItem("silver_shield", () -> new ShieldItem(new Item.Properties().durability(210))),
            steel_shield = registerItem("steel_shield", () -> new ShieldItem(new Item.Properties().durability(500))),
            titanium_shield = registerItem("titanium_shield", () -> new ShieldItem(new Item.Properties().durability(560))),
            firerite_shield = registerItem("firerite_shield", () -> new ShieldItem(new Item.Properties().durability(890))),
            frozen_shield = registerItem("frozen_shield", () -> new ShieldItem(new Item.Properties().durability(910))),
            copper_shield = registerItem("copper_shield", () -> new ShieldItem(new Item.Properties().durability(185))),
            gold_shield = registerItem("gold_shield", () -> new ShieldItem(new Item.Properties().durability(220))),

            //SWORDS
            aquanium_sword = registerItem("aquanium_sword", () -> new ItemModSword(ToolStats.AQUANIUM)),
            enderium_sword = registerItem("enderium_sword", () -> new ItemModSword(ToolStats.ENDERIUM)),
            luminite_sword = registerItem("luminite_sword", () -> new ItemModSword(ToolStats.LUMINITE)),
            platinum_sword = registerItem("platinum_sword", () -> new ItemModSword(ToolStats.PLATINUM)),
            silver_sword = registerItem("silver_sword", () -> new ItemModSword(ToolStats.SILVER)),
            steel_sword = registerItem("steel_sword", () -> new ItemModSword(ToolStats.STEEL)),
            titanium_sword = registerItem("titanium_sword", () -> new ItemModSword(ToolStats.TITANIUM)),
            firerite_sword = registerItem("firerite_sword", () -> new ItemModSword(ToolStats.FIRERITE)),
            frozen_sword = registerItem("frozen_sword", () -> new ItemModSword(ToolStats.FROZEN)),
            copper_sword = registerItem("copper_sword", () -> new ItemModSword(ToolStats.COPPER)),
            bedrock_sword = registerItem("bedrock_sword", () -> new ItemModSword(ToolStats.BEDROCK)),

            //TOOLS
            aquanium_shovel = registerItem("aquanium_shovel", () -> new ItemModShovel(ToolStats.AQUANIUM)),
                    aquanium_pickaxe = registerItem("aquanium_pickaxe", () -> new ItemModPickaxe(ToolStats.AQUANIUM)),
                    aquanium_axe = registerItem("aquanium_axe", () -> new ItemModAxe(ToolStats.AQUANIUM, -2.9F)), //TODO SPEED AND DAMAGE MODIFIER
                    aquanium_hoe = registerItem("aquanium_hoe", () -> new ItemModHoe(ToolStats.AQUANIUM, -2.9F)),

            enderium_shovel = registerItem("enderium_shovel", () -> new ItemModShovel(ToolStats.ENDERIUM)),
                    enderium_pickaxe = registerItem("enderium_pickaxe", () -> new ItemModPickaxe(ToolStats.ENDERIUM)),
                    enderium_axe = registerItem("enderium_axe", () -> new ItemModAxe(ToolStats.ENDERIUM, -2.9F)), //TODO SPEED AND DAMAGE MODIFIER
                    enderium_hoe = registerItem("enderium_hoe", () -> new ItemModHoe(ToolStats.ENDERIUM, -2.9F)),

            luminite_shovel = registerItem("luminite_shovel", () -> new ItemModShovel(ToolStats.LUMINITE)),
                    luminite_pickaxe = registerItem("luminite_pickaxe", () -> new ItemModPickaxe(ToolStats.LUMINITE)),
                    luminite_axe = registerItem("luminite_axe", () -> new ItemModAxe(ToolStats.LUMINITE, -2.9F)), //TODO SPEED AND DAMAGE MODIFIER
                    luminite_hoe = registerItem("luminite_hoe", () -> new ItemModHoe(ToolStats.LUMINITE, -2.9F)),

            platinum_shovel = registerItem("platinum_shovel", () -> new ItemModShovel(ToolStats.PLATINUM)),
                    platinum_pickaxe = registerItem("platinum_pickaxe", () -> new ItemModPickaxe(ToolStats.PLATINUM)),
                    platinum_axe = registerItem("platinum_axe", () -> new ItemModAxe(ToolStats.PLATINUM, -2.9F)), //TODO SPEED AND DAMAGE MODIFIER
                    platinum_hoe = registerItem("platinum_hoe", () -> new ItemModHoe(ToolStats.PLATINUM, -2.9F)),

            silver_shovel = registerItem("silver_shovel", () -> new ItemModShovel(ToolStats.SILVER)),
                    silver_pickaxe = registerItem("silver_pickaxe", () -> new ItemModPickaxe(ToolStats.SILVER)),
                    silver_axe = registerItem("silver_axe", () -> new ItemModAxe(ToolStats.SILVER, -2.9F)), //TODO SPEED AND DAMAGE MODIFIER
                    silver_hoe = registerItem("silver_hoe", () -> new ItemModHoe(ToolStats.SILVER, -2.9F)),

            steel_shovel = registerItem("steel_shovel", () -> new ItemModShovel(ToolStats.STEEL)),
                    steel_pickaxe = registerItem("steel_pickaxe", () -> new ItemModPickaxe(ToolStats.STEEL)),
                    steel_axe = registerItem("steel_axe", () -> new ItemModAxe(ToolStats.STEEL, -2.9F)), //TODO SPEED AND DAMAGE MODIFIER
                    steel_hoe = registerItem("steel_hoe", () -> new ItemModHoe(ToolStats.STEEL, -2.9F)),

            titanium_shovel = registerItem("titanium_shovel", () -> new ItemModShovel(ToolStats.TITANIUM)),
                    titanium_pickaxe = registerItem("titanium_pickaxe", () -> new ItemModPickaxe(ToolStats.TITANIUM)),
                    titanium_axe = registerItem("titanium_axe", () -> new ItemModAxe(ToolStats.TITANIUM, -2.9F)), //TODO SPEED AND DAMAGE MODIFIER
                    titanium_hoe = registerItem("titanium_hoe", () -> new ItemModHoe(ToolStats.TITANIUM, -2.9F)),

            firerite_shovel = registerItem("firerite_shovel", () -> new ItemModShovel(ToolStats.FIRERITE)),
                    firerite_pickaxe = registerItem("firerite_pickaxe", () -> new ItemModPickaxe(ToolStats.FIRERITE)),
                    firerite_axe = registerItem("firerite_axe", () -> new ItemModAxe(ToolStats.FIRERITE, -2.9F)), //TODO SPEED AND DAMAGE MODIFIER
                    firerite_hoe = registerItem("firerite_hoe", () -> new ItemModHoe(ToolStats.FIRERITE, -2.9F)),

            frozen_shovel = registerItem("frozen_shovel", () -> new ItemModShovel(ToolStats.FROZEN)),
                    frozen_pickaxe = registerItem("frozen_pickaxe", () -> new ItemModPickaxe(ToolStats.FROZEN)),
                    frozen_axe = registerItem("frozen_axe", () -> new ItemModAxe(ToolStats.FROZEN, -2.9F)), //TODO SPEED AND DAMAGE MODIFIER
                    frozen_hoe = registerItem("frozen_hoe", () -> new ItemModHoe(ToolStats.FROZEN, -2.9F)),

            copper_shovel = registerItem("copper_shovel", () -> new ItemModShovel(ToolStats.COPPER)),
                    copper_pickaxe = registerItem("copper_pickaxe", () -> new ItemModPickaxe(ToolStats.COPPER)),
                    copper_axe = registerItem("copper_axe", () -> new ItemModAxe(ToolStats.COPPER, -2.9F)), //TODO SPEED AND DAMAGE MODIFIER
                    copper_hoe = registerItem("copper_hoe", () -> new ItemModHoe(ToolStats.COPPER, -2.9F)),

            bedrock_shovel = registerItem("bedrock_shovel", () -> new ItemModShovel(ToolStats.BEDROCK)),
                    bedrock_pickaxe = registerItem("bedrock_pickaxe", () -> new ItemModPickaxe(ToolStats.BEDROCK)),
                    bedrock_axe = registerItem("bedrock_axe", () -> new ItemModAxe(ToolStats.BEDROCK, -2.9F)), //TODO SPEED AND DAMAGE MODIFIER
                    bedrock_hoe = registerItem("bedrock_hoe", () -> new ItemModHoe(ToolStats.BEDROCK, -2.9F)),

            //ARMOR
            aquanium_helmet = registerItem("aquanium_helmet", () -> ArmorAquanium.getInstance(ArmorItem.Type.HELMET)),
                    aquanium_chestplate = registerItem("aquanium_chestplate", () -> ArmorAquanium.getInstance(ArmorItem.Type.CHESTPLATE)),
                    aquanium_leggings = registerItem("aquanium_leggings", () -> ArmorAquanium.getInstance(ArmorItem.Type.LEGGINGS)),
                    aquanium_boots = registerItem("aquanium_boots", () -> ArmorAquanium.getInstance(ArmorItem.Type.BOOTS)),

            enderium_helmet = registerItem("enderium_helmet", () -> ArmorEnderium.getInstance(ArmorItem.Type.HELMET)),
                    enderium_chestplate = registerItem("enderium_chestplate", () -> ArmorEnderium.getInstance(ArmorItem.Type.CHESTPLATE)),
                    enderium_leggings = registerItem("enderium_leggings", () -> ArmorEnderium.getInstance(ArmorItem.Type.LEGGINGS)),
                    enderium_boots = registerItem("enderium_boots", () -> ArmorEnderium.getInstance(ArmorItem.Type.BOOTS)),

            luminite_helmet = registerItem("luminite_helmet", () -> new ItemArmor(ArmorStats.LUMINITE, ArmorItem.Type.HELMET)),
                    luminite_chestplate = registerItem("luminite_chestplate", () -> new ItemArmor(ArmorStats.LUMINITE, ArmorItem.Type.CHESTPLATE)),
                    luminite_leggings = registerItem("luminite_leggings", () -> new ItemArmor(ArmorStats.LUMINITE, ArmorItem.Type.LEGGINGS)),
                    luminite_boots = registerItem("luminite_boots", () -> new ItemArmor(ArmorStats.LUMINITE, ArmorItem.Type.BOOTS)),

            platinum_helmet = registerItem("platinum_helmet", () -> new ItemArmor(ArmorStats.PLATINUM, ArmorItem.Type.HELMET)),
                    platinum_chestplate = registerItem("platinum_chestplate", () -> new ItemArmor(ArmorStats.PLATINUM, ArmorItem.Type.CHESTPLATE)),
                    platinum_leggings = registerItem("platinum_leggings", () -> new ItemArmor(ArmorStats.PLATINUM, ArmorItem.Type.LEGGINGS)),
                    platinum_boots = registerItem("platinum_boots", () -> new ItemArmor(ArmorStats.PLATINUM, ArmorItem.Type.BOOTS)),

            silver_helmet = registerItem("silver_helmet", () -> new ItemArmor(ArmorStats.SILVER, ArmorItem.Type.HELMET)),
                   silver_chestplate = registerItem("silver_chestplate", () -> new ItemArmor(ArmorStats.SILVER, ArmorItem.Type.CHESTPLATE)),
                   silver_leggings = registerItem("silver_leggings", () -> new ItemArmor(ArmorStats.SILVER, ArmorItem.Type.LEGGINGS)),
                   silver_boots = registerItem("silver_boots", () -> new ItemArmor(ArmorStats.SILVER, ArmorItem.Type.BOOTS)),

            steel_helmet = registerItem("steel_helmet", () -> ArmorSteel.getInstance(ArmorItem.Type.HELMET)),
                   steel_chestplate = registerItem("steel_chestplate", () -> ArmorSteel.getInstance(ArmorItem.Type.CHESTPLATE)),
                   steel_leggings = registerItem("steel_leggings", () -> ArmorSteel.getInstance(ArmorItem.Type.LEGGINGS)),
                   steel_boots = registerItem("steel_boots", () -> ArmorSteel.getInstance(ArmorItem.Type.BOOTS)),

            titanium_helmet = registerItem("titanium_helmet", () -> new ItemArmor(ArmorStats.TITANIUM, ArmorItem.Type.HELMET)),
                   titanium_chestplate = registerItem("titanium_chestplate", () -> new ItemArmor(ArmorStats.TITANIUM, ArmorItem.Type.CHESTPLATE)),
                   titanium_leggings = registerItem("titanium_leggings", () -> new ItemArmor(ArmorStats.TITANIUM, ArmorItem.Type.LEGGINGS)),
                   titanium_boots = registerItem("titanium_boots", () -> new ItemArmor(ArmorStats.TITANIUM, ArmorItem.Type.BOOTS)),

            firerite_helmet = registerItem("firerite_helmet", () -> ArmorFirerite.getInstance(ArmorItem.Type.HELMET)),
                   firerite_chestplate = registerItem("firerite_chestplate", () -> ArmorFirerite.getInstance(ArmorItem.Type.CHESTPLATE)),
                   firerite_leggings = registerItem("firerite_leggings", () -> ArmorFirerite.getInstance(ArmorItem.Type.LEGGINGS)),
                   firerite_boots = registerItem("firerite_boots", () -> ArmorFirerite.getInstance(ArmorItem.Type.BOOTS)),

            frozen_helmet = registerItem("frozen_helmet", () -> ArmorFrozen.getInstance(ArmorItem.Type.HELMET)),
                   frozen_chestplate = registerItem("frozen_chestplate", () -> ArmorFrozen.getInstance(ArmorItem.Type.CHESTPLATE)),
                   frozen_leggings = registerItem("frozen_leggings", () -> ArmorFrozen.getInstance(ArmorItem.Type.LEGGINGS)),
                   frozen_boots = registerItem("frozen_boots", () -> ArmorFrozen.getInstance(ArmorItem.Type.BOOTS)),

            copper_helmet = registerItem("copper_helmet", () -> new ItemArmor(ArmorStats.COPPER, ArmorItem.Type.HELMET)),
                   copper_chestplate = registerItem("copper_chestplate", () -> new ItemArmor(ArmorStats.COPPER, ArmorItem.Type.CHESTPLATE)),
                   copper_leggings = registerItem("copper_leggings", () -> new ItemArmor(ArmorStats.COPPER, ArmorItem.Type.LEGGINGS)),
                   copper_boots = registerItem("copper_boots", () -> new ItemArmor(ArmorStats.COPPER, ArmorItem.Type.BOOTS)),

            //MISC
            molten_sulfur_bucket = registerItem("molten_sulfur_bucket"),  //TODO
            end_lava_bucket = registerItem("end_lava_bucket", () -> new BucketItem(FluidsMod.END_LAVA_FLUID, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1))),
            ice_beam = registerItem("ice_beam", () -> new IceBeam(new Item.Properties())),

            //EGGS
            dungeon_ice_spawn_egg = registerItem("dungeon_ice_spawn_egg", () -> new ForgeSpawnEggItem(EntityRegistry.DUNGEON_ICE, 0x34dbeb, 0xFFFFFF, new Item.Properties())),
            yak_spawn_egg = registerItem("yak_spawn_egg", () -> new ForgeSpawnEggItem(EntityRegistry.YAK, 2367000, 1183757, new Item.Properties()));

    private static RegistryObject<Item> registerItem(String registryId) {
        return ITEMS.register(registryId, ItemMod::new);
    }
    private static <T extends Item> RegistryObject<T> registerItem(String registryId, Supplier<T> item) {
        return ITEMS.register(registryId, item);
    }
}
package net.shadowbeast.projectshadow.items;

import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.shadowbeast.projectshadow.ProjectShadow;
import net.shadowbeast.projectshadow.enums.ToolStats;
import net.shadowbeast.projectshadow.items.costum.HammerItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ProjectShadow.MOD_ID);

    //INGOTS
    public static final RegistryObject<Item> AQUANIUM_INGOT = ITEMS.register("aquanium_ingot",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ENDERIUM_INGOT = ITEMS.register("enderium_ingot",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_INGOT = ITEMS.register("platinum_ingot",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LUMINITE_INGOT = ITEMS.register("luminite_ingot",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SILVER_INGOT = ITEMS.register("silver_ingot",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM_INGOT = ITEMS.register("titanium_ingot",
            ()-> new Item(new Item.Properties()));


    //RAW INGOTS
    public static final RegistryObject<Item> RAW_ENDERIUM = ITEMS.register("raw_enderium",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_LUMINITE = ITEMS.register("raw_luminite",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_STEEL = ITEMS.register("raw_steel",
            ()-> new Item(new Item.Properties()));

    //SHARDS
    public static final RegistryObject<Item> AQUANIUM_SHARD = ITEMS.register("aquanium_shard",
            ()-> new Item(new Item.Properties()));

    //NUGGETS
    public static final RegistryObject<Item> AQUANIUM_NUGGET = ITEMS.register("aquanium_nugget",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_NUGGET= ITEMS.register("platinum_nugget",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RUBY = ITEMS.register("ruby",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SILVER_NUGGET = ITEMS.register("silver_nugget",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STEEL_NUGGET = ITEMS.register("steel_nugget",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM_NUGGET = ITEMS.register("titanium_nugget",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COPPER_NUGGET = ITEMS.register("copper_nugget",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ENDERIUM_NUGGET = ITEMS.register("enderium_nugget",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LUMINITE_NUGGET = ITEMS.register("luminite_nugget",
            ()-> new Item(new Item.Properties()));

    //SWORDS
    public static final RegistryObject<Item> PLATINUM_SWORD = ITEMS.register("platinum_sword",
            ()-> new SwordItem(ToolStats.PLATINUM,3,-2.4F, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_SWORD = ITEMS.register("steel_sword",
            ()-> new SwordItem(ToolStats.STEEL,3,-2.6F, new Item.Properties()));
    public static final RegistryObject<Item> SILVER_SWORD = ITEMS.register("silver_sword",
            ()-> new SwordItem(ToolStats.SILVER,1,-2.4F, new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM_SWORD = ITEMS.register("titanium_sword",
            ()-> new SwordItem(ToolStats.TITANIUM,4,-2.4F, new Item.Properties()));

    //AXES
    public static final RegistryObject<Item> PLATINUM_AXE = ITEMS.register("platinum_axe",
            ()-> new AxeItem(ToolStats.PLATINUM,6F,-3.1F, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_AXE = ITEMS.register("steel_axe",
            ()-> new AxeItem(ToolStats.STEEL,6F,-3.2F, new Item.Properties()));
    public static final RegistryObject<Item> SILVER_AXE = ITEMS.register("silver_axe",
            ()-> new AxeItem(ToolStats.SILVER,5.0F,-3.0F, new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM_AXE = ITEMS.register("titanium_axe",
            ()-> new AxeItem(ToolStats.TITANIUM,6.0F,-3.0F, new Item.Properties()));

    //PICKAXES
    public static final RegistryObject<Item> PLATINUM_PICKAXE = ITEMS.register("platinum_pickaxe",
            ()-> new PickaxeItem(ToolStats.PLATINUM,1,-2.8F, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_PICKAXE = ITEMS.register("steel_pickaxe",
            ()-> new PickaxeItem(ToolStats.STEEL,1,-3F, new Item.Properties()));
    public static final RegistryObject<Item> SILVER_PICKAXE = ITEMS.register("silver_pickaxe",
            ()-> new PickaxeItem(ToolStats.SILVER,-1,-2.8F, new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM_PICKAXE = ITEMS.register("titanium_pickaxe",
            ()-> new PickaxeItem(ToolStats.TITANIUM,2,-2.8F, new Item.Properties()));

    //SHOVELS
    public static final RegistryObject<Item> PLATINUM_SHOVEL = ITEMS.register("platinum_shovel",
            ()-> new ShovelItem(ToolStats.PLATINUM,1.5F,-3F, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_SHOVEL = ITEMS.register("steel_shovel",
            ()-> new ShovelItem(ToolStats.STEEL,1.5F,-3F, new Item.Properties()));
    public static final RegistryObject<Item> SILVER_SHOVEL = ITEMS.register("silver_shovel",
            ()-> new ShovelItem(ToolStats.SILVER,-0.5F,-3.0F, new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM_SHOVEL = ITEMS.register("titanium_shovel",
            ()-> new ShovelItem(ToolStats.TITANIUM,2.5F,-3.0F, new Item.Properties()));

    //HOES
    public static final RegistryObject<Item> PLATINUM_HOE = ITEMS.register("platinum_hoe",
            ()-> new HoeItem(ToolStats.PLATINUM,-2,-1.0F, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_HOE = ITEMS.register("steel_hoe",
            ()-> new HoeItem(ToolStats.STEEL,-2,-1.0F, new Item.Properties()));
    public static final RegistryObject<Item> SILVER_HOE = ITEMS.register("silver_hoe",
            ()-> new HoeItem(ToolStats.SILVER,-2,-3.0F    , new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM_HOE = ITEMS.register("titanium_hoe",
            ()-> new HoeItem(ToolStats.TITANIUM,-2,0.0F, new Item.Properties()));

    //HAMMERS
    public static final RegistryObject<Item> STEEL_HAMMER = ITEMS.register("steel_hammer",
            ()-> new HammerItem(ToolStats.STEEL,6F,-3.4f, new Item.Properties().durability(1654)));
    public static final RegistryObject<Item> SILVER_HAMMER = ITEMS.register("silver_hammer",
            ()-> new HammerItem(ToolStats.SILVER,5.0F,-3.4F, new Item.Properties().durability(420)));
    public static final RegistryObject<Item> TITANIUM_HAMMER = ITEMS.register("titanium_hammer",
            ()-> new HammerItem(ToolStats.TITANIUM,6F,-3.4F, new Item.Properties().durability(2240)));
    public static final RegistryObject<Item> PLATINUM_HAMMER = ITEMS.register("platinum_hammer",
            ()-> new HammerItem(ToolStats.PLATINUM,6F,-3.4F, new Item.Properties().durability(1020)));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

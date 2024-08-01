package net.shadowbeast.frostbound.core;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.shadowbeast.frostbound.util.nbt.ListBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class ItemSettingConfig
{
    private static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.ConfigValue<List<? extends List<?>>> boilerItems;
    private static final ForgeConfigSpec.ConfigValue<List<? extends String>> blacklistedPotions;
    private static final ForgeConfigSpec.BooleanValue allowPotionsInHearth;
    private static final ForgeConfigSpec.ConfigValue<List<? extends List<?>>> soulLampItems;
    private static final ForgeConfigSpec.ConfigValue<List<? extends String>> soulLampDimensions;
    private static final ForgeConfigSpec.ConfigValue<List<? extends List<?>>> temperatureFoods;


    private static final ForgeConfigSpec.ConfigValue<List<? extends List<?>>> insulatingArmor;

    private static final ForgeConfigSpec.IntValue waterskinStrength;


    static final ItemSettingConfig INSTANCE = new ItemSettingConfig();

    static
    {
        /*
          Fuel Items
         */
        BUILDER.push("Fuel Items")
                .comment("Defines items that can be used as fuel",
                        "Format: [[\"item-id-1\", amount-1], [\"item-id-2\", amount-2], ...etc]");
        boilerItems = BUILDER
                .defineListAllowEmpty(List.of("Boiler"), () -> ListBuilder.begin(
                                List.of("#minecraft:planks",         10),
                                List.of("minecraft:coal",            37),
                                List.of("minecraft:charcoal",        37),
                                List.of("#minecraft:logs_that_burn", 37),
                                List.of("minecraft:coal_block",      333),
                                List.of("minecraft:magma_block",     333),
                                List.of("minecraft:lava_bucket",     1000)
                        ).build(),
                        it -> it instanceof List<?> list && list.size() == 2 && list.get(0) instanceof String && list.get(1) instanceof Number);

        blacklistedPotions = BUILDER
                .comment("Potions containing any of these effects will not be allowed in the hearth",
                        "Format: [\"effect_id\", \"effect_id\", ...etc]")
                .defineListAllowEmpty(List.of("Blacklisted Hearth Potions"), () -> ListBuilder.begin(
                                "minecraft:instant_damage",
                                "minecraft:poison",
                                "minecraft:wither",
                                "minecraft:weakness",
                                "minecraft:mining_fatigue",
                                "minecraft:slowness"
                        ).build(),
                        it -> it instanceof String);
        allowPotionsInHearth = BUILDER
                .comment("If true, potions can be used as fuel in the hearth",
                        "This gives all players in range the potion effect")
                .define("Allow Potions in Hearth", true);
        BUILDER.pop();

        /*
          Soulspring Lamp Items
         */
        BUILDER.push("Soulspring Lamp");
        soulLampItems = BUILDER
                .comment("Defines items that the Soulspring Lamp can use as fuel",
                        "Format: [[\"item-id-1\", amount-1], [\"item-id-2\", amount-2], ...etc]")
                .defineListAllowEmpty(List.of("Fuel Items"), () -> ListBuilder.<List<?>>begin(
                                List.of("cold_sweat:soul_sprout", 4)
                        ).build(),
                        it -> it instanceof List<?> list && list.size() == 2 && list.get(0) instanceof String && list.get(1) instanceof Number);

        soulLampDimensions = BUILDER
                .comment("Defines the dimensions that the Soulspring Lamp can be used in",
                        "Format: [\"dimension-id-1\", \"dimension-id-2\", ...etc]")
                .defineListAllowEmpty(List.of("Valid Dimensions"), () -> ListBuilder.begin(
                                "minecraft:the_nether"
                        ).build(),
                        it -> it instanceof String);
        BUILDER.pop();

        /*
         Insulation
         */


        insulatingArmor = BUILDER
                .comment("Defines the items that provide insulation when worn",
                        "See Insulation Ingredients for formatting")
                .defineListAllowEmpty(List.of("Insulating Armor"), () -> ListBuilder.begin(
                                        List.of("minecraft:leather_helmet",      4,  4),
                                        List.of("minecraft:leather_chestplate",  6,  6),
                                        List.of("minecraft:leather_leggings",    5,  5),
                                        List.of("minecraft:leather_boots",       4,  4)
                                ).build(),
                        it -> it instanceof List<?> list && list.size() >= 3
                                && list.get(0) instanceof String
                                && list.get(1) instanceof Number
                                && list.get(2) instanceof Number
                                && (list.size() < 4 || list.get(3) instanceof String)
                                && (list.size() < 5 || list.get(4) instanceof String));



        //BUILDER.pop();

        /*
         Consumables
         */
        BUILDER.push("Consumables");
        temperatureFoods = BUILDER
                .comment("Defines items that affect the player's temperature when consumed",
                        "Format: [[\"item_id\", amount, *nbt, *duration], [\"item_id\", amount, *nbt, *duration], ...etc]",
                        "Negative values are cold foods, positive values are hot foods",
                        "nbt: Optional. If set, the item will only affect the player's temperature if it has the specified NBT tag.",
                        "duration: Optional. If set, the player's temperature will remain increased/decreased for this amount of time.")
                .defineListAllowEmpty(List.of("Temperature-Affecting Foods"), () -> Arrays.asList(
                                List.of("cold_sweat:soul_sprout", -20, "{}", 1200)
                        ),
                        it -> it instanceof List<?> list && list.size() >= 2
                                && list.get(0) instanceof String
                                && list.get(1) instanceof Number
                                && (list.size() < 3 || list.get(2) instanceof String)
                                && (list.size() < 4 || list.get(3) instanceof Integer));
        waterskinStrength = BUILDER
                .comment("Defines how much a waterskin will change the player's body temperature by when used")
                .defineInRange("Waterskin Strength", 50, 0, Integer.MAX_VALUE);
        BUILDER.pop();

        SPEC = BUILDER.build();
    }

    public static void setup()
    {
        Path configPath = FMLPaths.CONFIGDIR.get();
        Path csConfigPath = Paths.get(configPath.toAbsolutePath().toString(), "frostbound");

        // Create the config folder
        try
        {   Files.createDirectory(csConfigPath);
        }
        catch (Exception ignored) {}

        ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.COMMON, SPEC, "frostbound/item_settings.toml");
    }

    public static ItemSettingConfig getInstance()
    {   return INSTANCE;
    }

    /* Getters */

    public List<? extends List<?>> getBoilerFuelItems()
    {   return boilerItems.get();
    }


    public List<? extends List<?>> getInsulatingArmorItems()
    {   return insulatingArmor.get();
    }




    public int getWaterskinStrength()
    {   return waterskinStrength.get();
    }

    public boolean arePotionsEnabled()
    {   return allowPotionsInHearth.get();
    }

    public List<String> getPotionBlacklist()
    {   return (List<String>) blacklistedPotions.get();
    }

    public synchronized void setInsulatingArmorItems(List<? extends List<?>> itemMap)
    {   synchronized (insulatingArmor)
    {   insulatingArmor.set(itemMap);
    }
    }



    public synchronized void setSoulLampFuelItems(List<? extends List<?>> items)
    {   synchronized (soulLampItems)
    {   soulLampItems.set(items);
    }
    }

    public synchronized void setFoodTemperatures(List<? extends List<?>> itemMap)
    {   synchronized (temperatureFoods)
    {   temperatureFoods.set(itemMap);
    }
    }

    public synchronized void setValidSoulLampDimensions(List<? extends String> items)
    {   synchronized (soulLampDimensions)
    {   soulLampDimensions.set(items);
    }
    }

    public synchronized void setWaterskinStrength(int strength)
    {   synchronized (waterskinStrength)
    {   waterskinStrength.set(strength);
    }
    }

    public synchronized void setPotionsEnabled(Boolean saver)
    {   synchronized (allowPotionsInHearth)
    {   allowPotionsInHearth.set(saver);
    }
    }

    public synchronized void setPotionBlacklist(List<String> saver)
    {   synchronized (blacklistedPotions)
    {   blacklistedPotions.set(saver);
    }
    }


}

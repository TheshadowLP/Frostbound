package net.shadowbeast.frostbound.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.shadowbeast.frostbound.Frostbound;
import net.shadowbeast.frostbound.items.ItemMod;
import net.shadowbeast.frostbound.registries.ItemRegistry;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.LinkedHashMap;
import java.util.Objects;
@ParametersAreNonnullByDefault
public class ItemModelGenerator extends ItemModelProvider {
    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }
    public ItemModelGenerator(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Frostbound.MOD_ID, existingFileHelper);
    }
    @Override
    protected void registerModels() {

        trimmedArmorItem(ItemRegistry.AQUANIUM_BOOTS);
        trimmedArmorItem(ItemRegistry.AQUANIUM_LEGGINGS);
        trimmedArmorItem(ItemRegistry.AQUANIUM_CHESTPLATE);
        trimmedArmorItem(ItemRegistry.AQUANIUM_HELMET);

        trimmedArmorItem(ItemRegistry.LUMINITE_BOOTS);
        trimmedArmorItem(ItemRegistry.LUMINITE_LEGGINGS);
        trimmedArmorItem(ItemRegistry.LUMINITE_CHESTPLATE);
        trimmedArmorItem(ItemRegistry.LUMINITE_HELMET);

        trimmedArmorItem(ItemRegistry.SILVER_BOOTS);
        trimmedArmorItem(ItemRegistry.SILVER_LEGGINGS);
        trimmedArmorItem(ItemRegistry.SILVER_CHESTPLATE);
        trimmedArmorItem(ItemRegistry.SILVER_HELMET);

        trimmedArmorItem(ItemRegistry.STEEL_BOOTS);
        trimmedArmorItem(ItemRegistry.STEEL_LEGGINGS);
        trimmedArmorItem(ItemRegistry.STEEL_CHESTPLATE);
        trimmedArmorItem(ItemRegistry.STEEL_HELMET);

        trimmedArmorItem(ItemRegistry.TITANIUM_BOOTS);
        trimmedArmorItem(ItemRegistry.TITANIUM_LEGGINGS);
        trimmedArmorItem(ItemRegistry.TITANIUM_CHESTPLATE);
        trimmedArmorItem(ItemRegistry.TITANIUM_HELMET);

        trimmedArmorItem(ItemRegistry.PLATINUM_BOOTS);
        trimmedArmorItem(ItemRegistry.PLATINUM_LEGGINGS);
        trimmedArmorItem(ItemRegistry.PLATINUM_CHESTPLATE);
        trimmedArmorItem(ItemRegistry.PLATINUM_HELMET);

        trimmedArmorItem(ItemRegistry.COPPER_BOOTS);
        trimmedArmorItem(ItemRegistry.COPPER_LEGGINGS);
        trimmedArmorItem(ItemRegistry.COPPER_CHESTPLATE);
        trimmedArmorItem(ItemRegistry.COPPER_HELMET);

        trimmedArmorItem(ItemRegistry.FIRERITE_BOOTS);
        trimmedArmorItem(ItemRegistry.FIRERITE_LEGGINGS);
        trimmedArmorItem(ItemRegistry.FIRERITE_CHESTPLATE);
        trimmedArmorItem(ItemRegistry.FIRERITE_HELMET);

        trimmedArmorItem(ItemRegistry.FROZEN_BOOTS);
        trimmedArmorItem(ItemRegistry.FROZEN_LEGGINGS);
        trimmedArmorItem(ItemRegistry.FROZEN_CHESTPLATE);
        trimmedArmorItem(ItemRegistry.FROZEN_HELMET);

    }

    private void trimmedArmorItem(RegistryObject<Item> itemRegistryObject) {
        final String MOD_ID = Frostbound.MOD_ID;

        if(itemRegistryObject.get() instanceof ArmorItem armorItem) {
            trimMaterials.forEach((trimMaterial, value) -> {

                float trimValue = value;

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = "item/" + armorItem;
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = new ResourceLocation(MOD_ID, armorItemPath);
                ResourceLocation trimResLoc = new ResourceLocation(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = new ResourceLocation(MOD_ID, currentTrimName);

                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc)
                        .texture("layer1", trimResLoc);

                this.withExistingParent(itemRegistryObject.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                new ResourceLocation(MOD_ID,
                                        "item/" + itemRegistryObject.getId().getPath()));
            });
        }
    }
    private ItemModelBuilder saplingItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Frostbound.MOD_ID,"block/" + item.getId().getPath()));
    }
    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Frostbound.MOD_ID,"item/" + item.getId().getPath()));
    }
    public void evenSimplerBlockItem(RegistryObject<Block> block) {
        this.withExistingParent(Frostbound.MOD_ID + ":" + Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block.get())).getPath(),
                modLoc("block/" + Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block.get())).getPath()));
    }
    public void trapdoorItem(RegistryObject<Block> block) {
        this.withExistingParent(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block.get())).getPath(),
                modLoc("block/" + Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block.get())).getPath() + "_bottom"));
    }
    public void fenceItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block.get())).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  new ResourceLocation(Frostbound.MOD_ID, "block/" + Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(baseBlock.get())).getPath()));
    }
    public void buttonItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block.get())).getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  new ResourceLocation(Frostbound.MOD_ID, "block/" + Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(baseBlock.get())).getPath()));
    }
    public void wallItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block.get())).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  new ResourceLocation(Frostbound.MOD_ID, "block/" + Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(baseBlock.get())).getPath()));
    }
    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(Frostbound.MOD_ID,"item/" + item.getId().getPath()));
    }
    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Frostbound.MOD_ID,"item/" + item.getId().getPath()));
    }
    private ItemModelBuilder simpleBlockItemBlockTexture(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Frostbound.MOD_ID,"block/" + item.getId().getPath()));
    }
}
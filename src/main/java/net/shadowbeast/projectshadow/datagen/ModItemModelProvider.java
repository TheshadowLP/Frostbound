package net.shadowbeast.projectshadow.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;

import static net.shadowbeast.projectshadow.ProjectShadow.MOD_ID;
import static net.shadowbeast.projectshadow.items.ModItems.*;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        trimmedArmorItem(AQUANIUM_HELMET);
        trimmedArmorItem(AQUANIUM_CHESTPLATE);
        trimmedArmorItem(AQUANIUM_LEGGINGS);
        trimmedArmorItem(AQUANIUM_BOOTS);

        trimmedArmorItem(ENDERIUM_HELMET);
        trimmedArmorItem(ENDERIUM_CHESTPLATE);
        trimmedArmorItem(ENDERIUM_LEGGINGS);
        trimmedArmorItem(ENDERIUM_BOOTS);

        trimmedArmorItem(COPPER_HELMET);
        trimmedArmorItem(COPPER_CHESTPLATE);
        trimmedArmorItem(COPPER_LEGGINGS);
        trimmedArmorItem(COPPER_BOOTS);

        trimmedArmorItem(FROZEN_HELMET);
        trimmedArmorItem(FROZEN_CHESTPLATE);
        trimmedArmorItem(FROZEN_LEGGINGS);
        trimmedArmorItem(FROZEN_BOOTS);

        trimmedArmorItem(FIRERITE_HELMET);
        trimmedArmorItem(FIRERITE_CHESTPLATE);
        trimmedArmorItem(FIRERITE_LEGGINGS);
        trimmedArmorItem(FIRERITE_BOOTS);

        trimmedArmorItem(SILVER_HELMET);
        trimmedArmorItem(SILVER_CHESTPLATE);
        trimmedArmorItem(SILVER_LEGGINGS);
        trimmedArmorItem(SILVER_BOOTS);

        trimmedArmorItem(LUMINITE_HELMET);
        trimmedArmorItem(LUMINITE_CHESTPLATE);
        trimmedArmorItem(LUMINITE_LEGGINGS);
        trimmedArmorItem(LUMINITE_BOOTS);

        trimmedArmorItem(PLATINUM_HELMET);
        trimmedArmorItem(PLATINUM_CHESTPLATE);
        trimmedArmorItem(PLATINUM_LEGGINGS);
        trimmedArmorItem(PLATINUM_BOOTS);

        trimmedArmorItem(STEEL_HELMET);
        trimmedArmorItem(STEEL_CHESTPLATE);
        trimmedArmorItem(STEEL_LEGGINGS);
        trimmedArmorItem(STEEL_BOOTS);

        trimmedArmorItem(TITANIUM_HELMET);
        trimmedArmorItem(TITANIUM_CHESTPLATE);
        trimmedArmorItem(TITANIUM_LEGGINGS);
        trimmedArmorItem(TITANIUM_BOOTS);
    }

    private void trimmedArmorItem(@NotNull RegistryObject<Item> itemRegistryObject) {
        LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
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

                // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
                // avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                // Trimmed armorItem files
                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc)
                        .texture("layer1", trimResLoc);

                // Non-trimmed armorItem file (normal variant)
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
}

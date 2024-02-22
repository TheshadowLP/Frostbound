package net.shadowbeast.projectshadow.items.custom;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.shadowbeast.projectshadow.items.ModItems;


public class ModItemProperties {
    public static void addCustomItemProperties() {
        ItemProperties.register(ModItems.STEEL_SHIELD.get(), new ResourceLocation("blocking"), (ItemStack, ClientLevel, LivingEntity, SomeInteger) -> LivingEntity != null && LivingEntity.isUsingItem() && LivingEntity.getUseItem() == ItemStack ? 1.0F : 0.0F);
    }
}
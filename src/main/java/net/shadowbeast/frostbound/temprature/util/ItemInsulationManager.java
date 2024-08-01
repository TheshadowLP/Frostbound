package net.shadowbeast.frostbound.temprature.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.shadowbeast.frostbound.config.Insulator;
import net.shadowbeast.frostbound.util.nbt.NBTHelper;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class ItemInsulationManager
{

    @SubscribeEvent
    public static void attachCapabilityToItemHandler(AttachCapabilitiesEvent<ItemStack> event)
    {
        ItemStack stack = event.getObject();


            // Attach the capability to the item

            // Legacy code for updating items using the pre-2.2 insulation system
            CompoundTag stackNBT = NBTHelper.getTagOrEmpty(stack);
    }



    @SubscribeEvent
    public static void handleInventoryOpen(PlayerContainerEvent event)
    {   event.getEntity().getPersistentData().putBoolean("InventoryOpen", event instanceof PlayerContainerEvent.Open);
    }



    public static boolean isInsulatable(ItemStack stack)
    {
        return stack.getItem() instanceof Equipable;
    }

    public static List<Insulator> getInsulatorsForStack(ItemStack stack)
    {
        List<Insulator> insulators = new ArrayList<>();


        return insulators;
    }

    public static List<AttributeModifier> getInsulationAttributeModifiers(ItemStack stack, Attribute attribute, @Nullable AttributeModifier.Operation operation, @Nullable Entity owner)
    {
        List<AttributeModifier> modifiers = new ArrayList<>();
        for (Insulator insulator : getInsulatorsForStack(stack))
        {
            if (insulator.test(owner, stack))
            {
                modifiers.addAll(insulator.attributes().get(attribute)
                        .stream()
                        .filter(mod -> operation == null || mod.getOperation() == operation)
                        .toList());
            }
        }
        return modifiers;
    }

    public static List<AttributeModifier> getAttributeModifiersForSlot(ItemStack stack, Attribute attribute, EquipmentSlot slot, @Nullable AttributeModifier.Operation operation, @Nullable Entity owner)
    {
        List<AttributeModifier> modifiers = new ArrayList<>(operation != null
                ? stack.getAttributeModifiers(slot).get(attribute)
                .stream()
                .filter(mod -> mod.getOperation() == operation)
                .toList()
                : stack.getAttributeModifiers(slot).get(attribute));
        modifiers.addAll(getInsulationAttributeModifiers(stack, attribute, operation, owner));
        return modifiers;
    }

    public static List<AttributeModifier> getAttributeModifiersForSlot(ItemStack stack, Attribute attribute, EquipmentSlot slot)
    {   return getAttributeModifiersForSlot(stack, attribute, slot, null, null);
    }
}
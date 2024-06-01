package net.shadowbeast.arcanemysteries.enchant;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.shadowbeast.arcanemysteries.ArcaneMysteries.MODID;

public class EnchantmentsRegistry {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MODID);
    public static RegistryObject<Enchantment> ICE_ASPECT =
            ENCHANTMENTS.register("ice_aspect",
                    () -> new IceAspectEnchantment(Enchantment.Rarity.UNCOMMON,
                            EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND));
    public static RegistryObject<Enchantment> TREECAPITATOR =
            ENCHANTMENTS.register("treecapitator",
                    () -> new TreeCapitatorEnchantment(Enchantment.Rarity.UNCOMMON,
                            EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND));
}
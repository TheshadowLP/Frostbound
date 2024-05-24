package net.shadowbeast.arcanemysteries.items.custom.armor;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.shadowbeast.arcanemysteries.enums.ArmorStats;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class SilverArmor extends ArmorItem {
    private SilverArmor(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }
    public static SilverArmor getInstance(Type ptype) {
        return new SilverArmor(ArmorStats.SILVER, ptype, new Properties().stacksTo(1));
    }
}

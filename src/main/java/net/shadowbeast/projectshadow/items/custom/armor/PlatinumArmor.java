package net.shadowbeast.projectshadow.items.custom.armor;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.shadowbeast.projectshadow.enums.ArmorStats;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class PlatinumArmor extends ArmorItem {
    private PlatinumArmor(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }
    public static PlatinumArmor getInstance(Type ptype) {
        return new PlatinumArmor(ArmorStats.PLATINUM, ptype, new Properties().stacksTo(1));
    }
}

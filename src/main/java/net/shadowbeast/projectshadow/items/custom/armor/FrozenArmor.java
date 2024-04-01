package net.shadowbeast.projectshadow.items.custom.armor;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.shadowbeast.projectshadow.enums.ArmorStats;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class FrozenArmor extends ArmorItem {
    private FrozenArmor(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }
    public static FrozenArmor getInstance(Type ptype) {
        return new FrozenArmor(ArmorStats.FROZEN, ptype, new Properties().stacksTo(1));
    }
}

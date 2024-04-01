package net.shadowbeast.projectshadow.items.custom.armor;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.shadowbeast.projectshadow.enums.ArmorStats;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class TitaniumArmor extends ArmorItem {
    private TitaniumArmor(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }
    public static TitaniumArmor getInstance(Type ptype) {
        return new TitaniumArmor(ArmorStats.TITANIUM, ptype, new Properties().stacksTo(1));
    }
}

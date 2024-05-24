package net.shadowbeast.arcanemysteries.items.custom.armor;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.shadowbeast.arcanemysteries.enums.ArmorStats;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class LuminiteArmor extends ArmorItem {
    private LuminiteArmor(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }
    public static LuminiteArmor getInstance(Type ptype) {
        return new LuminiteArmor(ArmorStats.LUMINITE, ptype, new Properties().stacksTo(1));
    }
}

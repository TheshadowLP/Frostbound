package net.shadowbeast.arcanemysteries.util;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;

public class WoodTypesMod {
    public static final WoodType FROZEN = WoodType.register(new WoodType(ArcaneMysteries.MOD_ID + ":frozen", BlockSetType.OAK));
}
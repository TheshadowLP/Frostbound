package net.shadowbeast.frostbound.util;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.shadowbeast.frostbound.Frostbound;

public class WoodTypesMod {
    public static final WoodType FROZEN = WoodType.register(new WoodType(Frostbound.MOD_ID + ":frozen", BlockSetType.OAK));
}
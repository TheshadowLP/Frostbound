package net.shadowbeast.projectshadow.util;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.shadowbeast.projectshadow.ProjectShadow;

public class ModWoodTypes {
    public static final WoodType FROZEN = WoodType.register(new WoodType(ProjectShadow.MOD_ID + ":frozen", BlockSetType.OAK));
}
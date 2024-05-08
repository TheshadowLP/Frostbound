package net.shadowbeast.projectshadow.items.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CompassItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import org.jetbrains.annotations.NotNull;
import javax.annotation.Nullable;
import java.util.List;

public class StrongholdCompass extends CompassItem {
    public StrongholdCompass(Properties builder) {
        super(builder);
    }
    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        tooltip.add(Component.translatable("item.projectshadow.stronghold_compass.description"));
    }
}
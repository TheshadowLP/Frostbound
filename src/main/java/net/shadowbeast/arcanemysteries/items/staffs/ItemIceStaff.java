package net.shadowbeast.arcanemysteries.items.staffs;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.shadowbeast.arcanemysteries.entities.projectile.EntityIceBeam;
import net.shadowbeast.arcanemysteries.util.LocalizeUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemIceStaff extends ItemStaff {

    public ItemIceStaff() {
        super(26, "ice", ChatFormatting.BLUE);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        if (!level.isClientSide) {
            EntityIceBeam projectile = new EntityIceBeam(level, player);

            projectile.setPos(player.getX(), player.getEyeY() - 0.1, player.getZ());

            projectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);

            level.addFreshEntity(projectile);
        }
        return InteractionResultHolder.success(player.getItemInHand(hand));
    }
}


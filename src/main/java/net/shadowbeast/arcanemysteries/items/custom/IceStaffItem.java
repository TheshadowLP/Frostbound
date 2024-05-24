package net.shadowbeast.arcanemysteries.items.custom;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.shadowbeast.arcanemysteries.entity.custom.IceBeamProjectileEntity;
import net.shadowbeast.arcanemysteries.items.ModItems;
import net.shadowbeast.arcanemysteries.particle.ModParticles;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class IceStaffItem extends Item {
    public IceStaffItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide) {
            IceBeamProjectileEntity projectile = new IceBeamProjectileEntity(level, player);

            projectile.setPos(player.getX(), player.getEyeY() - 0.1, player.getZ());

            projectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);

            level.addFreshEntity(projectile);
        }

        return InteractionResultHolder.success(player.getItemInHand(hand));
    }

    @Override
    public boolean isEnchantable(@NotNull ItemStack pStack) {
        return false;
    }
    @Override
    public boolean isRepairable(@NotNull ItemStack stack) {
        return false;
    }
    @Override
    public boolean isValidRepairItem(@NotNull ItemStack pStack, @NotNull ItemStack pRepairCandidate) {
        return false;
    }
    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }
    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        if(Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("tooltip.arcanemysteries.ice_staff.shift"));
        } else {
            pTooltipComponents.add(Component.translatable("tooltip.arcanemysteries.shift_for_info"));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}

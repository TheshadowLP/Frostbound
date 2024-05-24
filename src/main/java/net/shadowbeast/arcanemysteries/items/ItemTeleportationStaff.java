package net.shadowbeast.arcanemysteries.items;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.server.command.TextComponentHelper;
import net.shadowbeast.arcanemysteries.util.LocalizeUtils;
import net.shadowbeast.arcanemysteries.util.teleport.DimensionTeleporter;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
public class ItemTeleportationStaff extends Item{
    public ItemTeleportationStaff() {
        super(new Properties().durability(8).rarity(Rarity.RARE));
    }
    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level world, @NotNull Player player, @NotNull InteractionHand hand){
        if (!world.isClientSide) {
            if (player instanceof ServerPlayer) {
                BlockPos respawnPos = ((ServerPlayer) player).getRespawnPosition();
                if (respawnPos != null) {
                    ResourceKey<Level> respawnDimension = ((ServerPlayer) player).getRespawnDimension();
                    player.changeDimension(Objects.requireNonNull(Objects.requireNonNull(world.getServer()).getLevel(respawnDimension)), new DimensionTeleporter(world.getServer().getLevel(respawnDimension)));
                    ItemStack stack = player.getItemInHand(hand);
                    if (!player.isCreative()) {
                        stack.hurtAndBreak(1, player, (p_220009_1_) -> p_220009_1_.broadcastBreakEvent(player.getUsedItemHand()));
                    }
                    player.level().playSound(null, player.blockPosition(), SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.PLAYERS);
                    player.playNotifySound(SoundEvents.ALLAY_THROW, SoundSource.PLAYERS, 0.8f, 1f);
                    player.getCooldowns().addCooldown(stack.getItem(), 160);
                    return InteractionResultHolder.success(player.getItemInHand(hand));
                } else {
                    MutableComponent message = TextComponentHelper.createComponentTranslation(player, "message.failure_to_teleport");
                    message.withStyle(ChatFormatting.RED);
                    player.displayClientMessage(message, true);
                    player.playNotifySound(SoundEvents.BEACON_DEACTIVATE, SoundSource.PLAYERS, 1.2f, 1f);
                }
            }
        }
        return InteractionResultHolder.fail(player.getItemInHand(hand));
    }
    @Override
    public boolean isEnchantable(@NotNull ItemStack pStack) {return false;}
    @Override
    public boolean isRepairable(@NotNull ItemStack stack) {return false;}
    @Override
    public boolean isValidRepairItem(@NotNull ItemStack pStack, @NotNull ItemStack pRepairCandidate) {return false;}
    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {return false;}
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        tooltip.add(LocalizeUtils.i18n("tooltip.teleport_respawn_point"));
        tooltip.add(LocalizeUtils.usesRemaining(stack.getMaxDamage() - stack.getDamageValue()));
    }
}

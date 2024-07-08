package net.shadowbeast.arcanemysteries.items.staffs;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraftforge.server.command.TextComponentHelper;
import net.shadowbeast.arcanemysteries.util.teleport.DimensionTeleporter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ItemTeleportationStaff extends ItemStaff {

    public ItemTeleportationStaff() {
        super(8, "teleport_respawn_point", Rarity.RARE);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level world, @NotNull Player player, @NotNull InteractionHand hand){
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
                    if (!player.getAbilities().instabuild)
                        player.getCooldowns().addCooldown(this, 160);
                    //player.getCooldowns().addCooldown(stack.getItem(), 160);
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
}

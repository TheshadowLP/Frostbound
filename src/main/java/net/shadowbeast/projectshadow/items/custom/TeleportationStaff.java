package net.shadowbeast.projectshadow.items.custom;


import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class TeleportationStaff extends Item {
    public TeleportationStaff(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean isEnchantable(@NotNull ItemStack pStack) { return false; }

    @Override
    public boolean isDamageable(ItemStack stack) { return true; }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {
        if(!pLevel.isClientSide && pPlayer instanceof ServerPlayer serverPlayerEntity) {
            ServerLevel serverWorld = (ServerLevel)  pLevel;
            pPlayer.getCooldowns().addCooldown(this, 1200); //1 mun

            if (serverPlayerEntity.getRespawnPosition() != null) {
                Optional<Vec3> respawnPosition = Player.findRespawnPositionAndUseSpawnBlock(
                        serverWorld,
                        serverPlayerEntity.getRespawnPosition(),
                        serverPlayerEntity.getYRot(),
                        serverPlayerEntity.isRespawnForced(),
                        true
                );

                if (respawnPosition.isPresent()) {
                    ServerLevel respawnWorld = serverWorld.getServer().getLevel(serverPlayerEntity.getRespawnDimension());

                    if (respawnWorld != null) {
                        serverPlayerEntity.teleportTo(
                                respawnWorld,
                                respawnPosition.get().x,
                                respawnPosition.get().y,
                                respawnPosition.get().z,
                                serverPlayerEntity.getYRot(),
                                serverPlayerEntity.getXRot());
                        serverPlayerEntity.level().playSound(null, serverPlayerEntity.blockPosition(), SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.PLAYERS);
                        serverPlayerEntity.playNotifySound(SoundEvents.ALLAY_HURT, SoundSource.PLAYERS, 1f, 1f);
                    }
                } else {
                    serverPlayerEntity.displayClientMessage(Component.translatable("teleportationstaff.projectshadow.failure_to_teleport"), false);
                    serverPlayerEntity.playNotifySound(SoundEvents.ALLAY_DEATH, SoundSource.PLAYERS, 1f, 1f);
                }
            } else {
                serverPlayerEntity.displayClientMessage(Component.translatable("teleportationstaff.projectshadow.failure_to_teleport"), false);
                serverPlayerEntity.playNotifySound(SoundEvents.ALLAY_DEATH, SoundSource.PLAYERS, 1f, 1f);
            }
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }

}

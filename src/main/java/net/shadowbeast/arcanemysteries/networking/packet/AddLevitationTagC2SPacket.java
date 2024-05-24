package net.shadowbeast.arcanemysteries.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.shadowbeast.arcanemysteries.levitation_staff.PlayerLevitationTagProvider;

import java.util.function.Supplier;

public class AddLevitationTagC2SPacket {

    public AddLevitationTagC2SPacket() {

    }

    public AddLevitationTagC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE SERVER!
            ServerPlayer player = context.getSender();
            ServerLevel level = player.serverLevel();
                // increase the water level / thirst level of player
                player.getCapability(PlayerLevitationTagProvider.PLAYER_THIRST).ifPresent(levitationTag -> {
                    levitationTag.setLevitationTagged(true);
                });

                // Output the current thirst level
        });
        return true;
    }
}

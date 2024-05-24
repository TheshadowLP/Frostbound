package net.shadowbeast.arcanemysteries.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.shadowbeast.arcanemysteries.client.ClientLevitationData;

import java.util.function.Supplier;

public class LevitationDataSyncS2CPacket {
    private final boolean levitationTagged;

    public LevitationDataSyncS2CPacket(boolean levitationTagged) {
        this.levitationTagged = levitationTagged;
    }

    public LevitationDataSyncS2CPacket(FriendlyByteBuf buf) {
        this.levitationTagged = buf.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBoolean(levitationTagged);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE CLIENT!
            ClientLevitationData.set(levitationTagged);
        });
        return true;
    }
}

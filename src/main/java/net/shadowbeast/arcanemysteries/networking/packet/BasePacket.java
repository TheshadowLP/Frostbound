package net.shadowbeast.arcanemysteries.networking.packet;


import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.function.Supplier;

public abstract class BasePacket {
    protected SimpleChannel channel;

    public BasePacket(SimpleChannel channel) {
        this.channel = channel;
    }

    public BasePacket(FriendlyByteBuf packetBuffer, SimpleChannel channel) {
        this.channel = channel;
    }

    public abstract void encode(FriendlyByteBuf paramFriendlyByteBuf);

    public abstract void message(Supplier<NetworkEvent.Context> paramSupplier);
}

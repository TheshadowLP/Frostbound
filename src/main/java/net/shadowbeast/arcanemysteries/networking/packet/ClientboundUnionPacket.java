package net.shadowbeast.arcanemysteries.networking.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.simple.SimpleChannel;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;
import org.apache.commons.lang3.mutable.MutableBoolean;

import java.util.function.Supplier;

public abstract class ClientboundUnionPacket extends BasePacket {

    public ClientboundUnionPacket(SimpleChannel channel) {
        super(channel);
    }

    public ClientboundUnionPacket(FriendlyByteBuf packetBuffer, SimpleChannel channel) {
        super(packetBuffer, channel);
    }

    public void message(Supplier<NetworkEvent.Context> contextSupplier) {
        MutableBoolean wasHandled = new MutableBoolean();

        if (shouldRun()) {
            NetworkEvent.Context context = contextSupplier.get();
            context.enqueueWork(() -> wasHandled.setValue(
                    DistExecutor.unsafeCallWhenOn(Dist.CLIENT, () -> () -> {
                        assert Minecraft.getInstance().player != null;
                        return handleOnClient(Minecraft.getInstance().player);
                    })
            ));
            context.setPacketHandled(true);

            if (verifyIfHandled()) {
                ArcaneMysteries.LOGGER.info("Packet Handled: {}", wasHandled.booleanValue());
            }
        }
    }

    public boolean verifyIfHandled() { return false; }

    public boolean shouldRun() { return true; }

    public void send(ServerPlayer playerEntity) {
        this.channel.sendTo(this, playerEntity.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
    }

    public void send(ServerLevel world) {
        for (ServerPlayer playerEntity : world.players()) {
            send(playerEntity);
        }
    }

    public void send(MinecraftServer server) {
        for (ServerPlayer playerEntity : server.getPlayerList().getPlayers()) {
            send(playerEntity);
        }
    }

    public abstract boolean handleOnClient(LocalPlayer localPlayer);
}

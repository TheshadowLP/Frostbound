package net.shadowbeast.arcanemysteries.networking;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;
import net.shadowbeast.arcanemysteries.networking.packet.ClientboundDataTransferPacket;
import net.shadowbeast.arcanemysteries.networking.packet.ClientboundStatsPacket;

public class MessagesMod {
    public static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(ArcaneMysteries.MOD_ID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }
    public static void register() {
        INSTANCE.messageBuilder(ClientboundStatsPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(ClientboundStatsPacket::new)
                .encoder(ClientboundStatsPacket::encode)
                .consumerMainThread(ClientboundStatsPacket::message)
                .add();

        INSTANCE.messageBuilder(ClientboundDataTransferPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(ClientboundDataTransferPacket::new)
                .encoder(ClientboundDataTransferPacket::encode)
                .consumerMainThread(ClientboundDataTransferPacket::message)
                .add();
    }
    public static void registerPackets() {
        int id = 0;
        INSTANCE.registerMessage(id++, ClientboundStatsPacket.class, ClientboundStatsPacket::encode, ClientboundStatsPacket::new, ClientboundStatsPacket::message);
        INSTANCE.registerMessage(id++, ClientboundDataTransferPacket.class, ClientboundDataTransferPacket::encode, ClientboundDataTransferPacket::new, ClientboundDataTransferPacket::message);
    }
    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }
    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    public static <MSG> void sendToClients(MSG message) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), message);
    }
}
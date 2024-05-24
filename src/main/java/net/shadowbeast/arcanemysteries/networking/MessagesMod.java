package net.shadowbeast.arcanemysteries.networking;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;
import net.shadowbeast.arcanemysteries.networking.packet.AddLevitationTagC2SPacket;
import net.shadowbeast.arcanemysteries.networking.packet.LevitationDataSyncS2CPacket;

public class MessagesMod {
    private static SimpleChannel INSTANCE;
    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }
    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(ArcaneMysteries.MODID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(LevitationDataSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(LevitationDataSyncS2CPacket::new)
                .encoder(LevitationDataSyncS2CPacket::toBytes)
                .consumerMainThread(LevitationDataSyncS2CPacket::handle)
                .add();

        net.messageBuilder(AddLevitationTagC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(AddLevitationTagC2SPacket::new)
                .encoder(AddLevitationTagC2SPacket::toBytes)
                .consumerMainThread(AddLevitationTagC2SPacket::handle)
                .add();
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
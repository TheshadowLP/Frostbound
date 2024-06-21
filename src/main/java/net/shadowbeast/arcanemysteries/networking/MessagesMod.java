package net.shadowbeast.arcanemysteries.networking;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.server.ServerLifecycleHooks;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;
import net.shadowbeast.arcanemysteries.networking.packet.ClientboundDataTransferPacket;
import net.shadowbeast.arcanemysteries.networking.packet.ClientboundStatsPacket;
@Mod.EventBusSubscriber(modid = ArcaneMysteries.MOD_ID)
public class MessagesMod {

    public static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel NETWORK_WRAPPER;
    private static int packetId = 0;

    public static int id(){
        return packetId++;
    }
    static {
        NETWORK_WRAPPER = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(ArcaneMysteries.MOD_ID, "main_channel"))
                .clientAcceptedVersions(PROTOCOL_VERSION::equals)
                .serverAcceptedVersions(PROTOCOL_VERSION::equals)
                .networkProtocolVersion(() -> PROTOCOL_VERSION)
                .simpleChannel();
    }

    public static void registerPackets() {
        NETWORK_WRAPPER.messageBuilder(ClientboundStatsPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(ClientboundStatsPacket::new)
                .encoder(ClientboundStatsPacket::encode)
                .consumerMainThread(ClientboundStatsPacket::message)
                .add();

//        NETWORK_WRAPPER.messageBuilder(AddLevitationTagC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
//                .decoder(AddLevitationTagC2SPacket::new)
//                .encoder(AddLevitationTagC2SPacket::toBytes)
//                .consumerMainThread(AddLevitationTagC2SPacket::handle)
//                .add();

        NETWORK_WRAPPER.messageBuilder(ClientboundDataTransferPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(ClientboundDataTransferPacket::new)
                .encoder(ClientboundDataTransferPacket::encode)
                .consumerMainThread(ClientboundDataTransferPacket::message)
                .add();
//        NETWORK_WRAPPER.messageBuilder(LevitationDataSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
//                .decoder(LevitationDataSyncS2CPacket::new)
//                .encoder(LevitationDataSyncS2CPacket::toBytes)
//                .consumerMainThread(LevitationDataSyncS2CPacket::handle)
//                .add();
    }

    public static <MSG> void sendMSGToServer(MSG message) {
        NETWORK_WRAPPER.sendToServer(message);
    }

    public static <MSG> void sendMSGToAll(MSG message) {
        ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayers().forEach(player ->
                NETWORK_WRAPPER.sendTo(message, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT));
    }

    public static <MSG> void sendMSGToPlayer(MSG message, ServerPlayer player) {
        NETWORK_WRAPPER.sendTo(message, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
    }
}
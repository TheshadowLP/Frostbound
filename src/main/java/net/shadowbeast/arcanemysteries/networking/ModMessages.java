package net.shadowbeast.arcanemysteries.networking;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;
import net.shadowbeast.arcanemysteries.networking.packet.*;

public class ModMessages {
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

    }
    public static void registerPackets(){
        int id = 0;
        INSTANCE.registerMessage(id++, TemperatureSyncPacket.class,TemperatureSyncPacket::encode,TemperatureSyncPacket::decode,TemperatureSyncPacket::handle);
        INSTANCE.registerMessage(id++, TempModifiersSyncPacket.class,TempModifiersSyncPacket::encode,TempModifiersSyncPacket::decode,TempModifiersSyncPacket::handle);
        INSTANCE.registerMessage(id++, SyncForgeDataMessage.class,SyncForgeDataMessage::encode,SyncForgeDataMessage::decode,SyncForgeDataMessage::handle);
        INSTANCE.registerMessage(id++, SyncConfigSettingsMessage.class, SyncConfigSettingsMessage::encode,SyncConfigSettingsMessage::decode,SyncConfigSettingsMessage::handle);
        INSTANCE.registerMessage(id++, SyncPreferredUnitsMessage.class,SyncPreferredUnitsMessage::encode,SyncPreferredUnitsMessage::decode,SyncPreferredUnitsMessage::handle);
        INSTANCE.registerMessage(id++, ClientConfigAskMessage.class,ClientConfigAskMessage::encode,ClientConfigAskMessage::decode,ClientConfigAskMessage::handle);
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
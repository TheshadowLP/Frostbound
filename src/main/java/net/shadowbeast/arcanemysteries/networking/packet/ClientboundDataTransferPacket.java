package net.shadowbeast.arcanemysteries.networking.packet;

import com.google.common.collect.ImmutableMap;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;
import net.shadowbeast.arcanemysteries.json.DataMaps;
import net.shadowbeast.arcanemysteries.util.BiomeJsonHolder;
import net.shadowbeast.arcanemysteries.util.JsonHolder;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ClientboundDataTransferPacket extends ClientboundUnionPacket{
    private final ResourceLocation stat;
    private final JsonHolder settings;
    private final boolean clear;

    public ClientboundDataTransferPacket(final ResourceLocation statIn, final JsonHolder settingsIn, final boolean clear) {
        super(ArcaneMysteries.getInstance().channel);
        this.stat = statIn;
        this.settings = settingsIn;
        this.clear = clear;
    }

    public ClientboundDataTransferPacket(FriendlyByteBuf byteBuf) {
        super(byteBuf, ArcaneMysteries.getInstance().channel);
        this.stat = byteBuf.readResourceLocation();
        String cl = byteBuf.readUtf();
        this.settings = JsonHolder.deserialize(Objects.requireNonNull(byteBuf.readNbt()), Objects.requireNonNull(JsonHolder.HOLD.get(cl)));
        this.clear = byteBuf.readBoolean();
    }

    @Override
    public void encode(final @NotNull FriendlyByteBuf byteBuf) {
        byteBuf.writeResourceLocation(this.stat);
        byteBuf.writeUtf(this.settings.getClass().descriptorString());
        byteBuf.writeNbt(this.settings.serialize());
        byteBuf.writeBoolean(this.clear);
    }

    @Override
    public boolean handleOnClient(LocalPlayer sender) {
        if (settings instanceof BiomeJsonHolder) {
            if (this.clear) {
                ArcaneMysteries.LOGGER.info("Clearing client side biome data");
                DataMaps.Client.biome = ImmutableMap.of();
            }
            Map<ResourceLocation, BiomeJsonHolder> statMap = new HashMap<>(DataMaps.Client.biome);
            statMap.put(stat, (BiomeJsonHolder) settings);
            DataMaps.Client.biome = ImmutableMap.copyOf(statMap);
        }
        return true;
    }
}

package net.shadowbeast.arcanemysteries.networking.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.network.NetworkEvent;
import net.shadowbeast.arcanemysteries.temprature.caps.TemperatureCap;
import net.shadowbeast.arcanemysteries.temprature.util.EntityTempManager;

import java.util.function.Supplier;

public class TemperatureSyncPacket
{
    int entityId;
    CompoundTag traits;
    boolean instant;

    public TemperatureSyncPacket(LivingEntity entity, CompoundTag traits, boolean instant)
    {   this.entityId = entity.getId();
        this.traits = traits;
        this.instant = instant;
    }

    TemperatureSyncPacket(int entityId, CompoundTag traits, boolean instant)
    {   this.entityId = entityId;
        this.traits = traits;
        this.instant = instant;
    }

    public static void encode(TemperatureSyncPacket message, FriendlyByteBuf buffer)
    {   buffer.writeInt(message.entityId);
        buffer.writeNbt(message.traits);
        buffer.writeBoolean(message.instant);
    }

    public static TemperatureSyncPacket decode(FriendlyByteBuf buffer)
    {   return new TemperatureSyncPacket(buffer.readInt(), buffer.readNbt(), buffer.readBoolean());
    }

    public static void handle(TemperatureSyncPacket message, Supplier<NetworkEvent.Context> contextSupplier)
    {
        NetworkEvent.Context context = contextSupplier.get();

        if (context.getDirection().getReceptionSide().isClient())
        {
            context.enqueueWork(() ->
            {
                LivingEntity entity = (LivingEntity) Minecraft.getInstance().level.getEntity(message.entityId);

                if (entity != null)
                {
                    EntityTempManager.getTemperatureCap(entity).ifPresent(cap ->
                    {
                        cap.deserializeTraits(message.traits);
                        if (message.instant && cap instanceof TemperatureCap)
                        {

                        }
                    });
                }
            });
        }

        context.setPacketHandled(true);
    }
}
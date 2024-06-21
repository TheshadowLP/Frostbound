package net.shadowbeast.arcanemysteries.mod;

import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.ChunkEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.shadowbeast.arcanemysteries.util.insert.Inserts;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class PlatformHelper {
    public static boolean isClientInstance() {
        return FMLEnvironment.dist.isClient();
    }
    public static void handleEvents() {
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        forgeBus.addListener(PlatformHelper::onWorldLoad);
        forgeBus.addListener(PlatformHelper::onLivingTick);
        forgeBus.addListener(PlatformHelper::onPlayerLogOut);
    }

    private static void onWorldLoad(ChunkEvent.Load event) {
        if (event.getLevel() instanceof ServerLevel) {
            Inserts.LEVEL_LOAD.insert(event.getLevel());
        }
    }

    private static void onLivingTick(LivingEvent.LivingTickEvent event) {
        Inserts.LIVING_TICK.insert(event.getEntity());
    }
    private static void onPlayerLogOut(PlayerEvent.PlayerLoggedOutEvent event) { // Add this method
        Inserts.LOGGED_OUT.insert(event.getEntity());
    }
}

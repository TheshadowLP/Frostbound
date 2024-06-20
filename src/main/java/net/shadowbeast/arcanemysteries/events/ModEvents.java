package net.shadowbeast.arcanemysteries.events;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;
import net.shadowbeast.arcanemysteries.core.ArcaneRegistries;
import net.shadowbeast.arcanemysteries.temprature.condition.TemperatureChangeCondition;
import net.shadowbeast.arcanemysteries.temprature.condition.TemperatureChangeConditions;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents 
{
    @SubscribeEvent
    public static void registerParticlesz(final RegisterEvent event)
   {
        event.register(ArcaneRegistries.CONDITION, (p_23423)-> TemperatureChangeConditions.registerAll(p_23423));
   }
}

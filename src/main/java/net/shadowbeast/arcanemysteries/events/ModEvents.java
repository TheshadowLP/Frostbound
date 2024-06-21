package net.shadowbeast.arcanemysteries.events;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;
import net.shadowbeast.arcanemysteries.core.ArcaneRegistries;
import net.shadowbeast.arcanemysteries.temprature.condition.TemperatureChangeConditions;
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents 
{
    @SubscribeEvent
    public static void registerParticlesz(final RegisterEvent event)
   {
        event.register(ArcaneRegistries.CONDITION, (p_23423)-> TemperatureChangeConditions.registerAll(p_23423));
   }
}

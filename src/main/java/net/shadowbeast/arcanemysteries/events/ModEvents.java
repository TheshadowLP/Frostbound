package net.shadowbeast.arcanemysteries.events;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryBuilder;
import net.shadowbeast.arcanemysteries.core.ArcaneRegistries;
import net.shadowbeast.arcanemysteries.temprature.condition.TemperatureChangeCondition;
import net.shadowbeast.arcanemysteries.temprature.condition.TemperatureChangeConditions;
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents 
{
    private static final int MAX_VARINT = Integer.MAX_VALUE - 1;

    @SubscribeEvent
    public static void registerParticlesz(final RegisterEvent event)
   {
       event.register(ArcaneRegistries.CONDITION, TemperatureChangeConditions::registerAll);
   }
    @SubscribeEvent
    public static void registerSurviveRegistries(final NewRegistryEvent event) {
        event.create(new RegistryBuilder<TemperatureChangeCondition<?>>().setName(ArcaneRegistries.CONDITION.location()).setMaxID(MAX_VARINT));
    }
}

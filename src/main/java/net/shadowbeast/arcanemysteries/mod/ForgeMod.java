package net.shadowbeast.arcanemysteries.mod;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public interface ForgeMod {
    default IEventBus eventBus() {
        return FMLJavaModLoadingContext.get().getModEventBus();
    }
    String getMod_id();

}


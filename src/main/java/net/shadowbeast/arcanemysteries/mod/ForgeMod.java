package net.shadowbeast.arcanemysteries.mod;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.shadowbeast.arcanemysteries.util.insert.Inserts;

public interface ForgeMod {
    default IEventBus eventBus() {
        return FMLJavaModLoadingContext.get().getModEventBus();
    }


    String getModid();

}


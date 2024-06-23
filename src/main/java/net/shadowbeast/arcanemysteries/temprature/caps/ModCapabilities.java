package net.shadowbeast.arcanemysteries.temprature.caps;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.shadowbeast.arcanemysteries.temprature.interfaces.ITemperature;

public class ModCapabilities
{
    public static final Capability<ITemperature> PLAYER_TEMPERATURE = CapabilityManager.get(new CapabilityToken<>() {});
    public static final Capability<ITemperature> ENTITY_TEMPERATURE = CapabilityManager.get(new CapabilityToken<>() {});
}

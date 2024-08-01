package net.shadowbeast.frostbound.temprature.caps;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class EntityTempCap extends AbsTemperatureCapability
{
    @Override
    public void tickHurting(LivingEntity entity, double heatResistance, double coldResistance)
    {}

    @Override
    public void syncValues(LivingEntity entity)
    {
        super.syncValues(entity);
        if (!(entity instanceof Player))
        {   this.syncTimer = 40;
        }
    }
}
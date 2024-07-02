package net.shadowbeast.arcanemysteries.temprature.caps;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.shadowbeast.arcanemysteries.config.ConfigSettings;
import net.shadowbeast.arcanemysteries.temprature.Temperature;

public class TemperatureCap extends AbsTemperatureCapability
{
    @Override
    public void tickHurting(LivingEntity entity, double heatResistance, double coldResistance)
    {
        if ((!(entity instanceof Player player) || !player.isCreative()) && !entity.isSpectator())
        {   super.tickHurting(entity, heatResistance, coldResistance);
        }
    }

    @Override
    public void tick(LivingEntity entity)
    {
        super.tick(entity);
        if (entity instanceof Player player)
        {
            if (player.tickCount % 20 == 0)
            {   calculateHudVisibility(player);
            }
            if (player.isCreative())
            {   this.setTrait(Temperature.Trait.CORE, 0);
            }
        }
    }

    @Override
    public void tickDummy(LivingEntity entity)
    {
        super.tickDummy(entity);
    }

    public void calculateHudVisibility(Player player)
    {
        showWorldTemp = !ConfigSettings.REQUIRE_THERMOMETER.get()
                || player.isCreative();
        showBodyTemp = !player.isCreative() && !player.isSpectator();
    }
}

package net.shadowbeast.frostbound.api.event;

import net.minecraft.world.entity.EntityType;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.event.IModBusEvent;

@Cancelable
public class EnableTemperatureEvent extends Event implements IModBusEvent
{
    final EntityType<?> entityType;
    boolean enabled = false;

    public EnableTemperatureEvent(EntityType<?> entityType)
    {   this.entityType = entityType;
    }

    public EntityType<?> getEntityType()
    {   return entityType;
    }

    public void setEnabled(boolean enabled)
    {   this.enabled = enabled;
    }

    public boolean isEnabled()
    {   return enabled;
    }
}

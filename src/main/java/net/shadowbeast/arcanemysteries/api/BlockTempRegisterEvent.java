package net.shadowbeast.arcanemysteries.api;

import net.minecraftforge.eventbus.api.Event;
import net.shadowbeast.arcanemysteries.registries.BlockTempRegistry;
import net.shadowbeast.arcanemysteries.temprature.temp.BlockTemp;

/**
 * Fired when the {@link BlockTemp} registry is being built ({@link BlockTempRegistry}). <br>
 * The event is fired during {@link net.minecraftforge.event.level.LevelEvent.Load}. <br>
 * <br>
 * Use {@code BlockTempRegistry.flush()} if calling manually to prevent duplicates. <br>
 * (You probably shouldn't ever do that anyway) <br>
 * <br>
 * This event is not {@link net.minecraftforge.eventbus.api.Cancelable}. <br>
 * <br>
 */
public class BlockTempRegisterEvent extends Event
{
    /**
     * Adds a new {@link BlockTemp} to the registry.
     *
     * @param blockTemp The BlockTemp to add.
     */
    public void register(BlockTemp blockTemp)
    {
        BlockTempRegistry.register(blockTemp);
    }
}

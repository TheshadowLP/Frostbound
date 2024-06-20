package net.shadowbeast.arcanemysteries.util;

import net.shadowbeast.arcanemysteries.mod.MinecraftMod;

public abstract class AbstractSegment
{
    private boolean hasInitialized = false;

    protected MinecraftMod owner;

    public void setOwner(MinecraftMod mod) {
        this.owner = mod;
    }
    public boolean hasInitialized() {
        return this.hasInitialized;
    }

    protected void setInitialized() {
        this.hasInitialized = true;
    }
}

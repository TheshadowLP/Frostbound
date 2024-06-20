package net.shadowbeast.arcanemysteries.util;


import net.minecraft.server.MinecraftServer;

public class ServerSegment extends AbstractSegment {

    public final void setupServerBeforeMinecraft(MinecraftServer mc) {
        if (!hasInitialized()) {
            initServerBeforeMinecraft(mc);
            setInitialized();
        }
    }

    public void initServerBeforeMinecraft(MinecraftServer mc) {}
}
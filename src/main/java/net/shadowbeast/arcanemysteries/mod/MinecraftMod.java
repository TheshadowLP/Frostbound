package net.shadowbeast.arcanemysteries.mod;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.simple.SimpleChannel;
import net.shadowbeast.arcanemysteries.util.ServerSegment;
import net.shadowbeast.arcanemysteries.util.SetupClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

public class MinecraftMod implements ForgeMod
{
    private String modid;

    private LoadType loadType;

    private SetupClient clientSegment;

    private ServerSegment serverSegment;

    public SimpleChannel channel = null;

    public LoadType getEnv(Supplier<SetupClient> clientSegment, Supplier<ServerSegment> serverSegment) {
        if (clientSegment == null && serverSegment != null) {
            this.serverSegment = serverSegment.get();
            return LoadType.SERVER;
        }
        if (clientSegment != null && serverSegment == null) {
            if (PlatformHelper.isClientInstance()) {
                this.clientSegment = clientSegment.get();
                this.clientSegment.setOwner(this);
            }
            return LoadType.CLIENT;
        }
        if (clientSegment != null && serverSegment != null) {
            this.serverSegment = serverSegment.get();
            this.serverSegment.setOwner(this);
            if (PlatformHelper.isClientInstance()) {
                this.clientSegment = clientSegment.get();
                this.clientSegment.setOwner(this);
            }
            return LoadType.BOTH;
        }
        System.err.println(this.modid + " does not have any segments attached. This will cause errors");
        return null;
    }

    public MinecraftMod(String modid, Supplier<SetupClient> clientSegment, Supplier<ServerSegment> serverSegment, boolean shouldLoadMod) {
        this.modid = modid;
        this.loadType = getEnv(clientSegment, serverSegment);
        if (shouldLoadMod ) {

            if (PlatformHelper.isClientInstance()){}
        }
    }



    public MinecraftMod(String modid, Supplier<SetupClient> clientSegment, Supplier<ServerSegment> serverSegment) {
        this(modid, clientSegment, serverSegment, true);
    }

    public LoadType getLoadType() {
        return this.loadType;
    }

    public String getModid() {
        return this.modid;
    }

    public Logger getLogger() {
        return LogManager.getLogger(this.modid);
    }

    public ResourceLocation location(String name) {
        return new ResourceLocation(this.modid, name);
    }

    public String locationString(String name) {
        return this.modid + ":" + this.modid;
    }

    public SetupClient getClientSegment() {
        return this.clientSegment;
    }

    public ServerSegment getServerSegment() {
        return this.serverSegment;
    }

    public enum LoadType {
        CLIENT, BOTH, SERVER;

        public boolean onClient() {
            return (this == CLIENT || this == BOTH);
        }

        public boolean onServer() {
            return (this == SERVER || this == BOTH);
        }
    }
}

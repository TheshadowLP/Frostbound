package net.shadowbeast.arcanemysteries.mod;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.simple.SimpleChannel;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;
import net.shadowbeast.arcanemysteries.util.InsertCollector;
import net.shadowbeast.arcanemysteries.util.ServerSegment;
import net.shadowbeast.arcanemysteries.util.SetupClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

public class MinecraftMod implements ForgeMod
{
    private final String mod_id;

    private final LoadType loadType;

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
        if (clientSegment != null) {
            this.serverSegment = serverSegment.get();
            this.serverSegment.setOwner(this);
            if (PlatformHelper.isClientInstance()) {
                this.clientSegment = clientSegment.get();
                this.clientSegment.setOwner(this);
            }
            return LoadType.BOTH;
        }
        ArcaneMysteries.LOGGER.error("{} does not have any segments attached. This will cause errors!", this.mod_id);
        return null;
    }

    public MinecraftMod(String mod_id, Supplier<SetupClient> clientSegment, Supplier<ServerSegment> serverSegment, boolean shouldLoadMod) {
        this.mod_id = mod_id;
        this.loadType = getEnv(clientSegment, serverSegment);
        if (shouldLoadMod ) {
            //FIXME no empty if statements
            if (PlatformHelper.isClientInstance()){}
        }
    }
    public void registerInserts(InsertCollector collector) {}


    public MinecraftMod(String mod_id, Supplier<SetupClient> clientSegment, Supplier<ServerSegment> serverSegment) {
        this(mod_id, clientSegment, serverSegment, true);
    }

    public LoadType getLoadType() {
        return this.loadType;
    }

    public String getMod_id() {
        return this.mod_id;
    }

    public Logger getLogger() {
        return LogManager.getLogger(this.mod_id);
    }

    public ResourceLocation location(String name) {
        return new ResourceLocation(this.mod_id, name);
    }

    public String locationString(String name) {
        return this.mod_id + ":" + this.mod_id;
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

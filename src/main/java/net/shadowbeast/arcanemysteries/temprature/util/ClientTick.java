package net.shadowbeast.arcanemysteries.temprature.util;

import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.Random;

public abstract class ClientTick {
    Random rng;
    public abstract void tick(Player player);
    public abstract void read(CompoundTag compound);
    public abstract void write(CompoundTag compound);
    public abstract void save(LivingEntity player);
    public abstract boolean shouldTick();

    public ClientTick() {
        this.rng = new Random();
    }

    public void baseTick(Player player) {
        if (shouldTick()) {
            tick(player);
            save(player);
        }
    }

    public void baseClientTick(AbstractClientPlayer player) {
        if (shouldTick()) {
            clientTick(player);
        }
    }

    public void clientTick(AbstractClientPlayer player) {

    }
}

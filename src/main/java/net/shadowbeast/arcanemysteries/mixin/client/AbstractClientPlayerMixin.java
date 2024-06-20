package net.shadowbeast.arcanemysteries.mixin.client;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.shadowbeast.arcanemysteries.interfaces.util.IRealisticEntity;
import net.shadowbeast.arcanemysteries.temprature.TemperatureData;
import org.spongepowered.asm.mixin.Mixin;


@Mixin(AbstractClientPlayer.class)
public abstract class AbstractClientPlayerMixin extends Player implements IRealisticEntity {
    public AbstractClientPlayerMixin(Level pLevel, BlockPos pPos, float pYRot, GameProfile pGameProfile) {
        super(pLevel, pPos, pYRot, pGameProfile);
    }

    @Override
    public void tick() {
        super.tick();
        AbstractClientPlayer player = (AbstractClientPlayer)(Object)this;
        getTemperatureData().baseClientTick(player);
    }
}

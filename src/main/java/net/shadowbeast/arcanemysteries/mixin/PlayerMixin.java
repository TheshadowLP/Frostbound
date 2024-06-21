package net.shadowbeast.arcanemysteries.mixin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.shadowbeast.arcanemysteries.interfaces.util.IRealisticEntity;
import net.shadowbeast.arcanemysteries.temprature.TemperatureData;
import net.shadowbeast.arcanemysteries.temprature.util.EStats;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity implements IRealisticEntity
{
    protected PlayerMixin(EntityType<? extends LivingEntity> type, Level worldIn) {
        super(type, worldIn);
    }

        @Inject(method = "tick", at = @At(value = "INVOKE", shift = At.Shift.AFTER, target = "Lnet/minecraft/world/entity/player/Player;updateIsUnderwater()Z"))
        public void tickInject(CallbackInfo ci){
        EStats.addStatsOnSpawn((Player) (Object) this);

        if (!this.level().isClientSide) {
            ArcaneMysteries$getTemperatureData().baseTick((Player) (Object) this);

        }
    }


    @Unique
    public TemperatureData ArcaneMysteries$getTemperatureData(){
        return EStats.getTemperatureStats((Player)(Object)this);
    }
}

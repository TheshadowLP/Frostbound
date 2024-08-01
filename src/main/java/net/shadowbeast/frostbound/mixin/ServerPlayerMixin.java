package net.shadowbeast.frostbound.mixin;

import net.minecraft.server.level.ServerPlayer;
import net.shadowbeast.frostbound.util.interfaces.IFallDamageCancelable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public class ServerPlayerMixin {
    @Unique
    ServerPlayer player = ((ServerPlayer)(Object)this);
    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/advancements/critereon/LevitationTrigger;trigger(Lnet/minecraft/server/level/ServerPlayer;Lnet/minecraft/world/phys/Vec3;I)V", shift = At.Shift.BEFORE), cancellable = true)
    private void cancelAdvancement(CallbackInfo ci) {
        if(((IFallDamageCancelable) player).shouldCancelFallDamage()) {
            ci.cancel();
        }
    }
}

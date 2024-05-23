package net.shadowbeast.projectshadow.mixin;

import net.minecraft.client.player.Input;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.PlayerRideableJumping;
import net.shadowbeast.projectshadow.entity.mob.custom.YakEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin {
    @Shadow @Nullable public abstract PlayerRideableJumping jumpableVehicle();

    @Shadow private int jumpRidingTicks;

    @Shadow public Input input;

    @Shadow protected abstract void sendRidingJump();

    @Shadow public abstract float getJumpRidingScale();

    @Shadow private float jumpRidingScale;

    @Inject(method = "aiStep", at = @At(value = "TAIL"))
    private void customBar(CallbackInfo ci) {
        PlayerRideableJumping playerrideablejumping = this.jumpableVehicle();
        if (playerrideablejumping instanceof YakEntity yak) {
            boolean flag = this.input.jumping;
            if (yak.getJumpCooldown() == 0) {
                if (this.jumpRidingTicks < 0) {
                    ++this.jumpRidingTicks;
                    if (this.jumpRidingTicks == 0) {
                        this.jumpRidingScale = 0.0F;
                    }
                }

                if (flag && !this.input.jumping) {
                    this.jumpRidingTicks = -10;
                    yak.onPlayerJump(Mth.floor(this.getJumpRidingScale() * 100.0F));
                    this.sendRidingJump();
                } else if (!flag && this.input.jumping) {
                    this.jumpRidingTicks = 0;
                    this.jumpRidingScale = 0.0F;
                } else if (flag) {
                    ++this.jumpRidingTicks;
                    if (this.jumpRidingTicks < 46) {
                        this.jumpRidingScale = (float)this.jumpRidingTicks * 0.03F;
                    } else {
                        this.jumpRidingTicks = 46;
                        this.jumpRidingScale = 10;
                    }
                }
            } else {
                this.jumpRidingScale = 0.0F;
            }
        }
    }
}

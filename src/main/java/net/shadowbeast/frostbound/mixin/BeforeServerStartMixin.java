package net.shadowbeast.frostbound.mixin;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.server.ServerLifecycleHooks;
import net.shadowbeast.frostbound.config.ConfigSettings;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerLifecycleHooks.class)
public class BeforeServerStartMixin {
    @Inject(method = "handleServerAboutToStart", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/server/ServerLifecycleHooks;runModifiers(Lnet/minecraft/server/MinecraftServer;)V"), remap = false)
    private static void beforeServerStart(@NotNull MinecraftServer server, CallbackInfoReturnable<Boolean> cir)
    { ConfigSettings.load(server.registryAccess()); }
}

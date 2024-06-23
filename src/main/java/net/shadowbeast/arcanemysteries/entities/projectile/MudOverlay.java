package net.shadowbeast.arcanemysteries.entities.projectile;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.shadowbeast.arcanemysteries.registries.EffectsRegistry;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class MudOverlay {

    private static final ResourceLocation MUD_OVERLAY_TEXTURE = new ResourceLocation("arcanemysteries:textures/gui/mud_overlay.png");

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void onRenderGuiPre(RenderGuiEvent.Pre event) {
        Player player = Minecraft.getInstance().player;
       if (player != null && player.hasEffect(EffectsRegistry.MUD_EFFECT.get())) {
           renderMudOverlay(event);
        }
    }

    private static void renderMudOverlay(RenderGuiEvent.Pre event) {
        int width = event.getWindow().getGuiScaledWidth();
        int height = event.getWindow().getGuiScaledHeight();

        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);

        event.getGuiGraphics().blit(MUD_OVERLAY_TEXTURE, 0, 0, 0, 0, width, height, width, height);

        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.disableBlend();
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask(true);
    }
}

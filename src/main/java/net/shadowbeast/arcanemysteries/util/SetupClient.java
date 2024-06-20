package net.shadowbeast.arcanemysteries.util;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;

public abstract class SetupClient extends AbstractSegment
{

        public void setupGuiOverlays(OverlayCollector collector) {}


        public void initClientAfterMinecraft(Minecraft mc) {}

        public abstract ResourceLocation getModIcon();

        public final void setupClientAfterMinecraft(Minecraft mc) {
            if (!hasInitialized()) {
                initClientAfterMinecraft(mc);
                setInitialized();
            }
        }
    }



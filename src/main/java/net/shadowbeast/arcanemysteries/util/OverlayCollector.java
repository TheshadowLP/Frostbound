package net.shadowbeast.arcanemysteries.util;

import net.minecraft.client.gui.Gui;

public interface OverlayCollector {
    void register(String paramString, Order paramOrder, Overlay paramOverlay);

    public enum Order {
        START, END;
    }

    public static interface Overlay {
        void render(Gui param1Gui, GuiRenderer param1GuiRenderer, int param1Int1, int param1Int2);
    }
}

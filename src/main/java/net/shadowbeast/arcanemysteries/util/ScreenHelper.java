package net.shadowbeast.arcanemysteries.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;


public class ScreenHelper
{
    public enum ScreenOffset {
        TOP_LEFT, TOP, TOP_RIGHT, LEFT, CENTER, RIGHT, BOTTOM_LEFT, BOTTOM, BOTTOM_RIGHT;
    }


        public static int getYOffset(ScreenOffset pos, Minecraft mc) {
            if (pos.equals(ScreenOffset.TOP_LEFT) || pos.equals(ScreenOffset.TOP) || pos.equals(ScreenOffset.TOP_RIGHT))
                return 0;
            if (pos.equals(ScreenOffset.LEFT) || pos.equals(ScreenOffset.CENTER) || pos.equals(ScreenOffset.RIGHT))
                return mc.getWindow().getGuiScaledHeight() / 2;
            if (pos.equals(ScreenOffset.BOTTOM_LEFT) || pos.equals(ScreenOffset.BOTTOM) || pos.equals(ScreenOffset.BOTTOM_RIGHT))
                return mc.getWindow().getGuiScaledHeight();
            return 0;
        }

        public static int getXOffset(ScreenOffset pos, Minecraft mc) {
            if (pos.equals(ScreenOffset.TOP_LEFT) || pos.equals(ScreenOffset.LEFT) || pos.equals(ScreenOffset.BOTTOM_LEFT))
                return 0;
            if (pos.equals(ScreenOffset.TOP) || pos.equals(ScreenOffset.CENTER) || pos.equals(ScreenOffset.BOTTOM))
                return mc.getWindow().getGuiScaledWidth() / 2;
            if (pos.equals(ScreenOffset.TOP_RIGHT) || pos.equals(ScreenOffset.RIGHT) || pos.equals(ScreenOffset.BOTTOM_RIGHT))
                return mc.getWindow().getGuiScaledWidth();
            return 0;
        }

        public static void setWidgetPosition(AbstractWidget widget, int x, int y) {
            widget.setY(y);
            widget.setX(x);
        }

        public static void setWidgetY(AbstractWidget widget, int y) {
            widget.setY(y);
        }

        public static void setWidgetX(AbstractWidget widget, int x) {
            widget.setX(x);
        }

        public static int getWidgetY(AbstractWidget widget) {
            return widget.getY();
        }

        public static int getWidgetX(AbstractWidget widget) {
            return widget.getX();
        }

        public static Button.Builder buttonBuilder(Component message, Button.OnPress onPress) {
            return new Button.Builder(message, onPress);
        }
    }



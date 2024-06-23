package net.shadowbeast.arcanemysteries.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.shadowbeast.arcanemysteries.config.ConfigSettings;
import net.shadowbeast.arcanemysteries.core.ClientSettingConfig;
import net.shadowbeast.arcanemysteries.networking.ModMessages;
import net.shadowbeast.arcanemysteries.networking.packet.SyncConfigSettingsMessage;
import net.shadowbeast.arcanemysteries.networking.packet.SyncPreferredUnitsMessage;
import net.shadowbeast.arcanemysteries.temprature.Temperature;
import net.shadowbeast.arcanemysteries.util.MathHelper;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class ConfigScreen
{
    public static final int TITLE_HEIGHT = 16;
    public static final int BOTTOM_BUTTON_HEIGHT_OFFSET = 26;
    public static final int OPTION_SIZE = 25;
    public static final int BOTTOM_BUTTON_WIDTH = 150;

    public static Minecraft MC = Minecraft.getInstance();

    public static DecimalFormat TWO_PLACES = new DecimalFormat("#.##");

    public static boolean IS_MOUSE_DOWN = false;
    public static int MOUSE_X = 0;
    public static int MOUSE_Y = 0;

    static List<Function<Screen, AbstractConfigPage>> PAGES = new ArrayList<>(Arrays.asList(ConfigPageOne::new, ConfigPageTwo::new, ConfigPageThree::new));
    public static int FIRST_PAGE = 0;
    public static int LAST_PAGE = PAGES.size() - 1;
    public static int CURRENT_PAGE = 0;

    public static final Supplier<Integer> SHIFT_AMOUNT = () -> Screen.hasShiftDown() && Screen.hasControlDown() ? 100 : Screen.hasShiftDown() ? 10 : 1;

    public static Screen getPage(int index, Screen parentScreen)
    {   return PAGES.get(MathHelper.clamp(index, FIRST_PAGE, LAST_PAGE)).apply(parentScreen);
    }

    public static void saveConfig()
    {
        if (Minecraft.getInstance().player != null)
        {
            if (!MC.isLocalServer())
                ModMessages.INSTANCE.sendToServer(new SyncConfigSettingsMessage());
            else ConfigSettings.saveValues();
            ModMessages.INSTANCE.sendToServer(new SyncPreferredUnitsMessage(ConfigSettings.CELSIUS.get() ? Temperature.Units.C : Temperature.Units.F));
        }
        else ConfigSettings.saveValues();
        ClientSettingConfig.getInstance().writeAndSave();
    }

    @SubscribeEvent
    public static void onClicked(ScreenEvent.MouseButtonPressed event)
    {

    }

    @SubscribeEvent
    public static void onReleased(ScreenEvent.MouseButtonReleased event)
    {

    }
}
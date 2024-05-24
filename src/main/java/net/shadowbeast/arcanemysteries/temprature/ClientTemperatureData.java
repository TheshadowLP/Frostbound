package net.shadowbeast.arcanemysteries.temprature;

public class ClientTemperatureData {
    private static int playerTemperature;
    public static void set(int temperature) {
        ClientTemperatureData.playerTemperature = temperature;
    }
    public static int getPlayerTemperature() {
        return playerTemperature;
    }
}

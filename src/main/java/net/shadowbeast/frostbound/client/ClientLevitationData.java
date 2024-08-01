package net.shadowbeast.frostbound.client;

public class ClientLevitationData {
    private static boolean levitionTagged;
    public static void set(boolean levitionTagged) {
        ClientLevitationData.levitionTagged = levitionTagged;
    }
    public static boolean getLevitionTag() {
        return levitionTagged;
    }
}

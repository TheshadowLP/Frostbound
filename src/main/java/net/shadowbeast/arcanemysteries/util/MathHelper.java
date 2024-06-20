package net.shadowbeast.arcanemysteries.util;

public class MathHelper
{
    public static double roundDecimal(int i, double value) {
        int modInt = (int)(value * Math.pow(i, 10.0D));
        return modInt / Math.pow(i, 10.0D);
    }
}

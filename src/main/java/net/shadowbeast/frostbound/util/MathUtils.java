package net.shadowbeast.frostbound.util;

public class MathUtils {
    public static double toRadians(double degrees) {
        return degrees * Math.PI / 180;
    }
    public static double toDegrees(double radians) {
        return radians * 57.2957795f;
    }

    public static double getRelative(String axis, double yaw, double pitch, float radius) {
        double x;
        double y;
        double z;
        x = Math.sin(toRadians(yaw)) * radius * Math.sin(toRadians(pitch));
        z = Math.cos(toRadians(yaw)) * radius * Math.sin(toRadians(pitch));
        y = Math.cos(toRadians(pitch)) * radius;
        return switch (axis) {
            case "x" -> x;
            case "z" -> z;
            case "y" -> y;
            default -> 0;
        };
    }
}

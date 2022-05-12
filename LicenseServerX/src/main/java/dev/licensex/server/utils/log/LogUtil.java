package dev.licensex.server.utils.log;

import lombok.experimental.UtilityClass;

@UtilityClass
public class LogUtil {
    public static void logInfo(String msg) {
        System.out.println("LicenseServerX -> " + msg);
    }
    public static void logErr(String msg) {
        System.err.println(msg);
    }
}

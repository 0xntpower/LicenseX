package dev.licensex.server.utils;

import lombok.experimental.UtilityClass;

import java.io.Console;
import java.util.Scanner;

@UtilityClass
public class IOUtil {
    static final Scanner in = new Scanner(System.in);

    public static void logInfo(String msg) {
        System.out.println("LicenseServerX -> " + msg);
    }
    public static void logErr(String msg) {
        System.err.println("LicenseServerX -> " + msg);
    }
    public static String prompt(String msg) {
        System.out.print("LicenseServerX -> " + msg);
        return in.nextLine();
    }

    public static String promptMasked(String inputMessage) {
        if (runningFromIntelliJ())
            return prompt(inputMessage);
        Console console = System.console();
        if (console == null) {
            logErr("Err, Couldn't get Console instance");
            System.exit(0);
        }
        char[] passwordArray = console.readPassword(inputMessage);
        return new String(passwordArray);
    }

    public static boolean runningFromIntelliJ() {
        String classPath = System.getProperty("java.class.path");
        return classPath.contains("idea_rt.jar");
    }
}

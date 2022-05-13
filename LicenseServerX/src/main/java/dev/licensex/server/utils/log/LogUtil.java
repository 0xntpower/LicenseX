package dev.licensex.server.utils.log;

import lombok.experimental.UtilityClass;

import java.io.Console;
import java.util.Scanner;

@UtilityClass
public class LogUtil {
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
        Console console = System.console();
        if (console == null) {
//            logErr("Err, Couldn't get Console instance");
//            System.exit(0); ToDo revert this when building
            return prompt(inputMessage);
        }
        char[] passwordArray = console.readPassword(inputMessage);
        return new String(passwordArray);
    }
}

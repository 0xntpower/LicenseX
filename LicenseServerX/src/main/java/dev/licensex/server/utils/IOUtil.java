package dev.licensex.server.utils;

import dev.licensex.server.filesys.lx.LXConfig;
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

    public static void promptLicenseInput(LXConfig licenseFile) {
        IOUtil.logInfo("Activation required, please insert your LicenseX product license.");
        String license = "";
        while (license.length() < 5) {
            license = IOUtil.promptMasked("license: ");
        }
        licenseFile.set("license", license);
    }

    public static void promptSetupInput(LXConfig configFile) {
        IOUtil.logInfo("No configuration file found, Initializing setup process . . .\n");

        IOUtil.logInfo("Configuring mongoDB settings . . .");

        String databaseName = IOUtil.prompt("Please enter database name: ");
        while (databaseName.length() < 3) {
            IOUtil.prompt("The provided database name is too short, please provide a longer one.");
            databaseName = IOUtil.prompt("Please enter database name: ");
        }

        String mongoStr = IOUtil.prompt("Please enter a mongo connection string: ");
        while (mongoStr.length() < 3) {
            IOUtil.prompt("The provided connection string is too short, please provide a proper one.");
            mongoStr = IOUtil.prompt("Please enter a mongo connection string: ");
        }

        System.out.println();
        IOUtil.logInfo("Generating configuration file . . .");
        IOUtil.logInfo("Configuration completed.\n");

        configFile.set("MongoDB.database_name", databaseName);
        configFile.set("MongoDB.mongo_string", mongoStr);
    }
}

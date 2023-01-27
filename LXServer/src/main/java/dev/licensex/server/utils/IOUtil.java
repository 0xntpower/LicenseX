package dev.licensex.server.utils;

import dev.licensex.server.filesys.lx.LXConfig;
import lombok.experimental.UtilityClass;

import java.io.Console;
import java.util.Scanner;

@UtilityClass
public class IOUtil {
    static final Scanner in = new Scanner(System.in);
    static final String PREFIX = "LXServer -> ";

    public static void logInfo(String msg) {
        System.out.println(PREFIX + msg);
    }
    public static void logErr(String msg) {
        System.err.println(PREFIX + msg);
    }
    public static String prompt(String msg) {
        System.out.print(PREFIX + msg);
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

        String storage_type = IOUtil.prompt("Data storage type mongodb/yaml: ");

        while (!(storage_type.equalsIgnoreCase("mongodb")
                || storage_type.equalsIgnoreCase("yaml"))) {
            IOUtil.prompt("The provided storage type is not supported.");
            storage_type = IOUtil.prompt("Data storage type mongodb/yaml: ");
        }

        if (storage_type.equalsIgnoreCase("mongodb")) {
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

            configFile.set("MongoDB.database_name", databaseName);
            configFile.set("MongoDB.mongo_string", mongoStr);
        }

        System.out.println();
        IOUtil.logInfo("Generating configuration file . . .");
        IOUtil.logInfo("Configuration completed.\n");

        configFile.set("storage_type", storage_type);
    }
}

package dev.licensex.server.utils;

import dev.licensex.server.system.yaml.files.ConfigFile;
import dev.licensex.server.system.yaml.files.LicenseFile;
import lombok.experimental.UtilityClass;

import java.util.LinkedList;
import java.util.List;

@UtilityClass
public class SetupUtil {

    public static void promptLicenseInput(LicenseFile licenseFile) {
        IOUtil.logInfo("Activation required, please insert your LicenseX product license.");
        String license = "";
        while (license.length() < 5) {
            license = IOUtil.promptMasked("license: ");
        }
        licenseFile.set("license", license);
    }

    public static void promptSetupInput(ConfigFile configFile) {
        IOUtil.logInfo("No configuration file found.");
        IOUtil.logInfo("Initializing setup process . . .");

        String licensingModeInput;
        List<String> options = new LinkedList<>();
        options.add("idk");
        options.add("floating");
        options.add("per-machine");

        while (true) {
            licensingModeInput = IOUtil.prompt("Please select what licensing mode you want to use [Floating/Per-machine/Idk]: ");
            if (licensingModeInput.equalsIgnoreCase("idk")) {
                IOUtil.logInfo("Please visit the LicenseX documentation page at https://licensex.webflow.io/documentation to read about \nlicensing modes and find which one fits your usage the best.");
            } else if (!options.contains(licensingModeInput.toLowerCase())) {
                IOUtil.logInfo("unexpected input, please try again.");
            } else break;
        }

        IOUtil.logInfo("Generating configuration file . . .");
        IOUtil.logInfo("Configuration completed.\n");

        configFile.set("LicensingMode", licensingModeInput);
    }

}

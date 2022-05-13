package dev.licensex.server.utils;

import dev.licensex.server.system.yaml.files.ConfigFile;
import dev.licensex.server.system.yaml.files.LicenseFile;
import dev.licensex.server.utils.log.LogUtil;
import lombok.experimental.UtilityClass;

import java.util.LinkedList;
import java.util.List;

@UtilityClass
public class SetupUtil {

    public static void promptLicenseInput(LicenseFile licenseFile) {
        LogUtil.logInfo("Activation required, please insert your LicenseX product license.");
        String license = "";
        while (license.length() < 5) {
            license = LogUtil.promptMasked("license: ");
        }
        licenseFile.set("license", license);
    }

    public static void promptSetupInput(ConfigFile configFile) {
        LogUtil.logInfo("No configuration file found.");
        LogUtil.logInfo("Initializing setup process . . .");

        String licensingModeInput;
        List<String> options = new LinkedList<>();
        options.add("idk");
        options.add("floating");
        options.add("per-machine");

        while (true) {
            licensingModeInput = LogUtil.prompt("Please select what licensing mode you want to use [Floating/Per-machine/Idk]: ");
            if (licensingModeInput.equalsIgnoreCase("idk")) {
                LogUtil.logInfo("Please visit the LicenseX documentation page at https://licensex.webflow.io/documentation to read about \nlicensing modes and find which one fits your usage the best.");
            } else if (!options.contains(licensingModeInput.toLowerCase())) {
                LogUtil.logInfo("unexpected input, please try again.");
            } else break;
        }

        LogUtil.logInfo("Generating configuration file . . .");
        LogUtil.logInfo("Configuration completed.\n");

        configFile.set("LicensingMode", licensingModeInput);
    }
}

package dev.licensex.server;

import dev.licensex.server.system.files.ConfigFile;
import dev.licensex.server.system.files.LicenseFile;

public class LicenseServerX {

    final LicenseFile licenseFile;
    final ConfigFile configFile;

    public LicenseServerX() {
        licenseFile = new LicenseFile();
        configFile = new ConfigFile();
        System.out.println("hello world");
    }

}

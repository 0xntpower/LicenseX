package dev.licensex.server.yaml.files;

import dev.licensex.server.utils.IOUtil;
import dev.licensex.server.yaml.YamlStorage;
import dev.licensex.server.utils.FilenameUtils;

import java.io.File;
import java.net.URISyntaxException;

public class LicenseFile {

    private final YamlStorage licenseYaml;

    public LicenseFile() {
        String path = getSelfPath();
        assert path != null;
        path = FilenameUtils.getPath(path);

        File configFile = new File(path + "license.yml");

        if (!configFile.exists()) {
            licenseYaml = new YamlStorage("license", path);
            promptLicenseInput(this);
            licenseYaml.save();
        } else {
            licenseYaml = new YamlStorage("license", path);
        }
    }

    public void set(String key, String value) {
        licenseYaml.set(key, value);
        licenseYaml.save();
    }

    public String getLicense() {
        return licenseYaml.getString("license");
    }

    private String getSelfPath() {
        String path = null;
        try {
            path = ConfigFile.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return path;
    }

    private void promptLicenseInput(LicenseFile licenseFile) {
        IOUtil.logInfo("Activation required, please insert your LicenseX product license.");
        String license = "";
        while (license.length() < 5) {
            license = IOUtil.promptMasked("license: ");
        }
        licenseFile.set("license", license);
    }
}

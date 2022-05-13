package dev.licensex.server.system.yaml.files;

import dev.licensex.server.system.yaml.YamlStorage;
import dev.licensex.server.utils.FilenameUtils;
import dev.licensex.server.utils.SetupUtil;
import lombok.Getter;

import java.io.File;
import java.net.URISyntaxException;

public class ConfigFile {

    @Getter
    private final YamlStorage configYaml;

    public ConfigFile() {
        String path = getSelfPath();
        assert path != null;
        path = FilenameUtils.getPath(path);

        File configFile = new File(path + "config.yml");

        if (!configFile.exists()) {
            configYaml = new YamlStorage("config", path);
            configYaml.set("LicensingMode", "none");
            configYaml.save();

            SetupUtil.promptSetupInput(this);
        } else {
            configYaml = new YamlStorage("config", path);
        }
    }

    public void set(String key, String value) {
        configYaml.set(key, value);
        configYaml.save();
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
}

package dev.licensex.server.yaml.files;

import dev.licensex.server.utils.IOUtil;
import dev.licensex.server.yaml.YamlStorage;
import dev.licensex.server.utils.FilenameUtils;
import lombok.Getter;

import java.io.File;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

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
            promptSetupInput(this);
            configYaml.save();
        } else {
            configYaml = new YamlStorage("config", path);
        }
    }

    public void set(String key, String value) {
        configYaml.set(key, value);
        configYaml.save();
    }

    public Object get(String path) {
        return configYaml.get(path);
    }

    public String getString(String path) {
        return configYaml.getString(path);
    }

    public int getInt(String path) {
        return configYaml.getInt(path);
    }

    public boolean getBoolean(String path) {
        return configYaml.getBoolean(path);
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

    private void promptSetupInput(ConfigFile configFile) {
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
        IOUtil.logInfo("Configuring product settings . . .");

        String licenseRule;
        List<String> options = new LinkedList<>();
        options.add("idk");
        options.add("floating");
        options.add("per-machine");

        while (true) {
            licenseRule = IOUtil.prompt("Please select what license rule you want to use [Floating/Per-machine/Idk]: ");
            if (licenseRule.equalsIgnoreCase("idk")) {
                IOUtil.logInfo("Please visit the LicenseX documentation page \nat https://licensex.webflow.io/documentation to read about license rules\n and find which one fits your usage the best.");
            } else if (!options.contains(licenseRule.toLowerCase())) {
                IOUtil.logInfo("unexpected input, please try again.");
            } else break;
        }

        System.out.println();
        IOUtil.logInfo("Generating configuration file . . .");
        IOUtil.logInfo("Configuration completed.\n");

        configFile.set("MongoDB.database_name", databaseName);
        configFile.set("MongoDB.mongo_string", mongoStr);
        configFile.set("MongoDB.licenses_collection_name", "licenses"); // just setting it to default
        configFile.set("Product_settings.license_rule", licenseRule);
    }
}

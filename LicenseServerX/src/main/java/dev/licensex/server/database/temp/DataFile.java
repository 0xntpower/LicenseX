package dev.licensex.server.database.temp;

import dev.licensex.server.LicenseServerX;
import dev.licensex.server.filesys.yml.YamlStorage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataFile {

    private final YamlStorage yaml;

    public DataFile() {
        File configFile = new File(LicenseServerX.getSelfPath() + "datafile.yml");

        if (!configFile.exists()) {
            yaml = new YamlStorage("datafile", LicenseServerX.getSelfPath());
            yaml.save();
        } else {
            yaml = new YamlStorage("datafile", LicenseServerX.getSelfPath());
        }
    }

    public String getAllData() {
        StringBuilder data = new StringBuilder();
        Map<String, Object> dataMap = yaml.getData();

        for (String str : dataMap.keySet()) {
            data.append(str);
        }

        return data.toString();
    }

    public void addCategory(String category) {
        yaml.set(category + ".", "none");
        yaml.save();
    }

    public void addProduct(String category, String product) {
        yaml.set(category + "." + product + ".", "none");
        yaml.save();
    }

    public void addLicense(String category, String product, String license) {
        List<String> productLicenses = yaml.getStringList(category + "." + product + ".");;

        if (productLicenses == null)
            productLicenses = new ArrayList<>();

        productLicenses.add(license);
        yaml.set(category + "." + product + ".", productLicenses);

        yaml.save();
    }

    public void removeLicense(String category, String product, String license) {
        List<String> productLicenses = yaml.getStringList(category + "." + product + ".");;

        if (productLicenses == null)
            productLicenses = new ArrayList<>();

        productLicenses.remove(license);
        yaml.set(category + "." + product + ".", productLicenses);

        yaml.save();
    }
}

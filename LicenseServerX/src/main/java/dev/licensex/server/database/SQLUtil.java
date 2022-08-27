package dev.licensex.server.database;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SQLUtil {

    public static void saveProduct(String name, String category) {
        // ToDo first check if a product with the same name and category already exists
        String SAVE_PRODUCT_QUERY = "INSERT INTO TestDB.dbo.Products(Name, Category) " +
                "VALUES ('" + name + "', '" + category + "');";
    }

    public static void deleteProduct(String name, String category) {

    }

    public static void saveLicense(String licenseText, String productName, String categoryName) {

    }

    public static void deleteLicense(String licenseText, String productName, String categoryName) {

    }

    public static void saveUsageHistory(String licenseText, String ipAddress, String hwid) {

    }

    public static void deleteUsageHistory(String licenseText, String ipAddress, String hwid) {

    }

    public static String getLicenseData(String licenseText) {
        return "";
    }

    public static boolean shouldLicenseActivate(String licenseText) {
        return true;
    }
}

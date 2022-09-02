package dev.licensex.server.database;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SQLUtil {

    public static void saveProduct(String name, String category) {
        // ToDo first check if a product with the same name and category already exists

        String GET_PRODUCT_NAME_QUERY = "SELECT proT.Name " +
                "FROM TestDB.dbo.Products proT " +
                "WHERE proT.Name = '" + name + "' AND proT.Category = '" + category + "'";

        String SAVE_PRODUCT_QUERY = "INSERT INTO TestDB.dbo.Products(Name, Category) " +
                "VALUES ('" + name + "', '" + category + "');";
    }

    public static void deleteProduct(String name, String category) {

    }

    public static void saveLicense(String licenseText, String productName, String categoryName) {
        // ToDo first check if a license with the same licenseText already exists

        String GET_LICENSE_TEXT_QUERY = "";

        String SAVE_LICENSE_QUERY = "";
    }

    public static void deleteLicense(String licenseText, String productName, String categoryName) {

    }

    public static void saveUsageHistory(String licenseText, String ipAddress, String hwid) {

        String GET_LICENSE_ID_QUERY = "SELECT Id FROM TestDB.dbo.Licenses " +
                "WHERE LicenseText = '" + licenseText + "'";

        String license_id = ""; // result from GET_LICENSE_ID_QUERY here

        String SAVE_USAGE_HISTORY_QUERY = "INSERT INTO TestDB.dbo.Usage_history(License_id, IP, HWID) " +
                "VALUES (" + license_id + ", '" + ipAddress + "', '" + hwid + "');";

    }

    public static void deleteUsageHistory(String licenseText, String ipAddress, String hwid) {

    }

    public static String getLicenseData(String licenseText) {

        String GET_LICENSE_DATA_QUERY = "SELECT licT.Id, licT.LicenseText, proT.Name as Product, proT.Category " +
                "FROM TestDB.dbo.Licenses licT JOIN TestDB.dbo.Products proT " +
                "ON licT.Product = proT.Id " +
                "WHERE licT.LicenseText = '" + licenseText + "'";

        // ToDO check if the license actually exists, otherwise the query will return empty data probably?

        return "";
    }

    public static boolean shouldLicenseActivate(String licenseText) {

        // this will fail to return the text if the license doesn't exist, otherwise it exists
        String GET_LICENSE_TEXT_QUERY = "SELECT LicenseText FROM TestDB.dbo.Licenses " +
                "WHERE LicenseText = '" + licenseText + "'";

        return true;
    }
}

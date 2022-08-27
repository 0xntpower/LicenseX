package dev.licensex.server.database;

public class SQLInit {

    // ToDo change those to only create the tables only if they do not exist yet
    private static final String CREATE_PRODUCTS_TABLE_QUERY =
            "CREATE TABLE Products(" +
                    "Id INT NOT NULL IDENTITY PRIMARY KEY," +
                    "Name VARCHAR(20)," +
                    "Category VARCHAR(20)" +
                    ")";
    private static final String CREATE_LICENSES_TABLE_QUERY =
            "CREATE TABLE Licenses(" +
            "Id INT NOT NULL IDENTITY PRIMARY KEY," +
            "LicenseText VARCHAR(40)," +
            "Product INT FOREIGN KEY REFERENCES Products(Id)" +
            ")";
    private static final String CREATE_USAGE_HISTORY_TABLE_QUERY =
            "CREATE TABLE Usage_history(" +
            "License_id INT FOREIGN KEY REFERENCES Licenses(Id)," +
            "IP VARCHAR(25)," +
            "HWID VARCHAR(200)" +
            ")";

    public SQLInit() {
        connect();
    }

    public void connect() {

    }
}

package dev.licensex.server.database;

import dev.licensex.server.filesys.LXConfig;

import java.sql.*;

public class SQLInit {

    static final String DB_URL = "jdbc:sqlserver://localhost\\(localdb)/Test_local";
    static final String USERNAME = "DESKTOP-IJL24AC\\Nort";
    static final String PASSWORD = "";

    // ToDo change those to only create the tables only if they do not exist yet
    private static final String CREATE_PRODUCTS_TABLE_IF_NOT_EXISTS_QUERY =
            "CREATE TABLE Products(" +
                    "Id INT NOT NULL IDENTITY PRIMARY KEY," +
                    "Name VARCHAR(20)," +
                    "Category VARCHAR(20)" +
                    ")";
    private static final String CREATE_LICENSES_TABLE_IF_NOT_EXISTS_QUERY =
            "CREATE TABLE Licenses(" +
            "Id INT NOT NULL IDENTITY PRIMARY KEY," +
            "LicenseText VARCHAR(40)," +
            "Product INT FOREIGN KEY REFERENCES Products(Id)" +
            ")";
    private static final String CREATE_USAGE_HISTORY_TABLE_IF_NOT_EXISTS_QUERY =
            "CREATE TABLE Usage_history(" +
            "License_id INT FOREIGN KEY REFERENCES Licenses(Id)," +
            "IP VARCHAR(25)," +
            "HWID VARCHAR(200)" +
            ")";

    public SQLInit(LXConfig config) {
        Connection connection = openConnection();

        if (connection == null) {
            System.out.println("failed to connect to sql server");
            return;
        }

        createTables(connection);
        closeConnection(connection);
    }

    public Connection openConnection() {
        try {
            return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void createTables(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(CREATE_PRODUCTS_TABLE_IF_NOT_EXISTS_QUERY);
            statement.executeQuery(CREATE_LICENSES_TABLE_IF_NOT_EXISTS_QUERY);
            statement.executeQuery(CREATE_USAGE_HISTORY_TABLE_IF_NOT_EXISTS_QUERY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

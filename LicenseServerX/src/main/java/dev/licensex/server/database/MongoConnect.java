package dev.licensex.server.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dev.licensex.server.utils.IOUtil;
import dev.licensex.server.yaml.files.ConfigFile;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MongoConnect {

    private MongoClient client;
    private MongoDatabase database;
    private MongoCollection licenseCollection;

    public MongoConnect(ConfigFile configFile) {
        try {

            String databaseName = configFile.getString("MongoDB.database_name");

            // mongodb+srv://dbuser:Bu2ys8CPBG2ejtD7@testdb.l47li.mongodb.net/TestDB?retryWrites=true&w=majority
            MongoClientURI uri = new MongoClientURI(configFile.getString("MongoDB.mongo_string"));

            IOUtil.logInfo("Attempting to connect to database.");

            client = new MongoClient(uri);
            database = client.getDatabase(databaseName);

            IOUtil.logInfo("Connected to database successfully.");

            List<String> collectionsNames = database.listCollectionNames().into(new ArrayList<>());

            if (!collectionsNames.contains(configFile.getString("MongoDB.licenses_collection_name")))
                database.createCollection(configFile.getString("MongoDB.licenses_collection_name"));
            licenseCollection = database.getCollection(configFile.getString("MongoDB.licenses_collection_name"));

        } catch (Exception e) {
            IOUtil.logErr("Disabling due to issues with connecting to database.");
            e.printStackTrace();
        }
    }
}

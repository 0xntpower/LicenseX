package dev.licensex.server.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dev.licensex.server.filesys.LXConfig;
import dev.licensex.server.utils.IOUtil;
import lombok.Getter;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MongoConnect {

    private MongoClient client;
    private MongoDatabase database;

    public MongoConnect(LXConfig configFile) {
        try {

            String databaseName = configFile.getString("MongoDB.database_name");

            // mongodb+srv://dbuser:Bu2ys8CPBG2ejtD7@testdb.l47li.mongodb.net/TestDB?retryWrites=true&w=majority
            MongoClientURI uri = new MongoClientURI(configFile.getString("MongoDB.mongo_string"));

            client = new MongoClient(uri);
            database = client.getDatabase(databaseName);

            IOUtil.logInfo("Connected to database successfully.");

        } catch (Exception e) {
            IOUtil.logErr("Disabling due to issues with connecting to database.");
            e.printStackTrace();
        }
    }

    public MongoCollection<Document> getMongoCollection(String name) {
        List<String> existingCollectionNames = database.listCollectionNames().into(new ArrayList<>());

        if (!existingCollectionNames.contains(name))
            return null;

        return database.getCollection(name);
    }

    /**
     * Creates a new collection if it doesn't exist already
     * @param name the name of the collection
     */
    public boolean createMongoCollection(String name) {
        List<String> collectionsNames = database.listCollectionNames().into(new ArrayList<>());
        if (!collectionsNames.contains(name)) {
            database.createCollection(name);
            return true;
        }
        return false;
    }
}

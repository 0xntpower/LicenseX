package dev.licensex.server.database;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import dev.licensex.server.Launcher;
import lombok.experimental.UtilityClass;
import org.bson.Document;

@UtilityClass
public class MongoUtil {

    /**
     * Does license exist in database
     * @param license the license
     */
    public static boolean containsLicense(String product, String license) {
        // every product has a collection, and every license has a document inside that collection
        MongoCollection<Document> collection = Launcher.licenseServerX.getMongoConnect().getMongoCollection(product);

        FindIterable iterable = collection.find(Filters.eq("license", license));
        return iterable.first() != null;
    }

    /**
     * Add a license to database
     * @param license the license
     */
    public static void addLicenseToDatabase(String product, String license) {
        MongoCollection<Document> collection = Launcher.licenseServerX.getMongoConnect().getMongoCollection(product);

        // if license is not already in database, will add it
        if (collection.find(Filters.eq("license", license)).first() == null) {
            // Create a new document and insert the value
            Document document = new Document();
            document.put("license", license);

            collection.insertOne(document);
        }

    }

    /**
     * Use to delete a license from the database
     * @param license the license
     */
    public static void removeLicenseFromDatabase(String product, String license) {
        MongoCollection<Document> collection = Launcher.licenseServerX.getMongoConnect().getMongoCollection(product);

        // if the license is in the database, will remove it
        if (collection.find(Filters.eq("license", license)).first() != null) {
            // Update the values in existing document
            Document document = collection.find(Filters.eq("license", license)).first();

            assert document != null;
            collection.deleteOne(document);
        }

    }
}

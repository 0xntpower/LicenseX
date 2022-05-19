package dev.licensex.server.database;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import dev.licensex.server.Launcher;
import dev.licensex.server.utils.IOUtil;
import lombok.experimental.UtilityClass;
import org.bson.Document;

@UtilityClass
public class MongoUtil {

    public static int getHWIDsAmount(String product, String license) {
        // every product has a collection, and every license has a document inside that collection
        MongoCollection collection = Launcher.licenseServerX.getMongoConnect().getMongoCollection(product);

        if (collection == null) {
            IOUtil.logErr("Can't perform action on product collection that doesn't exist (" + product + ").");
            return -1;
        }

        Document document = (Document) collection.find(Filters.eq("license", license)).first();

        if (document == null) {
            return 0;
        }

        int count = 0;

        for (String key : document.keySet())
            if (key.contains("hwid"))
                count++;

        return count;
    }

    /**
     * Does license exist in database
     * @param license the license
     */
    public static boolean containsLicense(String product, String license) {
        // every product has a collection, and every license has a document inside that collection
        MongoCollection collection = Launcher.licenseServerX.getMongoConnect().getMongoCollection(product);

        FindIterable iterable = collection.find(Filters.eq("license", license));
        return iterable.first() != null;
    }

}

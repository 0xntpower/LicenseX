package dev.licensex.server.request.requests;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import dev.licensex.server.Launcher;
import dev.licensex.server.database.MongoUtil;
import dev.licensex.server.request.RequestExecutor;
import dev.licensex.server.utils.IOUtil;
import org.bson.Document;

import javax.net.ssl.SSLSocket;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class CreateProductRequest implements RequestExecutor {
    @Override
    public void onRequest(SSLSocket socket, BufferedReader input, PrintWriter output, String... args) {
        String category = args[0];
        String product_name = args[1];
        String product_id = args[1];

        String reply = "failed";
        String msg = socket.getInetAddress().getHostAddress() + " -> Product collection " + product_name + " already exists, aborting request.";

        if (MongoUtil.doesCollectionExist(product_name)) {

            MongoCollection<Document> collection = Launcher.licenseServerX.getMongoConnect().getMongoCollection(category);

            if (collection.find(Filters.eq("product_name", product_name)).first() == null
                    && collection.find(Filters.eq("product_id", product_name)).first() == null) {
                // Create a new document and insert the value
                Document document = new Document();
                document.put("product_name", product_name);
                document.put("product_id", product_id);

                collection.insertOne(document);

                reply = "success";
                msg = socket.getInetAddress().getHostAddress() + " -> Created new product collection named: " + product_name + ".";
            }

        }

        output.println(reply);
        IOUtil.logInfo(msg);
    }
}

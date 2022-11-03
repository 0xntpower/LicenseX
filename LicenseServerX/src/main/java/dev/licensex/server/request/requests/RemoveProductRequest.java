package dev.licensex.server.request.requests;

import dev.licensex.server.request.RequestExecutor;
import dev.licensex.server.utils.IOUtil;

import javax.net.ssl.SSLSocket;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class RemoveProductRequest implements RequestExecutor {
    @Override
    public void onRequest(SSLSocket socket, BufferedReader input, PrintWriter output, String... args) {
        String category = args[0];
        String product_name = args[1];
        String product_id = args[2];

//        String msg = socket.getInetAddress().getHostAddress() + " -> Product collection " + category + " doesn't exists, aborting request.";
//
//        if (MongoUtil.doesCollectionExist(category)) {
//
//            msg = socket.getInetAddress().getHostAddress() + " -> Product " + product_name + " doesn't exists, aborting request.";
//
//            MongoCollection<Document> collection = Launcher.licenseServerX.getMongoConnect().getMongoCollection(category);
//
//            // ToDo make this also check ids
//            Document document = collection.find(Filters.eq("product_name", product_name)).first();
//            if (document != null) {
//                collection.deleteOne(document);
//
//                msg = socket.getInetAddress().getHostAddress() + " -> Product " + product_name + " has been deleted.";
//            }
//
//        }
//
//        IOUtil.logInfo(msg);
//
//        try {
//            output.close();
//            input.close();
//            socket.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        IOUtil.logInfo(socket.getInetAddress().getHostAddress() + " -> Product ("+product_id+") has been removed");
    }
}

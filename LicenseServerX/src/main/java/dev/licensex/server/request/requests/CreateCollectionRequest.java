package dev.licensex.server.request.requests;

import dev.licensex.server.Launcher;
import dev.licensex.server.request.RequestExecutor;
import dev.licensex.server.utils.IOUtil;

import javax.net.ssl.SSLSocket;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class CreateCollectionRequest implements RequestExecutor {
    @Override
    public void onRequest(SSLSocket socket, BufferedReader input, PrintWriter output, String... args) {
//        String collectionName = args[0];
//
//        boolean createdCollection = Launcher.licenseServerX.getMongoConnect().createMongoCollection(collectionName);
//
//        String reply = "failed";
//        String msg = socket.getInetAddress().getHostAddress() + " -> Product collection " + collectionName + " already exists, aborting request.";
//
//        if (createdCollection) {
//            reply = "success";
//            msg = socket.getInetAddress().getHostAddress() + " -> Created new product collection named: " + collectionName + ".";
//        }
//
//        output.println(reply);
//        IOUtil.logInfo(msg);
    }
}

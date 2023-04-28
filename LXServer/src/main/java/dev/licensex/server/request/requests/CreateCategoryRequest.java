package dev.licensex.server.request.requests;

import dev.licensex.server.LXServer;
import dev.licensex.server.request.RequestExecutor;
import dev.licensex.server.utils.IOUtil;
import dev.licensex.server.utils.consts.LXP;
import dev.licensex.server.utils.crypto.AES;

import javax.net.ssl.SSLSocket;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class CreateCategoryRequest implements RequestExecutor {
    @Override
    public void onRequest(SSLSocket socket, BufferedReader input, PrintWriter output, String... args) {
        String collectionName = args[0];
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

        if (LXServer.datafile.isCategoryExists(collectionName)) {
            output.println(AES.encrypt(LXP.ACKNOWLEDGEMENTS.CLIENT_REQUEST_PROCESSING_FAILED, AES.getEncryptionKey()));
            IOUtil.logInfo(socket.getInetAddress().getHostAddress() + " -> Failed to create category ("+collectionName+") [Name taken]");
        } else {
            LXServer.datafile.addProduct(collectionName, "none");
            output.println(AES.encrypt(LXP.ACKNOWLEDGEMENTS.CLIENT_REQUEST_PROCESSED_SUCCESSFULLY, AES.getEncryptionKey()));
            IOUtil.logInfo(socket.getInetAddress().getHostAddress() + " -> Category ("+collectionName+") has been created");
        }
    }
}

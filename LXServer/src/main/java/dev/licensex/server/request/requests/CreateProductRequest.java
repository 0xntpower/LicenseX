package dev.licensex.server.request.requests;

import dev.licensex.server.LXServer;
import dev.licensex.server.request.RequestExecutor;
import dev.licensex.server.utils.IOUtil;
import dev.licensex.server.utils.consts.LXP;
import dev.licensex.server.utils.crypto.AES;

import javax.net.ssl.SSLSocket;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class CreateProductRequest implements RequestExecutor {
    @Override
    public void onRequest(SSLSocket socket, BufferedReader input, PrintWriter output, String... args) {
        String category = args[0];
        String product_name = args[1];

        IOUtil.logInfo(socket.getInetAddress().getHostAddress() + " -> debug cat ("+category+")");

        // if category was deleted while creating a product under it
        if (!LXServer.datafile.isCategoryExists(category)) {
            output.println(AES.encrypt(LXP.ACKNOWLEDGEMENTS.CLIENT_REQUEST_PROCESSING_FAILED, AES.getEncryptionKey()));
            IOUtil.logInfo(socket.getInetAddress().getHostAddress() + " -> Failed to create product ("+product_name+")");
        } else {
            LXServer.datafile.addProduct(category, product_name);
            output.println(AES.encrypt(LXP.ACKNOWLEDGEMENTS.CLIENT_REQUEST_PROCESSED_SUCCESSFULLY, AES.getEncryptionKey()));
            IOUtil.logInfo(socket.getInetAddress().getHostAddress() + " -> Product ("+product_name+") has been created");
        }
    }

}

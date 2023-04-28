package dev.licensex.server.request.requests;

import dev.licensex.server.LXServer;
import dev.licensex.server.request.RequestExecutor;
import dev.licensex.server.utils.IOUtil;
import dev.licensex.server.utils.consts.LXP;
import dev.licensex.server.utils.crypto.AES;

import javax.net.ssl.SSLSocket;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class AddLicenseRequest implements RequestExecutor {
    @Override
    public void onRequest(SSLSocket socket, BufferedReader input, PrintWriter output, String... args) {
        String category = args[0];
        String productName = args[1];
        String productId = args[2];
        String licenseId = args[3];


        if (LXServer.datafile.isCategoryExists(category)) {
            LXServer.datafile.addLicense(category, productName, licenseId);

            output.println(AES.encrypt(LXP.ACKNOWLEDGEMENTS.CLIENT_REQUEST_PROCESSED_SUCCESSFULLY, AES.getEncryptionKey()));

            IOUtil.logInfo(socket.getInetAddress().getHostAddress() + " -> License ("+licenseId+") has been added to database");
        } else {
            output.println(AES.encrypt(LXP.ACKNOWLEDGEMENTS.CLIENT_REQUEST_PROCESSING_FAILED, AES.getEncryptionKey()));
            IOUtil.logInfo(socket.getInetAddress().getHostAddress() + " -> Failed to add license ("+licenseId+") to database");
        }

    }

}

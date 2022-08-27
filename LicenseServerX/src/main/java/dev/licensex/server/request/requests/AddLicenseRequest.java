package dev.licensex.server.request.requests;

import dev.licensex.server.request.RequestExecutor;
import dev.licensex.server.utils.IOUtil;

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

        //MongoUtil.addLicenseToDatabase(category, productName, productId, licenseId);

        IOUtil.logInfo(socket.getInetAddress().getHostAddress() + " -> License ("+licenseId+") has been added to database");
    }
}

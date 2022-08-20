package dev.licensex.server.request.requests;

import dev.licensex.server.database.MongoUtil;
import dev.licensex.server.request.RequestExecutor;
import dev.licensex.server.utils.IOUtil;

import javax.net.ssl.SSLSocket;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class AddLicenseRequest implements RequestExecutor {
    @Override
    public void onRequest(SSLSocket socket, BufferedReader input, PrintWriter output, String... args) {
        String category = args[0];
        String product = args[1];
        String licenseId = args[2];

        MongoUtil.addLicenseToDatabase(category, product, licenseId);

        IOUtil.logInfo(socket.getInetAddress().getHostAddress() + " -> License ("+licenseId+") has been added to database");
    }
}

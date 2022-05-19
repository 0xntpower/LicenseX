package dev.licensex.server.request.requests;

import dev.licensex.server.database.MongoUtil;
import dev.licensex.server.request.RequestExecutor;
import dev.licensex.server.utils.IOUtil;

import javax.net.ssl.SSLSocket;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class RemoveLicenseRequest implements RequestExecutor {
    @Override
    public void onRequest(SSLSocket socket, BufferedReader input, PrintWriter output, String... args) {
        String product = args[0];
        String licenseId = args[2];

        MongoUtil.removeLicenseFromDatabase(product, licenseId);

        IOUtil.logInfo("License has been removed from database");
    }
}

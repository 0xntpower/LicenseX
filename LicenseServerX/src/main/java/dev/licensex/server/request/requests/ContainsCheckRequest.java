package dev.licensex.server.request.requests;

import dev.licensex.server.database.MongoUtil;
import dev.licensex.server.request.RequestExecutor;
import dev.licensex.server.utils.IOUtil;

import javax.net.ssl.SSLSocket;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class ContainsCheckRequest implements RequestExecutor {
    @Override
    public void onRequest(SSLSocket socket, BufferedReader input, PrintWriter output, String... args) {
        String product = args[0];
        String licenseId = args[2];

        String result = MongoUtil.containsLicense(product, licenseId) ? "Approved" : "Denied";

        output.println(result); // ToDo change the reply to something better
        IOUtil.logInfo(socket.getInetAddress().getHostAddress() + " -> " + result + " activation request for license - " + licenseId);
    }
}

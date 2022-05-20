package dev.licensex.server.request.requests;

import dev.licensex.server.Launcher;
import dev.licensex.server.request.RequestExecutor;
import dev.licensex.server.utils.IOUtil;

import javax.net.ssl.SSLSocket;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class RemoveCollectionRequest implements RequestExecutor {
    @Override
    public void onRequest(SSLSocket socket, BufferedReader input, PrintWriter output, String... args) {
        String product = args[0];

        Launcher.licenseServerX.getMongoConnect().getMongoCollection(product).drop();

        IOUtil.logInfo(socket.getInetAddress().getHostAddress() + " -> MongoCollection " + product + " has been removed.");
    }
}

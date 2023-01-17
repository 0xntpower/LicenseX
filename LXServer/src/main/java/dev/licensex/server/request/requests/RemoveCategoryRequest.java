package dev.licensex.server.request.requests;

import dev.licensex.server.request.RequestExecutor;
import dev.licensex.server.utils.IOUtil;
import dev.licensex.server.utils.consts.LXP;
import dev.licensex.server.utils.crypto.AES;

import javax.net.ssl.SSLSocket;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class RemoveCategoryRequest implements RequestExecutor {
    @Override
    public void onRequest(SSLSocket socket, BufferedReader input, PrintWriter output, String... args) {
        String category = args[0];

        output.println(AES.encrypt(LXP.ACKNOWLEDGEMENTS.CLIENT_REQUEST_PROCESSED_SUCCESSFULLY, AES.getEncryptionKey()));

        IOUtil.logInfo(socket.getInetAddress().getHostAddress() + " -> Category (" + category + ") has been removed.");
    }
}

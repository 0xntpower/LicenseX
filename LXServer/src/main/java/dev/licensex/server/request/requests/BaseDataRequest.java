package dev.licensex.server.request.requests;

import dev.licensex.server.LXServer;
import dev.licensex.server.request.RequestExecutor;
import dev.licensex.server.utils.IOUtil;
import dev.licensex.server.utils.crypto.AES;

import javax.net.ssl.SSLSocket;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class BaseDataRequest implements RequestExecutor {
    private static int BASE_DATA_REQUESTS_COUNT = 0;
    @Override
    public void onRequest(SSLSocket socket, BufferedReader input, PrintWriter output, String... args) {
        BASE_DATA_REQUESTS_COUNT++;
        //output.println(AES.encrypt(BASE_DATA_REQUESTS_COUNT + "|" + LXServer.datafile.getAllData(), AES.getEncryptionKey()));
        output.println(AES.encrypt(LXServer.datafile.getAllData(), AES.getEncryptionKey()));

        IOUtil.logInfo(socket.getInetAddress().getHostAddress() + " -> Data has been sent to manager client");
    }
}

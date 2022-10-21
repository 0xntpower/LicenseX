package dev.licensex.server.request.requests;

import dev.licensex.server.LicenseServerX;
import dev.licensex.server.request.RequestExecutor;
import dev.licensex.server.utils.IOUtil;
import dev.licensex.server.utils.consts.LicenseXProtocol;

import javax.net.ssl.SSLSocket;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class BaseDataRequest implements RequestExecutor {
    @Override
    public void onRequest(SSLSocket socket, BufferedReader input, PrintWriter output, String... args) {
        output.println(LicenseServerX.datafile.getAllData());

        IOUtil.logInfo(socket.getInetAddress().getHostAddress() + " -> Data has been sent to manager client");
    }
}

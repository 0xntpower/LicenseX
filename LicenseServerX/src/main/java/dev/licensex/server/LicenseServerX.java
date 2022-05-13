package dev.licensex.server;

import dev.licensex.server.system.ClientHandler;
import dev.licensex.server.system.request.RequestsManager;
import dev.licensex.server.system.request.requests.ExampleRequest1;
import dev.licensex.server.system.request.requests.ExampleRequest2;
import dev.licensex.server.system.yaml.files.ConfigFile;
import dev.licensex.server.system.yaml.files.LicenseFile;
import dev.licensex.server.utils.SSLUtil;
import dev.licensex.server.utils.log.LogUtil;
import lombok.Getter;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLSocket;

public class LicenseServerX {

    private static final int LISTENING_PORT = 1234;

    final LicenseFile licenseFile;
    final ConfigFile configFile;
    @Getter final RequestsManager requestsManager;

    public LicenseServerX() {
        licenseFile = new LicenseFile();
        configFile = new ConfigFile();
        requestsManager = new RequestsManager();

        requestsManager.registerRequestExecutor("contains", new ExampleRequest1());
        requestsManager.registerRequestExecutor("add", new ExampleRequest2());

        try {
            startSocketServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startSocketServer() throws Exception {
        SSLServerSocket sslServerSocket = SSLUtil.getSSLServerSocket(LISTENING_PORT);
        LogUtil.logInfo("listening to secure connections . . .");
        while (true) {
            SSLSocket sslsocket = (SSLSocket) sslServerSocket.accept();
            ClientHandler clientHandler = new ClientHandler(sslsocket, requestsManager);
            clientHandler.start();
        }
    }
}

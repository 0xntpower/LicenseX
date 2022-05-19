package dev.licensex.server;

import dev.licensex.server.database.MongoConnect;
import dev.licensex.server.request.RequestsManager;
import dev.licensex.server.request.requests.ContainsCheckRequest;
import dev.licensex.server.request.requests.CreateCollectionRequest;
import dev.licensex.server.request.requests.ExampleRequest1;
import dev.licensex.server.yaml.files.ConfigFile;
import dev.licensex.server.yaml.files.LicenseFile;
import dev.licensex.server.utils.SSLUtil;
import dev.licensex.server.utils.IOUtil;
import dev.licensex.server.utils.style.AsciiUtil;
import lombok.Getter;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLSocket;

@Getter
public class LicenseServerX {

    private static final int LISTENING_PORT = 1234;

    final LicenseFile licenseFile;
    ConfigFile configFile;
    RequestsManager requestsManager;
    MongoConnect mongoConnect;

    public LicenseServerX() {
        AsciiUtil.printBanner("SERVERX");

        // setup / load license file
        licenseFile = new LicenseFile();

        // ToDo verify product license validity here
        System.out.println();
        IOUtil.logInfo("Checking license . . .");
        IOUtil.logInfo("License activated successfully!\n");

        // setup / load config file
        configFile = new ConfigFile();

        // construct requests handlers
        requestsManager = new RequestsManager();
        requestsManager.registerRequestExecutor("contains", new ContainsCheckRequest());
        requestsManager.registerRequestExecutor("create", new CreateCollectionRequest());
        requestsManager.registerRequestExecutor("example", new ExampleRequest1());

        mongoConnect = new MongoConnect(configFile);

        try {
            startSocketServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startSocketServer() throws Exception {
        SSLServerSocket sslServerSocket = SSLUtil.getSSLServerSocket(LISTENING_PORT);
        IOUtil.logInfo("listening to secure connections . . .");
        while (true) {
            SSLSocket sslsocket = (SSLSocket) sslServerSocket.accept();
            ClientHandler clientHandler = new ClientHandler(sslsocket, requestsManager);
            clientHandler.start();
        }
    }
}

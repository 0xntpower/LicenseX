package dev.licensex.server;

import dev.licensex.server.database.MongoConnect;
import dev.licensex.server.request.RequestsManager;
import dev.licensex.server.request.requests.*;
import dev.licensex.server.utils.IOUtil;
import dev.licensex.server.utils.SSLUtil;
import dev.licensex.server.utils.style.AsciiUtil;
import dev.licensex.server.yaml.files.ConfigFile;
import dev.licensex.server.yaml.files.LicenseFile;
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
    }

    public void setUp() {
        // construct requests handlers
        requestsManager = new RequestsManager();
        requestsManager.registerRequestExecutor("add", new AddLicenseRequest());
        requestsManager.registerRequestExecutor("contains", new ContainsCheckRequest());
        requestsManager.registerRequestExecutor("remove", new RemoveLicenseRequest());
        requestsManager.registerRequestExecutor("create", new CreateCollectionRequest());
        requestsManager.registerRequestExecutor("example", new ExampleRequest1());

        IOUtil.logInfo("Attempting to connect to database.");
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

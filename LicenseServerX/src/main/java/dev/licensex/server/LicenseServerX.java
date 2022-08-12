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
    RequestsManager managerRequests;
    RequestsManager clientRequests;
    MongoConnect mongoConnect;

    public LicenseServerX() {
        System.setProperty("DEBUG.MONGO", "false");
        System.setProperty("DB.TRACE", "false");

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
        managerRequests = new RequestsManager();
        managerRequests.registerRequestExecutor("add", new AddLicenseRequest());
        managerRequests.registerRequestExecutor("contains", new ContainsCheckRequest());
        managerRequests.registerRequestExecutor("remove", new RemoveLicenseRequest());
        managerRequests.registerRequestExecutor("create", new CreateCollectionRequest());
        managerRequests.registerRequestExecutor("createproduct", new CreateProductRequest());

        clientRequests = new RequestsManager();
        clientRequests.registerRequestExecutor("contains", new ContainsCheckRequest());

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
            ClientHandler clientHandler = new ClientHandler(sslsocket, managerRequests, clientRequests);
            clientHandler.start();
        }
    }
}

package dev.licensex.server;

import dev.licensex.server.database.MongoConnect;
import dev.licensex.server.filesys.LXConfig;
import dev.licensex.server.request.RequestsManager;
import dev.licensex.server.request.requests.*;
import dev.licensex.server.utils.FilenameUtils;
import dev.licensex.server.utils.IOUtil;
import dev.licensex.server.utils.SSLUtil;
import dev.licensex.server.utils.style.AsciiUtil;
import lombok.Getter;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLSocket;
import java.net.URISyntaxException;

@Getter
public class LicenseServerX {

    private static final int LISTENING_PORT = 1234;

    final LXConfig licenseFile;
    LXConfig configFile;
    RequestsManager managerRequests;
    RequestsManager clientRequests;
    MongoConnect mongoConnect;

    public LicenseServerX() {
        System.setProperty("DEBUG.MONGO", "false");
        System.setProperty("DB.TRACE", "false");

        AsciiUtil.printBanner("SERVERX");

        // setup / load license file
        licenseFile = new LXConfig(getSelfPath() + "license.lx");
        if (licenseFile.isNewFile())
            IOUtil.promptLicenseInput(licenseFile);

        if (licenseFile.getString("license").length() < 3) {
            IOUtil.logErr("Invalid license.");
            return;
        }
        System.out.println();
        IOUtil.logInfo("Checking license . . .");
        IOUtil.logInfo("License activated successfully!\n");

        // setup / load config file
        configFile = new LXConfig(getSelfPath() + "config.lx", false);
        if (configFile.isNewFile())
            IOUtil.promptSetupInput(configFile);

    }

    public void setUp() {
        // construct requests handlers
        managerRequests = new RequestsManager();
        managerRequests.registerRequestExecutor("addlicense", new AddLicenseRequest());
        managerRequests.registerRequestExecutor("containslicense", new ContainsCheckRequest());

        managerRequests.registerRequestExecutor("removelicense", new RemoveLicenseRequest());
        managerRequests.registerRequestExecutor("removeproduct", new RemoveProductRequest());
        managerRequests.registerRequestExecutor("removecategory", new RemoveCollectionRequest());

        managerRequests.registerRequestExecutor("createcategory", new CreateCollectionRequest());
        managerRequests.registerRequestExecutor("createproduct", new CreateProductRequest());

        clientRequests = new RequestsManager();
        clientRequests.registerRequestExecutor("containslicense", new ContainsCheckRequest());

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

    private String getSelfPath() {
        String path = null;
        try {
            path = FilenameUtils.getPath(LXConfig.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return path;
    }
}

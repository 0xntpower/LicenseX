package dev.licensex.server;

import dev.licensex.server.database.sql.SQLInit;
import dev.licensex.server.database.yaml.DataFile;
import dev.licensex.server.filesys.lx.LXConfig;
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
public class LXServer {

    private static final int LISTENING_PORT = 1234;
    public static DataFile datafile;

    final LXConfig licenseFile;
    LXConfig configFile;
    RequestsManager managerRequests;
    RequestsManager clientRequests;
    SQLInit sqlInit;

    public LXServer() {
        System.setProperty("DEBUG.MONGO", "false");
        System.setProperty("DB.TRACE", "false");

        AsciiUtil.printBanner("LXSERVER");

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
        managerRequests.registerRequestExecutor("removecategory", new RemoveCategoryRequest());

        managerRequests.registerRequestExecutor("createcategory", new CreateCategoryRequest());
        managerRequests.registerRequestExecutor("createproduct", new CreateProductRequest());

        managerRequests.registerRequestExecutor("data", new BaseDataRequest());

        clientRequests = new RequestsManager();
        clientRequests.registerRequestExecutor("containslicense", new ContainsCheckRequest());

        //IOUtil.logInfo("Attempting to connect to database.");
        //sqlInit = new SQLInit(configFile);
        datafile = new DataFile();

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

    public static String getSelfPath() {
        String path = null;
        try {
            path = FilenameUtils.getPath(LXConfig.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return path;
    }
}

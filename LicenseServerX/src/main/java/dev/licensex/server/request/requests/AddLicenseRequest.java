package dev.licensex.server.request.requests;

import dev.licensex.server.LicenseServerX;
import dev.licensex.server.request.RequestExecutor;
import dev.licensex.server.utils.IOUtil;
import dev.licensex.server.utils.consts.LicenseXProtocol;

import javax.net.ssl.SSLSocket;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class AddLicenseRequest implements RequestExecutor {
    @Override
    public void onRequest(SSLSocket socket, BufferedReader input, PrintWriter output, String... args) {
        String category = args[0];
        String productName = args[1];
        String productId = args[2];
        String licenseId = args[3];

        //MongoUtil.addLicenseToDatabase(category, productName, productId, licenseId);
        LicenseServerX.datafile.addLicense(category, productName, licenseId);

        // ToDo verify data existence before replying
        output.println(LicenseXProtocol.ACKNOWLEDGEMENTS.CLIENT_REQUEST_PROCESSED_SUCCESSFULLY);

        IOUtil.logInfo(socket.getInetAddress().getHostAddress() + " -> License ("+licenseId+") has been added to database");
    }
}

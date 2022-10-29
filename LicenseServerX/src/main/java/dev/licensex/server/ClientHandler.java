package dev.licensex.server;

import dev.licensex.server.request.RequestException;
import dev.licensex.server.request.RequestsManager;
import dev.licensex.server.utils.IOUtil;
import dev.licensex.server.utils.StringUtil;

import javax.net.ssl.SSLSocket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ClientHandler extends Thread {

    final SSLSocket socket;
    final RequestsManager managerRequests;
    final RequestsManager clientRequests;
    BufferedReader input;
    PrintWriter output;

    public ClientHandler(SSLSocket socket, RequestsManager managerRequests, RequestsManager clientRequests) {
        this.socket = socket;
        this.managerRequests = managerRequests;
        this.clientRequests = clientRequests;
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));;
            output = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            IOUtil.logErr("Error creating streams: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            String requestStr = input.readLine();

            // expected request array syntax [originFlag, product, rqName, arg]
            // example request               [client, godseye, contains, licenseId]
            // example request after removed first [godseye, contains, licenseId]
            String[] request = StringUtil.split(requestStr, '|');

            // process the request according to its origin
            switch (request[0].toLowerCase()) {
                case "manager":
                    processManagerRequest(StringUtil.removeFirst(request));
                    break;
                case "client":
                    processClientRequest(StringUtil.removeFirst(request));
                    break;
                default:
                    throw new RequestException("Cannot sort un-flagged request, aborting. content:[" + requestStr + "]");
            }

        } catch (IOException | RequestException e) {
            e.printStackTrace();
        }
    }

    private void processManagerRequest(String[] request) throws RequestException {
        if (managerRequests.doesRequestExist(request[0]))
            managerRequests.getRequestExecutor(request[0]).onRequest(socket, input, output, StringUtil.removeFirst(request));
        else throw new RequestException("The received manager request does not exist, aborting. content: [" + request[1] + "]");
    }

    private void processClientRequest(String[] request) throws RequestException {
        if (clientRequests.doesRequestExist(request[0]))
            clientRequests.getRequestExecutor(request[0]).onRequest(socket, input, output, StringUtil.removeFirst(request));
        else throw new RequestException("The received client request does not exist, aborting. content: [" + request[1] + "]");
    }
}

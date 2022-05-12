package dev.licensex.server.system;

import dev.licensex.server.system.request.RequestException;
import dev.licensex.server.system.request.RequestsManager;
import dev.licensex.server.utils.StringUtil;
import dev.licensex.server.utils.log.LogUtil;
import lombok.extern.java.Log;

import javax.net.ssl.SSLSocket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ClientHandler extends Thread {

    final SSLSocket socket;
    final RequestsManager requestsManager;
    BufferedReader input;
    PrintWriter output;

    public ClientHandler(SSLSocket socket, RequestsManager requestsManager) {
        this.socket = socket;
        this.requestsManager = requestsManager;
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));;
            output = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            LogUtil.logErr("Error creating streams: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            String requestStr = input.readLine();

            // expected request array syntax [originFlag, rqName, arg, arg]
            String[] request = StringUtil.split(requestStr, '|');

            // process the request according to its origin
            switch (request[0]) {
                case "Manager" -> processManagerRequest(request);
                case "Client" -> processClientRequest(request);
                default -> throw new RequestException("Cannot sort un-flagged request, aborting. content:[" + requestStr + "]");
            }

        } catch (IOException | RequestException e) {
            e.printStackTrace();
        }
    }

    private void processManagerRequest(String[] request) throws RequestException {
        if (requestsManager.doesRequestExist(request[1]))
            requestsManager.getRequestExecutor(request[1]).onRequest(request);
        else throw new RequestException("The received manager request does not exist, aborting. content: [" + request[1] + "]");
    }

    private void processClientRequest(String[] request) throws RequestException {
        if (requestsManager.doesRequestExist(request[1]))
            requestsManager.getRequestExecutor(request[1]).onRequest(request);
        else throw new RequestException("The received client request does not exist, aborting. content: [" + request[1] + "]");
    }
}

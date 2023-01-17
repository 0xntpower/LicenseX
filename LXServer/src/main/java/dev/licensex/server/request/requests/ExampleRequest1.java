package dev.licensex.server.request.requests;

import dev.licensex.server.request.RequestExecutor;

import javax.net.ssl.SSLSocket;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class ExampleRequest1 implements RequestExecutor {
    @Override
    public void onRequest(SSLSocket socket, BufferedReader input, PrintWriter output, String... args) {
        String product = args[0];
        String rqName = args[1];
        String arg1 = args[2];
        String arg2 = args[3];

        System.out.println("add license to database here");
    }
}

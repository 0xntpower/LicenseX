package dev.licensex.server.request;

import javax.net.ssl.SSLSocket;
import java.io.BufferedReader;
import java.io.PrintWriter;

public interface RequestExecutor {
    void onRequest(SSLSocket socket, BufferedReader input, PrintWriter output, String... args);
}

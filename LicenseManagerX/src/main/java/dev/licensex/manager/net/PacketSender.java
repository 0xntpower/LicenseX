package dev.licensex.manager.net;

import dev.licensex.manager.utils.crypto.SSLUtil;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class PacketSender {

    private static final String REMOTE_HOST = "localhost";
    private static final int REMOTE_PORT = 1234;

    public static String sendPacketToServer(String msg) {
        String responseStr = "None";

        try {

            SSLSocketFactory factory = SSLUtil.getSocketFactory();
            SSLSocket sslsocket = (SSLSocket) factory.createSocket(REMOTE_HOST, REMOTE_PORT);

            // explicitly executing a handshake
            sslsocket.startHandshake();

            // What parameters were established?
//            System.out.printf("Negotiated Session: %s%n", sslsocket.getSession().getProtocol());
//            System.out.printf("Cipher Suite: %s%n", sslsocket.getSession().getCipherSuite());

            BufferedReader input = new BufferedReader(new InputStreamReader(sslsocket.getInputStream()));;
            PrintWriter output = new PrintWriter(sslsocket.getOutputStream(), true);

            output.println(msg);

            responseStr = input.readLine();
//            System.out.println("server -> " + responseStr);

            output.close();
            input.close();
            sslsocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseStr;
    }

    public static void sendPacketToServerNoResponse(String msg) {
        try {

            SSLSocketFactory factory = SSLUtil.getSocketFactory();
            SSLSocket sslsocket = (SSLSocket) factory.createSocket(REMOTE_HOST, REMOTE_PORT);

            // explicitly executing a handshake
            sslsocket.startHandshake();

            // What parameters were established?
//            System.out.printf("Negotiated Session: %s%n", sslsocket.getSession().getProtocol());
//            System.out.printf("Cipher Suite: %s%n", sslsocket.getSession().getCipherSuite());

            BufferedReader input = new BufferedReader(new InputStreamReader(sslsocket.getInputStream()));;
            PrintWriter output = new PrintWriter(sslsocket.getOutputStream(), true);

            output.println(msg);

            output.close();
            input.close();
            sslsocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

package dev.licensex.manager.net;

import dev.licensex.manager.files.LXConfig;
import dev.licensex.manager.utils.crypto.AES;
import dev.licensex.manager.utils.crypto.SSLUtil;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;

public class PacketSender {
    private static final String SYMMETRIC_KEY = "YecoF0I6M05thxLeokoHuW8iUhTdIUInjkfF";

    private static String REMOTE_HOST;
    private static int REMOTE_PORT;

    public static void loadConnectionValues(LXConfig config) {
        REMOTE_HOST = config.getString("Ip-address");
        REMOTE_PORT = config.getInt("Port");
    }

    public static String sendPacketToServerEX(String msg) throws ConnectException {
        msg = AES.encrypt(msg, AES.getEncryptionKey());
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
        } catch (ConnectException e) {
            throw new ConnectException();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseStr;
    }

    public static String sendPacketToServer(String msg) {
        msg = AES.encrypt(msg, AES.getEncryptionKey());
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
        msg = AES.encrypt(msg, AES.getEncryptionKey());
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

//            output.close();
//            input.close();
//            sslsocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

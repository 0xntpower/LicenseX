package dev.licensex.server;

import javax.net.ssl.SSLSocket;

public class SocketHandler extends Thread {

    final SSLSocket socket;

    public SocketHandler(SSLSocket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

    }
}

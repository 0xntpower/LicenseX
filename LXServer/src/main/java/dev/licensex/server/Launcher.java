package dev.licensex.server;

public final class Launcher {

    public static LXServer LXServer;

    public static void main(String[] args) {
        LXServer = new LXServer();
        LXServer.setUp();
    }
}

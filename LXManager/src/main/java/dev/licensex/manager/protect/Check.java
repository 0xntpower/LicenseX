package dev.licensex.manager.protect;

public abstract class Check {
    public abstract void check();
    public void flag(String data) {
        System.out.println("sniffer detected: " + data);
        System.exit(0);
    }
}

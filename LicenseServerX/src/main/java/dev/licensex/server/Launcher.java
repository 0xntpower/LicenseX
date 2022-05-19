package dev.licensex.server;

public class Launcher {

    public static LicenseServerX licenseServerX; // ToDo fix this being null somehow

    public static void main(String[] args) {
        licenseServerX = new LicenseServerX();
    }
}

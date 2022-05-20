package dev.licensex.server;

public class Launcher {

    public static LicenseServerX licenseServerX;

    public static void main(String[] args) {
        licenseServerX = new LicenseServerX();
        licenseServerX.setUp();
    }
}

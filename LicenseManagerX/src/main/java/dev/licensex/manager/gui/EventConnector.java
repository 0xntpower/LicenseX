package dev.licensex.manager.gui;

import dev.licensex.manager.net.PacketSender;
import dev.licensex.manager.utils.consts.LicenseXProtocol;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class EventConnector {

    public static String onRequestData() {
        return PacketSender.sendPacketToServer("manager|data");
    }

    public static boolean onCategoryCreate(String name) {
        String response = PacketSender.sendPacketToServer("manager|createcategory|" + name);
        return response.equals(LicenseXProtocol.ACKNOWLEDGEMENTS.CLIENT_REQUEST_PROCESSED_SUCCESSFULLY);
    }

    public static boolean onProductCreate(String category, String name, String productId) {
        String response = PacketSender.sendPacketToServer("manager|createproduct|" + category + "|" + name + "|" + productId);
        return response.equals(LicenseXProtocol.ACKNOWLEDGEMENTS.CLIENT_REQUEST_PROCESSED_SUCCESSFULLY);
    }

    public static boolean onLicenseAdd(String category, String productName, String productId, String licenseId) {
        String response = PacketSender.sendPacketToServer("manager|addlicense|" + category + "|" + productName + "|" + productId + "|" + licenseId);
        return response.equals(LicenseXProtocol.ACKNOWLEDGEMENTS.CLIENT_REQUEST_PROCESSED_SUCCESSFULLY);
    }

    public static boolean onLicenseContains(String category, String productName, String productId, String licenseId) {
        String response = PacketSender.sendPacketToServer("manager|containslicense|" + category + "|" + productName + "|" + productId + "|" + licenseId);
        return response.equalsIgnoreCase("success");
    }

    public static void onCategoryDelete(String category, String categoryId) {
        PacketSender.sendPacketToServerNoResponse("manager|removecategory|" + category + "|" + categoryId);
    }

    public static void onProductDelete(String category, String productName, String productId) {
        PacketSender.sendPacketToServerNoResponse("manager|removeproduct|" + category + "|" + productName + "|" + productId);
    }

    public static void onLicenseDelete(String category, String productName, String productId, String licenseId) {
        PacketSender.sendPacketToServerNoResponse("manager|removelicense|" + category + "|" + productName + "|" + productId + "|" + licenseId);
    }
}

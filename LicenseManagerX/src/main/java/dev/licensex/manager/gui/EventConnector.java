package dev.licensex.manager.gui;

import dev.licensex.manager.net.PacketSender;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class EventConnector {

    public static boolean onCategoryCreate(String name) {
        String response = PacketSender.sendPacketToServer("manager|createcategory|" + name);
        return response.equalsIgnoreCase("success");
    }

    public static boolean onProductCreate(String category, String name, String productId) {
        String response = PacketSender.sendPacketToServer("manager|createproduct|" + category + "|" + name + "|" + productId);
        return response.equalsIgnoreCase("success");
    }

    public static boolean onLicenseAdd(String category, String productName, String productId, String licenseId) {
        String response = PacketSender.sendPacketToServer("manager|addlicense|" + category + "|" + productName + "|" + productId + "|" + licenseId);
        return response.equalsIgnoreCase("success");
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

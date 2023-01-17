package dev.licensex.manager.gui;

import dev.licensex.manager.net.PacketSender;
import dev.licensex.manager.utils.consts.LXP;
import dev.licensex.manager.utils.crypto.AES;
import lombok.experimental.UtilityClass;

import java.net.ConnectException;

@UtilityClass
public final class EventConnector {

    public static String onRequestData() throws ConnectException {
        return AES.decrypt(PacketSender.sendPacketToServerEX("manager|data|"), AES.getEncryptionKey());
    }

    public static boolean onCategoryCreate(String name) {
        String response = PacketSender.sendPacketToServer("manager|createcategory|" + name);
        response = AES.decrypt(response, AES.getEncryptionKey());
        return response.equals(LXP.ACKNOWLEDGEMENTS.CLIENT_REQUEST_PROCESSED_SUCCESSFULLY);
    }

    public static boolean onProductCreate(String category, String name, String productId) {
        String response = PacketSender.sendPacketToServer("manager|createproduct|" + category + "|" + name + "|" + productId);
        response = AES.decrypt(response, AES.getEncryptionKey());
        return response.equals(LXP.ACKNOWLEDGEMENTS.CLIENT_REQUEST_PROCESSED_SUCCESSFULLY);
    }

    public static boolean onLicenseAdd(String category, String productName, String productId, String licenseId) {
        String response = PacketSender.sendPacketToServer("manager|addlicense|" + category + "|" + productName + "|" + productId + "|" + licenseId);
        response = AES.decrypt(response, AES.getEncryptionKey());
        return response.equals(LXP.ACKNOWLEDGEMENTS.CLIENT_REQUEST_PROCESSED_SUCCESSFULLY);
    }

    public static boolean onLicenseContains(String category, String productName, String productId, String licenseId) {
        String response = PacketSender.sendPacketToServer("client|containslicense|" + category + "|" + productName + "|" + productId + "|" + licenseId);
        response = AES.decrypt(response, AES.getEncryptionKey());
        return response.equals(LXP.ACKNOWLEDGEMENTS.CLIENT_REQUEST_PROCESSED_SUCCESSFULLY);
    }

    // ToDo implement reverse thread in case deleting fails
    public static boolean onCategoryDelete(String category, String categoryId) {
        String response = PacketSender.sendPacketToServer("manager|removecategory|" + category + "|" + categoryId);
        response = AES.decrypt(response, AES.getEncryptionKey());
        return response.equals(LXP.ACKNOWLEDGEMENTS.CLIENT_REQUEST_PROCESSED_SUCCESSFULLY);
    }

    public static boolean onProductDelete(String category, String productName, String productId) {
        String response = PacketSender.sendPacketToServer("manager|removeproduct|" + category + "|" + productName + "|" + productId);
        response = AES.decrypt(response, AES.getEncryptionKey());
        return response.equals(LXP.ACKNOWLEDGEMENTS.CLIENT_REQUEST_PROCESSED_SUCCESSFULLY);
    }

    public static boolean onLicenseDelete(String category, String productName, String productId, String licenseId) {
        String response = PacketSender.sendPacketToServer("manager|removelicense|" + category + "|" + productName + "|" + productId + "|" + licenseId);
        response = AES.decrypt(response, AES.getEncryptionKey());
        return response.equals(LXP.ACKNOWLEDGEMENTS.CLIENT_REQUEST_PROCESSED_SUCCESSFULLY);
    }
}

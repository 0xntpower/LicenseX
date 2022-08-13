package dev.licensex.manager.gui;

import dev.licensex.manager.net.PacketSender;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class EventConnector {

    public static boolean onCategoryCreate(String name) {
        String response = PacketSender.sendPacketToServer("manager|create|" + name);
        return response.equalsIgnoreCase("success");
    }

    public static boolean onProductCreate(String category, String name, String productId) {
        String response = PacketSender.sendPacketToServer("manager|createproduct|" + category + "|" + name + "|" + productId);
        return response.equalsIgnoreCase("success");
    }

    public static void onProductDelete(String category, String productName, String productId) {
        PacketSender.sendPacketToServerNoResponse("manager|removeproduct|" + category + "|" + productName + "|" + productId);
    }
}

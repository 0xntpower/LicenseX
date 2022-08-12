package dev.licensex.manager.gui;

import dev.licensex.manager.net.PacketSender;

public final class EventConnector {

    public static boolean onCategoryCreate(String name) {
        String response = PacketSender.sendPacketToServer("create|" + name);
        return response.equalsIgnoreCase("success");
    }

    public static boolean onProductCreate(String name) {
        String response = PacketSender.sendPacketToServer("createproduct|" + name);
        return response.equalsIgnoreCase("success");
    }
}

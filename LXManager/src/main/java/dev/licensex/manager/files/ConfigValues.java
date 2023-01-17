package dev.licensex.manager.files;

import dev.licensex.manager.net.PacketSender;

public class ConfigValues {
    public static class CONNECTION {
        public static String SupportedTLS;
        public static String EncryptionLayers;
    }

    public static void loadConfiguration(LXConfig config) {
        PacketSender.loadConnectionValues(config);
        ConfigValues.CONNECTION.SupportedTLS = config.getString("Supported-tls");
        ConfigValues.CONNECTION.EncryptionLayers = config.getString("EncryptionLayers");
    }
}

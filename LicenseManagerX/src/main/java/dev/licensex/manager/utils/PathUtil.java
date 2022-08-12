package dev.licensex.manager.utils;

import dev.licensex.manager.Launcher;
import lombok.experimental.UtilityClass;

import java.net.URISyntaxException;

@UtilityClass
public class PathUtil {

    public static String getSelfPath() {
        String path = null;
        try {
            path = Launcher.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return FilenameUtils.getPath(path);
    }

}

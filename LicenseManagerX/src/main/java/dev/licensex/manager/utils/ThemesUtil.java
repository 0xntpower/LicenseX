package dev.licensex.manager.utils;

import com.formdev.flatlaf.IntelliJTheme;
import dev.licensex.manager.Launcher;
import lombok.experimental.UtilityClass;

import javax.swing.*;
import java.io.InputStream;

@UtilityClass
public class ThemesUtil {

    public static void setDefaultSystemLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setDarkMode() {
        InputStream inputStream = Launcher.class.getResourceAsStream(
                "/one_dark.theme.json");
        if (inputStream != null)
            IntelliJTheme.setup(inputStream);
    }
}

package dev.licensex.manager.utils;

import com.formdev.flatlaf.IntelliJTheme;
import dev.licensex.manager.Launcher;
import dev.licensex.manager.gui.pages.MainGUI;
import lombok.experimental.UtilityClass;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.io.InputStream;

@UtilityClass
public class ThemesUtil {

    public static void setDefaultSystemLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            if (MainGUI.tree != null) {
                DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) MainGUI.tree.getCellRenderer();
                renderer.setBackgroundNonSelectionColor(MainGUI.leftPanel.getBackground());
                MainGUI.tree.setBackground(MainGUI.leftPanel.getBackground());
            }
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

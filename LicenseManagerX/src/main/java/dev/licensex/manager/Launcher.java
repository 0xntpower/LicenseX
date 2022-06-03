package dev.licensex.manager;

import dev.licensex.manager.gui.pages.MainGUI;
import dev.licensex.manager.utils.ThemesUtil;

public final class Launcher {

    public static MainGUI mainGUI;

    public static void main(String[] args) {
        ThemesUtil.setDefaultSystemLookAndFeel();
        mainGUI = new MainGUI();
    }


}

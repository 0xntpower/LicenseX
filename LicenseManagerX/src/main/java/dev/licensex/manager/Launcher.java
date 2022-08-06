package dev.licensex.manager;

import dev.licensex.manager.files.LXConfig;
import dev.licensex.manager.gui.pages.MainGUI;
import dev.licensex.manager.utils.ThemesUtil;

public final class Launcher {

    public static MainGUI mainGUI;

    public static void main(String[] args) {
        LXConfig lxConfig = new LXConfig("C:\\Users\\Nort\\Desktop\\test.lx");

        //lxConfig.set("license", "test123");

        for (String str : lxConfig.getContent())
            System.out.println(str);

        ThemesUtil.setDefaultSystemLookAndFeel();
        mainGUI = new MainGUI();
    }


}

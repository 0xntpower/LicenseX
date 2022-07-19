package dev.licensex.manager;

import dev.licensex.manager.gui.pages.MainGUI;
import dev.licensex.manager.utils.ThemesUtil;

public final class Launcher {

    public static MainGUI mainGUI;

    public static void main(String[] args) {
//        List<String> content = new ArrayList<>();
//
//        content.add("cp is pc");
//        content.add("lol 123 pizza");
//
//        try {
//            LicenseXFile licenseXFile = new LicenseXFile("test.lx", "C:\\Users\\Nort\\Desktop", content);
//        } catch (LicenseManagerXException e) {
//            e.printStackTrace();
//        }

        ThemesUtil.setDefaultSystemLookAndFeel();
        mainGUI = new MainGUI();
    }


}

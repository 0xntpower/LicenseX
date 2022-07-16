package dev.licensex.manager;

import dev.licensex.manager.files.LicenseXFile;
import dev.licensex.manager.gui.pages.MainGUI;
import dev.licensex.manager.utils.ThemesUtil;

import java.util.ArrayList;
import java.util.List;

public final class Launcher {

    public static MainGUI mainGUI;

    public static void main(String[] args) {

        List<String> contents = new ArrayList<>();
        contents.add("hello my name is nort");
        contents.add("dejavu is playing faction rn probably");
        contents.add("and he doesn't know how to cooke");

        LicenseXFile licenseXFile = new LicenseXFile("test.lx", "C:\\Users\\Nort\\Desktop\\test.lx");
//        try {
//            LicenseXFile licenseXFile = new LicenseXFile("test.lx", "C:\\Users\\Nort\\Desktop\\test.lx", contents);
//        } catch (LicenseManagerXException e) {
//            e.printStackTrace();
//        }
        ThemesUtil.setDefaultSystemLookAndFeel();
        mainGUI = new MainGUI();
    }


}

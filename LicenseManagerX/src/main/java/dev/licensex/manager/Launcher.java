package dev.licensex.manager;

import dev.licensex.manager.files.LXConfig;
import dev.licensex.manager.gui.pages.ActivationGUI;
import dev.licensex.manager.gui.pages.MainGUI;
import dev.licensex.manager.utils.PathUtil;
import dev.licensex.manager.utils.ThemesUtil;

public final class Launcher {

    public static MainGUI mainGUI;

    public static void main(String[] args) {
        ThemesUtil.setDefaultSystemLookAndFeel();

        LXConfig licenseFile = new LXConfig(PathUtil.getSelfPath() + "license.lx");
        String licenseId = licenseFile.get("license") == null ? null : (licenseFile.get("license") + "");

        mainGUI = new MainGUI();

        if (licenseId == null)
            new ActivationGUI(mainGUI, licenseFile);
    }


}

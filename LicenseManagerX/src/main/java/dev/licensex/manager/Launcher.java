package dev.licensex.manager;

import dev.licensex.manager.files.LXConfig;
import dev.licensex.manager.gui.pages.ActivationGUI;
import dev.licensex.manager.gui.pages.MainGUI;
import dev.licensex.manager.utils.PathUtil;
import dev.licensex.manager.utils.ThemesUtil;
import dev.licensex.manager.winapi.ChecksManager;

public final class Launcher {

    private static final ChecksManager checksManager = new ChecksManager();
    public static MainGUI mainGUI;

    public static void main(String[] args) {
        ThemesUtil.setDefaultSystemLookAndFeel();

        checksManager.runSystemChecks();
        checksManager.registerScheduler(5);

        LXConfig licenseFile = new LXConfig(PathUtil.getSelfPath() + "license.lx");
        String licenseId = licenseFile.get("license") == null ? null : (licenseFile.get("license") + "");

        mainGUI = new MainGUI();

        ActivationGUI activationGUI = new ActivationGUI(mainGUI, licenseFile);

        if (licenseId == null)
            activationGUI.startWindow();
        else {
            activationGUI.performActivation(licenseId);
        }
    }


}

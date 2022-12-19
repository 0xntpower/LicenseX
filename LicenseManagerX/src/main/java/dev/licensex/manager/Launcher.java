package dev.licensex.manager;

import dev.licensex.manager.files.LXConfig;
import dev.licensex.manager.gui.pages.ActivationGUI;
import dev.licensex.manager.gui.pages.MainGUI;
import dev.licensex.manager.protect.ChecksManager;
import dev.licensex.manager.utils.PathUtil;
import dev.licensex.manager.utils.ThemesUtil;

public final class Launcher { // ToDo fix bug when exiting license or configuration windows
    // ToDo limit the kind of characters that can be used in a license (so it can't mess up functions in StringUtil)

    private static final ChecksManager checksManager = new ChecksManager();
    public static MainGUI mainGUI;

    public static void main(String[] args) {
        String os = System.getProperty("os.name");

        ThemesUtil.setDefaultSystemLookAndFeel();

        if (os.contains("win")) {
            checksManager.runSystemChecks();
            checksManager.registerScheduler(5);
        }

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

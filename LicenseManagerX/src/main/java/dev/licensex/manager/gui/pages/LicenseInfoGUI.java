package dev.licensex.manager.gui.pages;

public class LicenseInfoGUI extends JFrameX {
    private static boolean isRunning;

    public LicenseInfoGUI() {
        if (isRunning) return;
        isRunning = true;
        buildWindow();
        setVisible(true);
    }

    @Override
    protected void buildWindow() {
        setTitle("Info");

        // Section: license details
        // - original license id
        // - current license id
        // - date when generated

        // Section: license properties
        // type: per-machine/concurrent-sessions/unlimited
        // type-limit: number limit here
        // expiration: date/Lifetime

        // Section: User data
        // Registered machines: amount-of-machine  [show]
        // Ip history: amount-of-ips  [show]
    }

    @Override
    public void dispose() {
        super.dispose();
        isRunning = false;
    }
}

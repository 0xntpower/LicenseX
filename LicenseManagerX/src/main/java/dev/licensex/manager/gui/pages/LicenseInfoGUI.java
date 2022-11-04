package dev.licensex.manager.gui.pages;

import dev.licensex.manager.Launcher;
import dev.licensex.manager.gui.JFrameX;

import javax.swing.*;
import java.awt.*;

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
        setBounds(420, 150, 420, 430);
        setLocationRelativeTo(Launcher.mainGUI);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(null);

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

        setContentPane(panel);
    }

    @Override
    public void dispose() {
        super.dispose();
        isRunning = false;
    }
}

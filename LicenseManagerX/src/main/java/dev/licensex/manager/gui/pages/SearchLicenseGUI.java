package dev.licensex.manager.gui.pages;

import dev.licensex.manager.Launcher;

import javax.swing.*;
import java.awt.*;

public class SearchLicenseGUI extends JFrameX {
    private static boolean isRunning;

    public SearchLicenseGUI() {
        if (isRunning) return;
        isRunning = true;
        buildWindow();
        setVisible(true);
    }

    @Override
    protected void buildWindow() {
        setTitle("Search License");
        setBounds(420, 150, 420, 130);
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

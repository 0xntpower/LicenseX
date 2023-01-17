package dev.licensex.manager.gui.pages;

import dev.licensex.manager.Launcher;
import dev.licensex.manager.gui.JFrameX;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckForUpdatesGUI extends JFrameX {
    private static boolean isRunning;

    public CheckForUpdatesGUI() {
        if (isRunning) return;
        isRunning = true;
        buildWindow();
        setVisible(true);
    }

    @Override
    protected void buildWindow() {
        setTitle("Check For Updates");
        setBounds(420, 150, 420, 130);
        setLocationRelativeTo(Launcher.mainGUI);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(null);

        String checkResult = isUsingLatest() ? "You are using the latest version of LicenseManagerX"
                : "A new version of LicenseManagerX is available for install";

        JLabel editionLabel = new JLabel(checkResult);
        editionLabel.setBounds(13, 10, 380, 20);
        editionLabel.setFont(new Font(editionLabel.getFont().getName(), Font.PLAIN, 15));
        editionLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(editionLabel);

        String os = System.getProperty("os.name");

        JLabel versionLabel = new JLabel("Current version: 1.0.0 | Latest version: 1.0.0");
        versionLabel.setBounds(62, 35, os.contains("Mac OS X") ? 280 : 260, 20);
        versionLabel.setFont(new Font(versionLabel.getFont().getName(), Font.PLAIN, 13));
        versionLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(versionLabel);

        JButton btnClose = new JButton("Close");
        btnClose.setBounds(152, 60, 89, 23);
        panel.add(btnClose);
        btnClose.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setContentPane(panel);
    }

    private boolean isUsingLatest() {
        // ToDo insert version checking code here
        return true;
    }

    @Override
    public void dispose() {
        super.dispose();
        isRunning = false;
    }
}

package dev.licensex.manager.gui.pages;

import dev.licensex.manager.Launcher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckForUpdatesGUI extends JFrame {

    public CheckForUpdatesGUI() {
        buildWindow();
        show();
    }

    private void buildWindow() {
        setResizable(false);
        setTitle("Check For Updates");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(420, 150, 420, 130);
        setLayout(new BorderLayout());
        setLocationRelativeTo(Launcher.mainGUI);

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
        return true;
    }
}

package dev.licensex.manager.gui.pages;

import dev.licensex.manager.Launcher;
import dev.licensex.manager.gui.JFrameX;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutGUI extends JFrameX {
    private static boolean isRunning;

    public AboutGUI() {
        if (isRunning) return;
        isRunning = true;
        buildWindow();
        setVisible(true);
    }

    @Override
    protected void buildWindow() {
        setTitle("About");
        setBounds(400, 280, 400, 280);
        setLocationRelativeTo(Launcher.mainGUI);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("LicenseManagerX");
        titleLabel.setBounds(85, 20, 200, 23);
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(titleLabel);

        JSeparator jSeparator1 = new JSeparator();
        jSeparator1.setBounds(29, 50, 320, 20);
        panel.add(jSeparator1);

        String os = System.getProperty("os.name");

        JLabel editionLabel = new JLabel("Edition: Individual/Standard");
        editionLabel.setBounds(os.contains("Mac OS X") ? 22 : 15, 62, 180, 20);
        editionLabel.setFont(new Font(editionLabel.getFont().getName(), Font.PLAIN, 13));
        editionLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(editionLabel);

        JLabel versionLabel = new JLabel("Version: 1.0.0");
        versionLabel.setBounds(-3, 85, 140, 20);
        versionLabel.setFont(new Font(versionLabel.getFont().getName(), Font.PLAIN, 13));
        versionLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(versionLabel);

        JLabel creditsLabel = new JLabel("Authors: Nort721");
        creditsLabel.setBounds(os.contains("Mac OS X") ? 9 : 6, 108, 140, 20);
        creditsLabel.setFont(new Font(creditsLabel.getFont().getName(), Font.PLAIN, 13));
        creditsLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(creditsLabel);

        JLabel descriptionLabel = new JLabel("Description: A powerful product licensing solution");
        descriptionLabel.setBounds(os.contains("Mac OS X") ? 7 : -7, 131, 350, 20);
        descriptionLabel.setFont(new Font(descriptionLabel.getFont().getName(), Font.PLAIN, 13));
        descriptionLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(descriptionLabel);

        JLabel copyrightLabel = new JLabel("Copyright (c) 2022-2032 LicenseX");
        copyrightLabel.setBounds(os.contains("Mac OS X") ? 2 : -7, 154, 260, 20);
        copyrightLabel.setFont(new Font(copyrightLabel.getFont().getName(), Font.PLAIN, 13));
        copyrightLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(copyrightLabel);

        JSeparator jSeparator2 = new JSeparator();
        jSeparator2.setBounds(29, 186, 320, 20);
        panel.add(jSeparator2);

        JButton btnClose = new JButton("Close");
        btnClose.setBounds(140, 205, 89, 23);
        panel.add(btnClose);
        btnClose.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setContentPane(panel);
    }

    @Override
    public void dispose() {
        super.dispose();
        isRunning = false;
    }
}

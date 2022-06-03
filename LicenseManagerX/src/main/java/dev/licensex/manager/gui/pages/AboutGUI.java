package dev.licensex.manager.gui.pages;

import dev.licensex.manager.Launcher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutGUI extends JFrame {

    public AboutGUI() {
        buildWindow();
        show();
    }

    private void buildWindow() {
        setResizable(false);
        setTitle("About");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(400, 280, 400, 280);
        setLayout(new BorderLayout());
        setLocationRelativeTo(Launcher.mainGUI);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("LicenseManagerX");
        titleLabel.setBounds(85, 20, 200, 20);
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(titleLabel);

        JSeparator jSeparator1 = new JSeparator();
        jSeparator1.setBounds(29, 50, 320, 20);
        panel.add(jSeparator1);

        JLabel editionLabel = new JLabel("Edition: Individual/Standard");
        editionLabel.setBounds(15, 62, 180, 20);
        editionLabel.setFont(new Font(editionLabel.getFont().getName(), Font.PLAIN, 13));
        editionLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(editionLabel);

        JLabel versionLabel = new JLabel("Version: 1.0.0");
        versionLabel.setBounds(-3, 85, 140, 20);
        versionLabel.setFont(new Font(versionLabel.getFont().getName(), Font.PLAIN, 13));
        versionLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(versionLabel);

        JLabel creditsLabel = new JLabel("Authors: Nort721");
        creditsLabel.setBounds(6, 108, 140, 20);
        creditsLabel.setFont(new Font(creditsLabel.getFont().getName(), Font.PLAIN, 13));
        creditsLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(creditsLabel);

        JLabel descriptionLabel = new JLabel("Description: A powerful product licensing solution");
        descriptionLabel.setBounds(-7, 131, 350, 20);
        descriptionLabel.setFont(new Font(descriptionLabel.getFont().getName(), Font.PLAIN, 13));
        descriptionLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(descriptionLabel);

        JLabel copyrightLabel = new JLabel("Copyright (c) 2022-2032 LicenseX");
        copyrightLabel.setBounds(-7, 154, 260, 20);
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
}

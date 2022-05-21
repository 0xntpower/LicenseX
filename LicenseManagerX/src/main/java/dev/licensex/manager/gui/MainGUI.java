package dev.licensex.manager.gui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI {
    private JFrame jFrame;
    private JProgressBar bar;

    public MainGUI() {
        buildWindow();
        jFrame.show();
    }

    private void buildWindow() {
        jFrame = new JFrame();

        jFrame.setResizable(true);
        jFrame.setTitle("LicenseManagerX");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setBounds(200, 200, 1100, 680);
        jFrame.setLayout(new BorderLayout());

        JMenuBar menuBar = getMenuBar();

        jFrame.getContentPane().add(menuBar, BorderLayout.PAGE_START);

        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.LEFT);
        panel1.setBorder(new BevelBorder(BevelBorder.LOWERED));
        panel1.setPreferredSize(new Dimension(jFrame.getWidth() / 2, jFrame.getHeight() - (menuBar.getHeight() + 65)));
        panel1.add(label);

        JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bar = new JProgressBar();
        bar.setStringPainted(true);
        bar.setOpaque(false);
        bar.setVisible(false);
        panel2.setBorder(new BevelBorder(BevelBorder.LOWERED));
        panel2.setPreferredSize(new Dimension(jFrame.getWidth() / 3, jFrame.getHeight() - (menuBar.getHeight() + 65)));
        panel2.add(bar);

        JSplitPane spt = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel1, panel2) {
            private final int location = 250;

            {
                setDividerLocation(location);
            }

            @Override
            public int getDividerLocation() {
                return location;
            }

            @Override
            public int getLastDividerLocation() {
                return location;
            }
        };

        jFrame.add(spt, BorderLayout.SOUTH);
    }

    private JMenuBar getMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu productMenu = new JMenu("Product");

        JMenuItem newProductMenuItem = new JMenuItem("New product");
        newProductMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        productMenu.add(newProductMenuItem);

        JMenuItem newProductCategoryMenuItem = new JMenuItem("New product category");
        newProductCategoryMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        productMenu.add(newProductCategoryMenuItem);

        menuBar.add(productMenu);

        JMenu licenseMenu = new JMenu("License");

        JMenuItem generateLicenseMenuItem = new JMenuItem("Generate New License");
        generateLicenseMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        licenseMenu.add(generateLicenseMenuItem);

        menuBar.add(licenseMenu);

        JMenu ThemesMenu = new JMenu("Themes");

        JMenuItem darkModeMenuItem = new JMenuItem("Dracula");
        darkModeMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        ThemesMenu.add(darkModeMenuItem);

        menuBar.add(ThemesMenu);

        JMenu HelpMenu = new JMenu("Help");

        JMenuItem checkForUpdatesMenuItem = new JMenuItem("Check For Updates");
        checkForUpdatesMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        HelpMenu.add(checkForUpdatesMenuItem);

        JMenuItem aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        HelpMenu.add(aboutMenuItem);

        menuBar.add(HelpMenu);

        return menuBar;
    }
}

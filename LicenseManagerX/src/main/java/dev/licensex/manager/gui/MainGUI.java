package dev.licensex.manager.gui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.JMenuBar;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame {

    public MainGUI() {
        buildWindow();
        show();
    }

    private void buildWindow() {
        setResizable(true);
        setTitle("LicenseManagerX");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 200, 1100, 680);
        setLayout(new BorderLayout());

        JMenuBar menuBar = utils.getMenuBar();

        getContentPane().add(menuBar, BorderLayout.PAGE_START);

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.LEFT);
        leftPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        leftPanel.setPreferredSize(new Dimension(getWidth() / 2, getHeight() - (menuBar.getHeight() + 65)));
        leftPanel.add(label);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JProgressBar bar = new JProgressBar();
        bar.setStringPainted(true);
        bar.setOpaque(false);
        bar.setVisible(false);
        rightPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        rightPanel.setPreferredSize(new Dimension(getWidth() / 3, getHeight() - (menuBar.getHeight() + 65)));
        rightPanel.add(bar);

        JSplitPane spt = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel) {
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

        add(spt, BorderLayout.SOUTH);
    }

    static class utils {

        public static JMenuBar getMenuBar() {
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

            JMenuItem defaultModeMenuItem = new JMenuItem("Light");
            defaultModeMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
            ThemesMenu.add(defaultModeMenuItem);

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
                    String licenseType = "none";
                    JOptionPane.showMessageDialog(null, "Version: 0.0.0\nLicense-type: " + licenseType + "\nAuthor: Nort721", "LicenseManagerX", JOptionPane.INFORMATION_MESSAGE);
                }
            });
            HelpMenu.add(aboutMenuItem);

            menuBar.add(HelpMenu);

            return menuBar;
        }

    }

}

package dev.licensex.manager.gui.pages;

import dev.licensex.manager.Launcher;
import dev.licensex.manager.gui.actions.RightClickListener;
import dev.licensex.manager.utils.ThemesUtil;

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
        setLocationRelativeTo(null);

        // ---- menu bar start
        JMenuBar menuBar = new JMenuBar();

        JMenu productMenu = new JMenu("Product");

        JMenuItem newProductMenuItem = new JMenuItem("New product");
        newProductMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewProductGUI();
            }
        });
        productMenu.add(newProductMenuItem);

        JMenuItem newProductCategoryMenuItem = new JMenuItem("New product category");
        newProductCategoryMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewProductCategoryGUI();
            }
        });
        productMenu.add(newProductCategoryMenuItem);

        productMenu.add(new JSeparator());

        JMenuItem refreshMenuItem = new JMenuItem("Refresh");
        refreshMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        productMenu.add(refreshMenuItem);

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ToDo save and close all connections here
                System.exit(0);
            }
        });
        productMenu.add(exitMenuItem);

        menuBar.add(productMenu);

        JMenu licenseMenu = new JMenu("License");

        JMenuItem generateLicenseMenuItem = new JMenuItem("Generate New License");
        generateLicenseMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GenerateLicenseGUI();
            }
        });
        licenseMenu.add(generateLicenseMenuItem);

        menuBar.add(licenseMenu);

        JMenu ThemesMenu = new JMenu("Themes");

        JMenuItem defaultModeMenuItem = new JMenuItem("Light");
        defaultModeMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ThemesUtil.setDefaultSystemLookAndFeel();
                SwingUtilities.updateComponentTreeUI(Launcher.mainGUI);
            }
        });
        ThemesMenu.add(defaultModeMenuItem);

        ThemesMenu.add(new JSeparator());

        JMenuItem darkModeMenuItem = new JMenuItem("Dark");
        darkModeMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ThemesUtil.setDarkMode();
                SwingUtilities.updateComponentTreeUI(Launcher.mainGUI);
            }
        });
        ThemesMenu.add(darkModeMenuItem);

        menuBar.add(ThemesMenu);

        JMenu HelpMenu = new JMenu("Help");

        JMenuItem checkForUpdatesMenuItem = new JMenuItem("Check For Updates");
        checkForUpdatesMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CheckForUpdatesGUI();
            }
        });
        HelpMenu.add(checkForUpdatesMenuItem);

        HelpMenu.add(new JSeparator());

        JMenuItem aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AboutGUI();
            }
        });
        HelpMenu.add(aboutMenuItem);

        menuBar.add(HelpMenu);

        setJMenuBar(menuBar);
        getContentPane().add(new JLabel("No product selected", SwingConstants.CENTER));

        //getContentPane().add(menuBar, BorderLayout.PAGE_START);
        // --- menu bar end

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.LEFT);
        leftPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        leftPanel.setPreferredSize(new Dimension(getWidth() / 2, getHeight() - (menuBar.getHeight() + 80)));
        leftPanel.add(label);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JProgressBar bar = new JProgressBar();
        bar.setStringPainted(true);
        bar.setOpaque(false);
        bar.setVisible(false);
        rightPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        rightPanel.setPreferredSize(new Dimension(getWidth() / 3, getHeight() - (menuBar.getHeight() + 80)));
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

        leftPanel.addMouseListener(new RightClickListener());
    }

}

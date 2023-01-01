package dev.licensex.manager.gui.pages;

import dev.licensex.manager.Launcher;
import dev.licensex.manager.gui.JFrameX;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
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

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel licenseDetailsPanel = new JPanel();
        //licenseDetailsPanel.setPreferredSize(new Dimension(getWidth(), getHeight() / 2));

        // Section: license details
        // - original license id
        // - current license id
        // - date when generated

        Border licenseDetailsCategoryBorder = BorderFactory.createTitledBorder("License details");
        licenseDetailsPanel.setBorder(licenseDetailsCategoryBorder);

        JLabel originalLicenseLabel = new JLabel("Original license id: ");
        originalLicenseLabel.setBounds(0, 20, 50, 20);
        originalLicenseLabel.setFont(new Font(originalLicenseLabel.getFont().getName(), Font.PLAIN, 12));
        //originalLicenseLabel.setHorizontalAlignment(JLabel.CENTER);
        licenseDetailsPanel.add(originalLicenseLabel);

        JLabel originalLicenseLabelValue = new JLabel("XXXX-XXXX-XXXX-XXXX");
        originalLicenseLabelValue.setBounds(originalLicenseLabel.getWidth(), (int) originalLicenseLabel.getBounds().getY(), 150, 20);
        originalLicenseLabelValue.setFont(new Font(originalLicenseLabelValue.getFont().getName(), Font.PLAIN, 12));
        //originalLicenseLabelValue.setHorizontalAlignment(JLabel.CENTER);
        licenseDetailsPanel.add(originalLicenseLabelValue);


        JPanel licenseDataPanel = new JPanel();
        //licenseDataPanel.setPreferredSize(new Dimension(getWidth(), getHeight() / 2));

        // Section: license properties
        // type: per-machine/concurrent-sessions/unlimited
        // type-limit: number limit here
        // expiration: date/Lifetime

        Border licenseDetailsCategoryBorder1 = BorderFactory.createTitledBorder("Usage data");
        licenseDataPanel.setBorder(licenseDetailsCategoryBorder1);

        JLabel originalLicenseLabel1 = new JLabel("Original license id: ");
        originalLicenseLabel1.setBounds(0, 20, 100, 20);
        originalLicenseLabel1.setFont(new Font(originalLicenseLabel1.getFont().getName(), Font.PLAIN, 12));
        originalLicenseLabel1.setHorizontalAlignment(JLabel.CENTER);
        licenseDataPanel.add(originalLicenseLabel1);

        JLabel originalLicenseLabelValue1 = new JLabel("XXXX-XXXX-XXXX-XXXX");
        originalLicenseLabelValue1.setBounds(originalLicenseLabel.getWidth(), (int) originalLicenseLabel.getBounds().getY(), 150, 20);
        originalLicenseLabelValue1.setFont(new Font(originalLicenseLabelValue1.getFont().getName(), Font.PLAIN, 12));
        originalLicenseLabelValue1.setHorizontalAlignment(JLabel.CENTER);
        licenseDataPanel.add(originalLicenseLabelValue1);

        mainPanel.add(licenseDetailsPanel);
        mainPanel.add(licenseDataPanel);

        setContentPane(mainPanel);
    }

    @Override
    public void dispose() {
        super.dispose();
        isRunning = false;
    }
}

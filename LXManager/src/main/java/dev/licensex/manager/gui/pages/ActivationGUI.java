package dev.licensex.manager.gui.pages;

import dev.licensex.manager.Launcher;
import dev.licensex.manager.files.LXConfig;
import dev.licensex.manager.gui.JFrameX;
import dev.licensex.manager.utils.PathUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ActivationGUI extends JFrameX {
    private static boolean isRunning;

    private MainGUI mainGUI;
    private LXConfig licenseFile;
    private JPasswordField licenseInputField;
    private boolean activated = false;

    public ActivationGUI(MainGUI mainGUI, LXConfig licenseFile) {
        if (isRunning) return;
        isRunning = true;
        this.mainGUI = mainGUI;
        mainGUI.setEnabled(false);
        this.licenseFile = licenseFile;
    }

    public void startWindow() {
        buildWindow();
        setLocationRelativeTo(Launcher.mainGUI);
        setVisible(true);
    }

    class CustomKeyListener implements KeyListener {
        public void keyTyped(KeyEvent e) {}
        public void keyPressed(KeyEvent e) {}
        public void keyReleased(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                performActivation(licenseInputField.getText());
            }
        }
    }

    @Override
    protected void buildWindow() {
        setTitle("Activation");
        setBounds(200, 200, 370, 93);
        setLocationRelativeTo(Launcher.mainGUI);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.setLayout(null);

        JLabel licenseLabel = new JLabel();
        licenseLabel.setBounds(10, 5, 130, 20);
        licenseLabel.setText("license:");
        licenseLabel.setFont(new Font(licenseLabel.getFont().getName(), Font.PLAIN, licenseLabel.getFont().getSize() + 1));
        panel.add(licenseLabel);

        licenseInputField = new JPasswordField();
        licenseInputField.setBounds(10, 25, 250, 20);
        panel.add(licenseInputField);
        licenseInputField.setColumns(10);
        licenseInputField.addKeyListener(new CustomKeyListener());

        JButton inputBtn = new JButton("ENTER");
        inputBtn.setBounds(270, 24, 80, 22);
        panel.add(inputBtn);
        inputBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                performActivation(licenseInputField.getText());
            }
        });

        setContentPane(panel);
    }

    public void performActivation(String licenseId) {
        if (licenseId.length() < 19) {
            showDialog("Syntax error, license is too short", "Process failed!", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (checkLicenseWithServer(licenseId)) {
            // license approved, after checking with server
            licenseFile.set("license", licenseId);
            activated = true;
            dispose();

            LXConfig configFile = new LXConfig(PathUtil.getSelfPath() + "config.lx", false);
            String ipAddress = configFile.get("Ip-address") == null ? null : (configFile.get("Ip-address") + "");
            ConfigurationGUI configurationGUI = new ConfigurationGUI(mainGUI, configFile);

            if (ipAddress == null) {
                configurationGUI.startWindow();
            } else {
                configurationGUI.silentlyStartConf();
            }

        } else {
            showDialog("License is not activated", "Activation failed!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean checkLicenseWithServer(String licenseId) {
        return licenseId.contains("-");
    }

    @Override
    public void dispose() {
        super.dispose();
        isRunning = false;
        if (!activated) {
            System.exit(0);
        }
    }
}

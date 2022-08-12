package dev.licensex.manager.gui.pages;

import dev.licensex.manager.Launcher;
import dev.licensex.manager.files.LXConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActivationGUI extends JFrameX {
    private static boolean isRunning;

    private MainGUI mainGUI;
    private LXConfig licenseFile;

    public ActivationGUI(MainGUI mainGUI, LXConfig licenseFile) {
        if (isRunning) return;
        isRunning = true;
        this.mainGUI = mainGUI;
        mainGUI.setEnabled(false);

        this.licenseFile = licenseFile;

        buildWindow();
        setVisible(true);
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

        JPasswordField licenseInputField = new JPasswordField();
        licenseInputField.setBounds(10, 25, 250, 20);
        panel.add(licenseInputField);
        licenseInputField.setColumns(10);

        JButton inputBtn = new JButton("ENTER");
        inputBtn.setBounds(270, 24, 80, 22);
        panel.add(inputBtn);
        inputBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (licenseInputField.getText().length() < 19) {
                    showDialog("Syntax error, license is too short", "Process failed!", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String licenseId = licenseInputField.getText();

                if (checkLicenseWithServer(licenseId)) {
                    // license approved, after checking with server
                    licenseFile.set("license", licenseId);
                    mainGUI.setEnabled(true);
                    showDialog("License has been activated", "Activation complete!", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    showDialog("License is not activated", "Activation failed!", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        setContentPane(panel);
    }

    private boolean checkLicenseWithServer(String licenseId) {
        return licenseId.contains("-");
    }

    @Override
    public void dispose() {
        super.dispose();
        isRunning = false;
        if (!mainGUI.isEnabled())
            mainGUI.dispose();
    }
}

package dev.licensex.manager.gui.pages;

import dev.licensex.manager.Launcher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditLicensePropertiesGUI extends JFrameX {
    private static boolean isRunning;

    private JComboBox<String> noneComboBox;

    private String id;

    public EditLicensePropertiesGUI(String id) {
        if (isRunning) return;
        isRunning = true;
        this.id = id;
        buildWindow();
        setVisible(true);
    }

    @Override
    protected void buildWindow() {
        String os = System.getProperty("os.name");

        setTitle("Edit License Properties");
        setBounds(400, 280, os.contains("Mac OS X") ? 400 : 420, os.contains("Mac OS X") ? 200 : 210);
        setLocationRelativeTo(Launcher.mainGUI);
        JPanel panel = new JPanel();
        panel.setLayout(null);

        String[] IdTypeChoices = { "Groups", "Noise" };
        JComboBox<String> idTypeComboBox = new JComboBox<>(IdTypeChoices);
        idTypeComboBox.setSelectedItem(id.contains("-") ? "Groups" : "Noise");
        idTypeComboBox.setBounds(os.contains("Mac OS X") ? 11 : 12, 60, 100, 25);
        idTypeComboBox.setVisible(true);
        panel.add(idTypeComboBox);

        String[] licenseTypeChoices = { "Per-machine", "Live-sessions", "Unlimited" };
        JComboBox<String> licenseTypeComboBox = new JComboBox<>(licenseTypeChoices);
        licenseTypeComboBox.setSelectedItem("Per-machine");
        licenseTypeComboBox.setBounds(os.contains("Mac OS X") ? 151 : 152, 60, 100, 25);
        licenseTypeComboBox.setVisible(true);
        panel.add(licenseTypeComboBox);

        JTextField limitTextField = new JTextField();
        limitTextField.setBounds(os.contains("Mac OS X") ? 190 : 187, 90, 65, 19);
        limitTextField.setColumns(4);
        limitTextField.setText("1");
        panel.add(limitTextField);

        JLabel limitLabel = new JLabel("Limit: ");
        limitLabel.setBounds(os.contains("Mac OS X") ? 134 : 129, 90, 80, 20);
        limitLabel.setFont(new Font(limitLabel.getFont().getName(), Font.PLAIN, 12));
        limitLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(limitLabel);

        String[] noneChoices = { "Lifetime", "Expire" };
        noneComboBox = new JComboBox<>(noneChoices);
        noneComboBox.setSelectedItem("lifetime");
        noneComboBox.setBounds(os.contains("Mac OS X") ? 291 : 292, 60, 100, 25);
        noneComboBox.setVisible(true);
        panel.add(noneComboBox);

        JLabel dateLabel = new JLabel("Date: ");
        dateLabel.setBounds(os.contains("Mac OS X") ? 274 : 269, 90, 80, 20);
        dateLabel.setFont(new Font(dateLabel.getFont().getName(), Font.PLAIN, 12));
        dateLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(dateLabel);

        JTextField dateTextField = new JTextField();
        dateTextField.setBounds(os.contains("Mac OS X") ? 334 : 327, 90, 65, 19);
        dateTextField.setColumns(4);
        dateTextField.setEditable(false);
        panel.add(dateTextField);

        noneComboBox.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if ((noneComboBox.getSelectedItem() + "").equalsIgnoreCase("Lifetime")) {
                    dateTextField.setText("");
                    dateTextField.setEditable(false);
                } else {
                    dateTextField.setEditable(true);
                }
            }
        });

        JLabel licenseIdLabel = new JLabel("License-id: ");
        licenseIdLabel.setBounds(-50, 10, 220, 20);
        licenseIdLabel.setFont(new Font(licenseIdLabel.getFont().getName(), Font.PLAIN, 12));
        licenseIdLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(licenseIdLabel);

        JTextField idTextField = new JTextField();
        idTextField.setBounds(115, 10, 200, 19);
        idTextField.setColumns(10);
        panel.add(idTextField);
        idTextField.setText(id);

        JLabel userNameLabel = new JLabel("Username: ");
        userNameLabel.setBounds(-50, 33, 220, 20);
        userNameLabel.setFont(new Font(userNameLabel.getFont().getName(), Font.PLAIN, 12));
        userNameLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(userNameLabel);

        JTextField userNameTextField = new JTextField();
        userNameTextField.setBounds(115, 33, 200, 19);
        userNameTextField.setColumns(10);
        userNameTextField.setEditable(false);
        panel.add(userNameTextField);

        JCheckBox nameInLicenseCheckBox = new JCheckBox("Name in license");
        nameInLicenseCheckBox.setBounds(9, 90, 135, 20);
        panel.add(nameInLicenseCheckBox);
        nameInLicenseCheckBox.setEnabled(false);

        JButton btnGenerate = new JButton("generate");
        btnGenerate.setBounds(320, 10, os.contains("Mac OS X") ? 70 : 80, 19);
        panel.add(btnGenerate);

        JButton btnSave = new JButton("Save");
        btnSave.setBounds(110, 120, 180, 20);
        panel.add(btnSave);
        btnSave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                MainGUI.printLicenses();
                dispose();
            }
        });

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(110, 143, 180, 20);
        panel.add(btnCancel);
        btnCancel.addActionListener(new ActionListener() {

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

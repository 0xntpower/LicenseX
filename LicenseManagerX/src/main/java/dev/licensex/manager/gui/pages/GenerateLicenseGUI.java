package dev.licensex.manager.gui.pages;

import dev.licensex.manager.Launcher;
import dev.licensex.manager.utils.StringUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerateLicenseGUI extends JFrame {

    private JComboBox<String> idTypeComboBox;
    private JComboBox<String> licenseTypeComboBox;
    private JComboBox<String> noneComboBox;

    public GenerateLicenseGUI() {
        buildWindow();
        show();
    }

    private void buildWindow() {
        String os = System.getProperty("os.name");

        setResizable(false);
        setTitle("Generate new license");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(400, 280, os.contains("Mac OS X") ? 400 : 420, os.contains("Mac OS X") ? 200 : 210);
        setLocationRelativeTo(Launcher.mainGUI);
        JPanel panel = new JPanel();
        panel.setLayout(null);

        String[] IdTypeChoices = { "Groups", "Noise" };
        idTypeComboBox = new JComboBox<>(IdTypeChoices);
        idTypeComboBox.setSelectedItem("Groups");
        idTypeComboBox.setBounds(os.contains("Mac OS X") ? 11 : 12, 60, 100, 25);
        idTypeComboBox.setVisible(true);
        panel.add(idTypeComboBox);

        String[] licenseTypeChoices = { "Per-machine", "Live-sessions" };
        licenseTypeComboBox = new JComboBox<>(licenseTypeChoices);
        licenseTypeComboBox.setSelectedItem("Per-machine");
        licenseTypeComboBox.setBounds(os.contains("Mac OS X") ? 151 : 152, 60, 100, 25);
        licenseTypeComboBox.setVisible(true);
        panel.add(licenseTypeComboBox);

        JTextField limitTextField = new JTextField();
        limitTextField.setBounds(os.contains("Mac OS X") ? 190 : 187, 90, 65, 19);
        limitTextField.setColumns(4);
        panel.add(limitTextField);

        JLabel limitLabel = new JLabel("Limit: ");
        limitLabel.setBounds(os.contains("Mac OS X") ? 134 : 129, 90, 80, 20);
        limitLabel.setFont(new Font(limitLabel.getFont().getName(), Font.PLAIN, 12));
        limitLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(limitLabel);

        String[] noneChoices = { "placeholder1", "placeholder2" };
        noneComboBox = new JComboBox<>(noneChoices);
        noneComboBox.setSelectedItem("placeholder1");
        noneComboBox.setBounds(os.contains("Mac OS X") ? 291 : 292, 60, 100, 25);
        noneComboBox.setVisible(true);
        panel.add(noneComboBox);

        JCheckBox placeholderCheckBox = new JCheckBox("Placeholder");
        placeholderCheckBox.setBounds(os.contains("Mac OS X") ? 289 : 289, 90, 135, 20);
        panel.add(placeholderCheckBox);

        JLabel licenseIdLabel = new JLabel("License-id: ");
        licenseIdLabel.setBounds(-50, 10, 220, 20);
        licenseIdLabel.setFont(new Font(licenseIdLabel.getFont().getName(), Font.PLAIN, 12));
        licenseIdLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(licenseIdLabel);

        JTextField idTextField = new JTextField();
        idTextField.setBounds(115, 10, 200, 19);
        idTextField.setColumns(10);
        panel.add(idTextField);
        idTextField.setText(generateLicenseString());

        JLabel userNameLabel = new JLabel("Username: ");
        userNameLabel.setBounds(-50, 33, 220, 20);
        userNameLabel.setFont(new Font(userNameLabel.getFont().getName(), Font.PLAIN, 12));
        userNameLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(userNameLabel);

        JTextField userNameTextField = new JTextField();
        userNameTextField.setBounds(115, 33, 200, 19);
        userNameTextField.setColumns(10);
        panel.add(userNameTextField);

        JCheckBox nameInLicenseCheckBox = new JCheckBox("Name in license");
        nameInLicenseCheckBox.setBounds(9, 90, 135, 20);
        panel.add(nameInLicenseCheckBox);
        nameInLicenseCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nameInLicenseCheckBox.isSelected()) {
                    if (userNameTextField.getText().length() > 0
                            && !idTextField.getText().contains(userNameTextField.getText()))
                        idTextField.setText(idTextField.getText() + '-' + userNameTextField.getText());
                } else {
                    if (userNameTextField.getText().length() > 0
                            && idTextField.getText().contains(userNameTextField.getText())) {
                        idTextField.setText(generateLicenseString());
                    }
                }
            }
        });

        JButton btnGenerate = new JButton("generate");
        btnGenerate.setBounds(320, 10, os.contains("Mac OS X") ? 70 : 80, 19);
        panel.add(btnGenerate);
        btnGenerate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (nameInLicenseCheckBox.isSelected() && userNameTextField.getText().length() > 0)
                    idTextField.setText(generateLicenseString() + '-' + userNameTextField.getText());
                else
                    idTextField.setText(generateLicenseString());
            }
        });

        idTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nameInLicenseCheckBox.isSelected() && userNameTextField.getText().length() > 0)
                    idTextField.setText(generateLicenseString() + '-' + userNameTextField.getText());
                else
                    idTextField.setText(generateLicenseString());
            }
        });


        JButton btnSave = new JButton("Save");
        btnSave.setBounds(110, 120, 180, 20);
        panel.add(btnSave);
        btnSave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                MainGUI.saveLicenseToProduct(idTextField.getText());
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

    private String generateLicenseString() {
        return (idTypeComboBox.getSelectedItem() + "").equals("Groups") ? StringUtil.generateString(4) + '-' +
                StringUtil.generateString(4) + '-' + StringUtil.generateString(4) +
                '-' + StringUtil.generateString(4) : StringUtil.generateString(19);
    }
}

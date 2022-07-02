package dev.licensex.manager.gui.pages;

import dev.licensex.manager.Launcher;
import dev.licensex.manager.utils.StringUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerateLicenseGUI extends JFrame {

    private JComboBox<String> idTypeComboBox;

    public GenerateLicenseGUI() {
        buildWindow();
        show();
    }

    private void buildWindow() {
        setResizable(false);
        setTitle("Generate new license");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(400, 280, 400, 200);
        setLocationRelativeTo(Launcher.mainGUI);
        JPanel panel = new JPanel();
        panel.setLayout(null);

        String[] IdTypeChoices = { "Groups", "Noise" };
        idTypeComboBox = new JComboBox<>(IdTypeChoices);
        idTypeComboBox.setSelectedItem("Groups");
        idTypeComboBox.setBounds(20, 85, 100, 25);
        idTypeComboBox.setVisible(true);
        panel.add(idTypeComboBox);

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
        nameInLicenseCheckBox.setBounds(20, 60, 135, 20);
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
        btnGenerate.setBounds(320, 10, 70, 19);
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

        JButton btnSave = new JButton("Save");
        btnSave.setBounds(110, 120, 180, 20);
        panel.add(btnSave);
        btnSave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

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

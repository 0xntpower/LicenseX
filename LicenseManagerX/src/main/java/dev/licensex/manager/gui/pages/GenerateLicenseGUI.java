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
        setVisible(true);
    }

    private void buildWindow() {
        String os = System.getProperty("os.name");

        setResizable(false);
        setTitle("Generate New License");
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

        String[] licenseTypeChoices = { "Per-machine", "Live-sessions", "Unlimited" };
        licenseTypeComboBox = new JComboBox<>(licenseTypeChoices);
        licenseTypeComboBox.setSelectedItem("Per-machine");
        licenseTypeComboBox.setBounds(os.contains("Mac OS X") ? 151 : 152, 60, 100, 25);
        licenseTypeComboBox.setVisible(true);
        panel.add(licenseTypeComboBox);

        JLabel limitLabel = new JLabel("Limit: ");
        limitLabel.setBounds(os.contains("Mac OS X") ? 134 : 129, 90, 80, 20);
        limitLabel.setFont(new Font(limitLabel.getFont().getName(), Font.PLAIN, 12));
        limitLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(limitLabel);

        JTextField limitTextField = new JTextField();
        limitTextField.setBounds(os.contains("Mac OS X") ? 190 : 187, 90, 65, 19);
        limitTextField.setColumns(4);
        limitTextField.setText("1");
        panel.add(limitTextField);

        licenseTypeComboBox.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                if ((licenseTypeComboBox.getSelectedItem() + "").equalsIgnoreCase("Unlimited")) {
                    limitTextField.setText("");
                    limitTextField.setEditable(false);
                } else {
                    limitTextField.setEditable(true);
                }
            }
        });

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

        noneComboBox.addActionListener (new ActionListener () {
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
                if (!licenseTypeComboBox.getSelectedItem().toString().equals("Unlimited")
                        && limitTextField.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "Please enter a license limit", "Process failed!", JOptionPane.ERROR_MESSAGE);
                    return;
                }

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

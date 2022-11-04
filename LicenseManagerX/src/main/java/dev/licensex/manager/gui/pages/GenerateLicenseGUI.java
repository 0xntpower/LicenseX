package dev.licensex.manager.gui.pages;

import dev.licensex.manager.Launcher;
import dev.licensex.manager.gui.EventConnector;
import dev.licensex.manager.gui.JFrameX;
import dev.licensex.manager.utils.LicenseData;
import dev.licensex.manager.utils.StringUtil;
import dev.licensex.manager.utils.enums.LICENSE_ID_TYPE;
import dev.licensex.manager.utils.enums.LIMIT_TYPE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GenerateLicenseGUI extends JFrameX {
    private static boolean isRunning;

    private static LicenseData lastLicenseData = new LicenseData();

    private JComboBox<String> idTypeComboBox;
    private JComboBox<String> licenseTypeComboBox;
    private JComboBox<String> expiresComboBox;
    private JTextField limitTextField;
    private JTextField idTextField;
    private JTextField userNameTextField;
    private JCheckBox nameInLicenseCheckBox;
    private JTextField dateTextField;

    public GenerateLicenseGUI() {
        if (isRunning) return;
        isRunning = true;
        buildWindow();
        loadLast();
        setVisible(true);
    }

    private void loadLast() {
        if (lastLicenseData.getIdType() == LICENSE_ID_TYPE.GROUPS)
            idTypeComboBox.setSelectedItem("Groups");
        else
            idTypeComboBox.setSelectedItem("Noise");

        if (lastLicenseData.getLimitType() == LIMIT_TYPE.PER_MACHINE)
            licenseTypeComboBox.setSelectedItem("Per-machine");
        else if (lastLicenseData.getLimitType() == LIMIT_TYPE.LIVE_SESSIONS)
            licenseTypeComboBox.setSelectedItem("Live-sessions");
        else licenseTypeComboBox.setSelectedItem("Unlimited");

        limitTextField.setText(lastLicenseData.getLimit() + "");

        if (lastLicenseData.isExpires())
            expiresComboBox.setSelectedItem("Expire");
        else expiresComboBox.setSelectedItem("lifetime");

        if (lastLicenseData.getId().length() == 0)
            idTextField.setText(generateLicenseString());

        userNameTextField.setText(lastLicenseData.getUsername());

        nameInLicenseCheckBox.setSelected(lastLicenseData.isNameInLicense());

        dateTextField.setText(lastLicenseData.getExpiration_date());
    }

    class CustomKeyListener implements KeyListener {
        public void keyTyped(KeyEvent e) {}
        public void keyPressed(KeyEvent e) {}
        public void keyReleased(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                saveLicense();
            }
        }
    }

    @Override
    protected void buildWindow() {
        String os = System.getProperty("os.name");

        setTitle("Generate New License");
        setBounds(400, 280, os.contains("Mac OS X") ? 400 : 420, os.contains("Mac OS X") ? 200 : 210);
        setLocationRelativeTo(Launcher.mainGUI);
        JPanel panel = new JPanel();
        panel.setLayout(null);

        String[] IdTypeChoices = { "Groups", "Noise" };
        idTypeComboBox = new JComboBox<>(IdTypeChoices);
        idTypeComboBox.setBounds(os.contains("Mac OS X") ? 11 : 12, 60, 100, 25);
        idTypeComboBox.setVisible(true);
        panel.add(idTypeComboBox);

        String[] licenseTypeChoices = { "Per-machine", "Live-sessions", "Unlimited" };
        licenseTypeComboBox = new JComboBox<>(licenseTypeChoices);
        licenseTypeComboBox.setBounds(os.contains("Mac OS X") ? 151 : 152, 60, 100, 25);
        licenseTypeComboBox.setVisible(true);
        panel.add(licenseTypeComboBox);

        JLabel limitLabel = new JLabel("Limit: ");
        limitLabel.setBounds(os.contains("Mac OS X") ? 134 : 129, 90, 80, 20);
        limitLabel.setFont(new Font(limitLabel.getFont().getName(), Font.PLAIN, 12));
        limitLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(limitLabel);

        limitTextField = new JTextField();
        limitTextField.setBounds(os.contains("Mac OS X") ? 190 : 187, 90, 65, 19);
        limitTextField.setColumns(4);
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
        expiresComboBox = new JComboBox<>(noneChoices);
        expiresComboBox.setBounds(os.contains("Mac OS X") ? 291 : 292, 60, 100, 25);
        expiresComboBox.setVisible(true);
        panel.add(expiresComboBox);

        JLabel dateLabel = new JLabel("Date: ");
        dateLabel.setBounds(os.contains("Mac OS X") ? 274 : 269, 90, 80, 20);
        dateLabel.setFont(new Font(dateLabel.getFont().getName(), Font.PLAIN, 12));
        dateLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(dateLabel);

        dateTextField = new JTextField();
        dateTextField.setBounds(os.contains("Mac OS X") ? 334 : 327, 90, 65, 19);
        dateTextField.setColumns(4);
        dateTextField.setEditable(false);
        panel.add(dateTextField);

        expiresComboBox.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                if ((expiresComboBox.getSelectedItem() + "").equalsIgnoreCase("Lifetime")) {
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

        idTextField = new JTextField();
        idTextField.setBounds(115, 10, 200, 19);
        idTextField.setColumns(10);
        panel.add(idTextField);
        idTextField.addKeyListener(new CustomKeyListener());

        JLabel userNameLabel = new JLabel("Username: ");
        userNameLabel.setBounds(-50, 33, 220, 20);
        userNameLabel.setFont(new Font(userNameLabel.getFont().getName(), Font.PLAIN, 12));
        userNameLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(userNameLabel);

        userNameTextField = new JTextField();
        userNameTextField.setBounds(115, 33, 200, 19);
        userNameTextField.setColumns(10);
        panel.add(userNameTextField);
        userNameTextField.addKeyListener(new CustomKeyListener());

        nameInLicenseCheckBox = new JCheckBox("Name in license");
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
                saveLicense();
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

    private void saveLicense() {
        if (!licenseTypeComboBox.getSelectedItem().toString().equals("Unlimited")
                && limitTextField.getText().length() == 0) {
            showDialog("Please fill all fields", "Process failed!", JOptionPane.ERROR_MESSAGE);
            return;
        }

        LICENSE_ID_TYPE id_type = (idTypeComboBox.getSelectedItem() + "").equalsIgnoreCase("Groups") ? LICENSE_ID_TYPE.GROUPS : LICENSE_ID_TYPE.NOISE;

        LIMIT_TYPE limit_type = LIMIT_TYPE.PER_MACHINE;
        String selectedItemStr = licenseTypeComboBox.getSelectedItem() + "";
        if (selectedItemStr.equalsIgnoreCase("Live-sessions"))
            limit_type = LIMIT_TYPE.LIVE_SESSIONS;
        else if (selectedItemStr.equalsIgnoreCase("Unlimited"))
            limit_type = LIMIT_TYPE.UNLIMITED;

        String limitNum = limitTextField.getText();
        if (!StringUtil.isStringNumber(limitNum)) {
            showDialog("Limit most be a number", "Process failed!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int limit = Integer.parseInt(limitNum);

        lastLicenseData = new LicenseData(idTextField.getText(), userNameTextField.getText(), nameInLicenseCheckBox.isSelected(),
                id_type, limit_type, limit, (expiresComboBox.getSelectedItem() + "").equalsIgnoreCase("Expire"), dateTextField.getText());

        new Thread(new Runnable() {
            public void run() {
                if (!EventConnector.onLicenseAdd(MainGUI.selectedNode.getParent().toString(),
                        MainGUI.selectedNode.toString(), "", idTextField.getText())) {
                    showDialog("Failed to add license", "Process failed!", JOptionPane.ERROR_MESSAGE);

                    MainUtils.removeLicenseFromProduct(idTextField.getText());
                    MainUtils.printLicenses();
                }
            }
        }).start();

        MainUtils.saveLicenseToProduct(idTextField.getText());
        MainUtils.printLicenses();
        dispose();
    }

    private String generateLicenseString() {
        return (idTypeComboBox.getSelectedItem() + "").equals("Groups") ? StringUtil.generateString(4) + '-' +
                StringUtil.generateString(4) + '-' + StringUtil.generateString(4) +
                '-' + StringUtil.generateString(4) : StringUtil.generateString(19);
    }

    @Override
    public void dispose() {
        super.dispose();
        isRunning = false;
    }
}

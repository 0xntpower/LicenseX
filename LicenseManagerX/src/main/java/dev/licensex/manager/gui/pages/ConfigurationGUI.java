package dev.licensex.manager.gui.pages;

import dev.licensex.manager.Launcher;
import dev.licensex.manager.files.ConfigValues;
import dev.licensex.manager.files.LXConfig;
import dev.licensex.manager.utils.StringUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigurationGUI extends JFrameX {
    private static boolean isRunning;

    private MainGUI mainGUI;
    private LXConfig configFile;

    private JComboBox<String> tlsComboBox;
    private JComboBox<String> encComboBox;
    private JTextField ipInputField;
    private JTextField portInputField;
    private JTextField statusField;
    private JComboBox<String> typeComboBox;

    public ConfigurationGUI(MainGUI mainGUI, LXConfig configFile) {
        if (isRunning) return;
        isRunning = true;
        this.mainGUI = mainGUI;
        mainGUI.setEnabled(false);
        this.configFile = configFile;
    }

    public void startWindow() {
        buildWindow();
        setVisible(true);
    }

    @Override
    protected void buildWindow() {
        String os = System.getProperty("os.name");

        setTitle("Configuration");
        setBounds(200, 200, 300, 250);
        setLocationRelativeTo(Launcher.mainGUI);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.setLayout(null);

        JLabel typeLabel = new JLabel();
        typeLabel.setBounds(10, 10, 130, 20);
        typeLabel.setText("Access type:");
        typeLabel.setFont(new Font(typeLabel.getFont().getName(), Font.PLAIN, typeLabel.getFont().getSize() + 1));
        panel.add(typeLabel);

        String[] typesChoices = { "IP & Port", "Domain" };
        typeComboBox = new JComboBox<>(typesChoices);
        typeComboBox.setBounds(110, 10, 100, 25);
        typeComboBox.setVisible(true);
        panel.add(typeComboBox);

        JLabel ipLabel = new JLabel();
        ipLabel.setBounds(10, 45, 130, 20);
        ipLabel.setText("Ip-address:");
        ipLabel.setFont(new Font(ipLabel.getFont().getName(), Font.PLAIN, ipLabel.getFont().getSize() + 1));
        panel.add(ipLabel);

        ipInputField = new JTextField();
        ipInputField.setBounds(80, 45, 135, 20);
        panel.add(ipInputField);
        ipInputField.setColumns(10);

        JLabel portLabel = new JLabel();
        portLabel.setBounds(10, 72, 130, 20);
        portLabel.setText("Port:");
        portLabel.setFont(new Font(portLabel.getFont().getName(), Font.PLAIN, portLabel.getFont().getSize() + 1));
        panel.add(portLabel);

        portInputField = new JTextField();
        portInputField.setBounds(80, 72, 135, 20);
        panel.add(portInputField);
        portInputField.setColumns(10);

        JLabel tlsLabel = new JLabel();
        tlsLabel.setBounds(10, 105, 130, 20);
        tlsLabel.setText("Supported TLS:");
        tlsLabel.setFont(new Font(tlsLabel.getFont().getName(), Font.PLAIN, tlsLabel.getFont().getSize() + 1));
        panel.add(tlsLabel);

        String[] tlsChoices = { "TLSv3", "TLSv2 & TLSv3" };
        tlsComboBox = new JComboBox<>(tlsChoices);
        tlsComboBox.setBounds(110, 105, 100, 25);
        tlsComboBox.setVisible(true);
        panel.add(tlsComboBox);

        JLabel doubleLayerEncLabel = new JLabel();
        doubleLayerEncLabel.setBounds(10, 140, 130, 20);
        doubleLayerEncLabel.setText("Encryption layers:");
        doubleLayerEncLabel.setFont(new Font(doubleLayerEncLabel.getFont().getName(), Font.PLAIN, doubleLayerEncLabel.getFont().getSize() + 1));
        panel.add(doubleLayerEncLabel);

        String[] encChoices = { "Single", "Double" };
        encComboBox = new JComboBox<>(encChoices);
        encComboBox.setBounds(110, 140, 100, 25);
        encComboBox.setVisible(true);
        panel.add(encComboBox);

        typeComboBox.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                ipLabel.setText((typeComboBox.getSelectedItem() + "").equalsIgnoreCase("Domain") ? "Domain:" : "Ip-address:");
            }
        });

        statusField = new JTextField();
        statusField.setBounds(145, 180, 130, 20);
        panel.add(statusField);
        statusField.setColumns(10);
        statusField.setEditable(false);
        statusField.setText("IDLE");

        JButton inputBtn = new JButton("ENTER");
        inputBtn.setBounds(10, 180, 80, 22);
        panel.add(inputBtn);
        inputBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (ipInputField.getText().length() < 4 || portInputField.getText().length() < 2) {
                    return;
                }

                // ToDo check that the ip or domain syntax is correct

                if (!StringUtil.isStringNumber(portInputField.getText())
                        || Integer.parseInt(portInputField.getText()) > 65535
                        || Integer.parseInt(portInputField.getText()) < 255) {
                    showDialog("Incorrect port value", "Process failed!", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                configFile.set("Ip-address", ipInputField.getText());
                configFile.set("Port", portInputField.getText());
                configFile.set("Supported-tls", (tlsComboBox.getSelectedItem() + ""));
                configFile.set("EncryptionLayers", (encComboBox.getSelectedItem() + ""));

                silentlyStartConf();

                dispose();
            }
        });

        setContentPane(panel);
    }

    public void silentlyStartConf() {
        ConfigValues.loadConfiguration(configFile);
        mainGUI.start();
    }

    @Override
    public void dispose() {
        super.dispose();
        isRunning = false;
        if (!mainGUI.isEnabled())
            mainGUI.dispose();
    }
}

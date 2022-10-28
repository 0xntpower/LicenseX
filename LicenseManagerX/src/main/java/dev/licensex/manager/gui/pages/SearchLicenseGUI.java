package dev.licensex.manager.gui.pages;

import dev.licensex.manager.Launcher;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchLicenseGUI extends JFrameX {
    private static boolean isRunning;

    public SearchLicenseGUI() {
        if (isRunning) return;
        isRunning = true;
        buildWindow();
        setVisible(true);
    }

    @Override
    protected void buildWindow() {
        String os = System.getProperty("os.name");

        setTitle("Search License");
        setBounds(400, 280, 380, 127);
        setLocationRelativeTo(Launcher.mainGUI);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JTextField searchTextField = new JTextField();
        searchTextField.setBounds(55, 10, 250, 19);
        searchTextField.setColumns(10);
        panel.add(searchTextField);

        JButton btnSave = new JButton("Search");
        btnSave.setBounds(90, 43, 180, 20);
        panel.add(btnSave);
        btnSave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (searchTextField.getText().length() == 0) {
                    showDialog("Please type in a license-id", "Process failed!", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                new LicenseInfoGUI();
                // ToDo search a license and expand the explorer and select it

                dispose();
            }
        });

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(90, os.contains("Mac OS X") ? 66 : 63, 180, 20);
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

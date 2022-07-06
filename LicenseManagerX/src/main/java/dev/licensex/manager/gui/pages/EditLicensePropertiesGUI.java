package dev.licensex.manager.gui.pages;

import dev.licensex.manager.Launcher;

import javax.swing.*;
import java.awt.*;

public class EditLicensePropertiesGUI extends JFrame {

    public EditLicensePropertiesGUI() {
        buildWindow();
        setVisible(true);
    }

    private void buildWindow() {
        setResizable(false);
        setTitle("Check For Updates");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(420, 150, 420, 130);
        setLayout(new BorderLayout());
        setLocationRelativeTo(Launcher.mainGUI);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        setContentPane(panel);
    }
}

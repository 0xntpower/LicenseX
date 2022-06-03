package dev.licensex.manager.gui.pages;

import dev.licensex.manager.Launcher;

import javax.swing.*;
import java.awt.*;

public class GenerateLicenseGUI extends JFrame {

    public GenerateLicenseGUI() {
        buildWindow();
        show();
    }

    private void buildWindow() {
        setResizable(false);
        setTitle("Generate New License");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(400, 280, 560, 260);
        setLayout(new BorderLayout());
        setLocationRelativeTo(Launcher.mainGUI);
    }
}

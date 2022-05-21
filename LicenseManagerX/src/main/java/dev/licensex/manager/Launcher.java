package dev.licensex.manager;

import dev.licensex.manager.gui.MainGUI;

import javax.swing.*;

public final class Launcher {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new MainGUI();
    }
}

package dev.licensex.manager.gui;

import javax.swing.*;

public abstract class JFrameX extends JFrame {

    public JFrameX() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        //setAlwaysOnTop(true);
    }

    protected abstract void buildWindow();

    protected void showDialog(String content, String title, int type) {
        //setAlwaysOnTop(false);
        JOptionPane.showMessageDialog(null, content, title, type);
        //setAlwaysOnTop(true);
    }
}

package dev.licensex.manager.gui.pages;

import javax.swing.*;

public abstract class JFrameX extends JFrame {

    public JFrameX() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setAlwaysOnTop(true);
    }

    protected abstract void buildWindow();

}

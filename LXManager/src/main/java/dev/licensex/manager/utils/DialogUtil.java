package dev.licensex.manager.utils;

import dev.licensex.manager.gui.JFrameX;
import lombok.experimental.UtilityClass;

import javax.swing.*;

@UtilityClass
public class DialogUtil {

    public static int showConfirmDialog(String content, String title, int type, JFrameX jFrameX) {
        return JOptionPane.showConfirmDialog(jFrameX, content, title, type);
    }

//    public static int showConfirmDialog(String content, String title, int type, JFrameX jFrameX) {
//        final JOptionPane pane = new JOptionPane(content, type);
//        final JDialog dialog = pane.createDialog(null, title);
//        dialog.setLocationRelativeTo(jFrameX);
//        dialog.setVisible(true);
//        dialog.dispose();
//        return (int) pane.getValue();
//    }
}

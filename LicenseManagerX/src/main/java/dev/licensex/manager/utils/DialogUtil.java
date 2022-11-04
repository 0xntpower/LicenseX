package dev.licensex.manager.utils;

import dev.licensex.manager.gui.JFrameX;
import lombok.experimental.UtilityClass;

import javax.swing.*;

@UtilityClass
public class DialogUtil {

    public static int showConfirmDialog(String content, String title, int type, JFrameX jFrameX) {
//        boolean tempOnTop = jFrameX.isAlwaysOnTop();
//        jFrameX.setAlwaysOnTop(false);
        int result = JOptionPane.showConfirmDialog(null, content, title, type);
//        jFrameX.setAlwaysOnTop(tempOnTop);
        return result;
    }
}

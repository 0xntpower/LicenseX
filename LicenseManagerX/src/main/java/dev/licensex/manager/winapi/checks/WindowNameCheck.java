package dev.licensex.manager.winapi.checks;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import dev.licensex.manager.winapi.Check;

import java.util.Arrays;
import java.util.List;

public class WindowNameCheck extends Check {
    private final List<String> popularSniffersNames = Arrays.asList("The Wireshark Network Analyzer", "Snipping Tool");

    @Override
    public void check() {
        for (String windowName : popularSniffersNames) {
            WinDef.HWND hwnd = User32.INSTANCE.FindWindow(null, windowName);
            if (hwnd != null)
                flag(windowName);
        }
    }
}

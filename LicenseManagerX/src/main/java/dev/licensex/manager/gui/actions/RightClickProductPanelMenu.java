package dev.licensex.manager.gui.actions;

import dev.licensex.manager.gui.pages.GenerateLicenseGUI;
import dev.licensex.manager.gui.pages.NewProductCategoryGUI;
import dev.licensex.manager.gui.pages.NewProductGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RightClickProductPanelMenu extends JPopupMenu {
    public RightClickProductPanelMenu() {
        JMenuItem generateNewLicenseMenuItem = new JMenuItem("Generate new license");
        generateNewLicenseMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GenerateLicenseGUI();
            }
        });

        JMenuItem refreshMenuItem = new JMenuItem("Refresh");
        refreshMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Todo insert refresh code here
            }
        });

        add(generateNewLicenseMenuItem);
        add(new JSeparator());
        add(refreshMenuItem);
    }
}

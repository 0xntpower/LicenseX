package dev.licensex.manager.gui.actions;

import dev.licensex.manager.gui.pages.NewProductCategoryGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RightClickDatabaseMenu extends JPopupMenu {
    public RightClickDatabaseMenu() {
        JMenuItem newProductCategoryMenuItem = new JMenuItem("New product category");
        newProductCategoryMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewProductCategoryGUI();
            }
        });
        JMenuItem refreshMenuItem = new JMenuItem("Refresh");
        refreshMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Todo insert refresh code here
            }
        });
        add(newProductCategoryMenuItem);
        add(new JSeparator());
        add(refreshMenuItem);
    }
}

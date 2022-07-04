package dev.licensex.manager.gui.actions;

import dev.licensex.manager.gui.pages.NewProductCategoryGUI;
import dev.licensex.manager.gui.pages.NewProductGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RightClickExplorerPanelMenu extends JPopupMenu {
    public RightClickExplorerPanelMenu() {
        JMenuItem newProductMenuItem = new JMenuItem("New product");
        newProductMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewProductGUI();
            }
        });

        JMenuItem newProductCategoryMenuItem = new JMenuItem("New product category");
        newProductCategoryMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewProductCategoryGUI();
            }
        });

        add(newProductMenuItem);
        add(new JSeparator());
        add(newProductCategoryMenuItem);
    }
}

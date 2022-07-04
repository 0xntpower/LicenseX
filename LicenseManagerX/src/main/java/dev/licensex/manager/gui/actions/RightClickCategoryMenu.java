package dev.licensex.manager.gui.actions;

import dev.licensex.manager.gui.pages.NewProductGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RightClickCategoryMenu extends JPopupMenu {
    public RightClickCategoryMenu() {
        JMenuItem newProductCategoryMenuItem = new JMenuItem("New product");
        newProductCategoryMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewProductGUI();
            }
        });
        JMenuItem deleteMenuItem = new JMenuItem("Delete");
        deleteMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        add(newProductCategoryMenuItem);
        add(new JSeparator());
        add(deleteMenuItem);
    }
}

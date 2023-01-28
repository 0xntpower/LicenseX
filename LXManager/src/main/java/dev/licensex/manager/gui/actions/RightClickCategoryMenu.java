package dev.licensex.manager.gui.actions;

import dev.licensex.manager.gui.EventConnector;
import dev.licensex.manager.gui.pages.MainGUI;
import dev.licensex.manager.gui.pages.MainUtil;
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
                int answerId = JOptionPane.showConfirmDialog(null, "This action is irreversible, are you sure you want to delete this category and all the products that's under it?",
                        "Action confirmation", JOptionPane.YES_NO_OPTION);
                if (answerId == JOptionPane.YES_OPTION) {
                    EventConnector.onCategoryDelete(MainGUI.selectedNode.toString(), "");
                    MainUtil.removeSelectedCategory();
                }
            }
        });

        add(newProductCategoryMenuItem);
        add(new JSeparator());
        add(deleteMenuItem);
    }
}

package dev.licensex.manager.gui.actions;

import dev.licensex.manager.gui.pages.MainGUI;
import dev.licensex.manager.gui.pages.NewProductCategoryGUI;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RightClickLicenseMenu extends JPopupMenu {

    public RightClickLicenseMenu() {

        // ToDo check if license is blocked here
        boolean isLicenseBlocked = false;
        JMenuItem blockMenuItem;

        if (isLicenseBlocked) {
            blockMenuItem = new JMenuItem("Unblock");
            blockMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // ToDo insert unblocking code here
                }
            });
        } else {
            blockMenuItem = new JMenuItem("Block");
            blockMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // ToDo insert blocking code here
                }
            });
        }

        JMenuItem deleteMenuItem = new JMenuItem("Delete");
        deleteMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultMutableTreeNode lastSelectedNode = MainGUI.selectedNode;
                ((DefaultTreeModel)MainGUI.tree.getModel()).nodeStructureChanged(MainGUI.rootNode);
                MainGUI.tree.expandPath(new TreePath(lastSelectedNode.getPath()));
            }
        });
        JMenuItem propertiesMenuItem = new JMenuItem("Edit Properties");
        propertiesMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewProductCategoryGUI();
            }
        });

        add(blockMenuItem);
        add(new JSeparator());
        add(deleteMenuItem);
        add(new JSeparator());
        add(propertiesMenuItem);
    }
}

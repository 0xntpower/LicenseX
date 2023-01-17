package dev.licensex.manager.gui.actions;

import dev.licensex.manager.gui.pages.MainGUI;
import dev.licensex.manager.gui.pages.NewProductCategoryGUI;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
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
                DefaultMutableTreeNode lastSelectedNode = MainGUI.selectedNode;
                ((DefaultTreeModel)MainGUI.tree.getModel()).nodeStructureChanged(MainGUI.rootNode);
                MainGUI.tree.expandPath(new TreePath(lastSelectedNode.getPath()));
            }
        });
        add(newProductCategoryMenuItem);
        add(new JSeparator());
        add(refreshMenuItem);
    }
}

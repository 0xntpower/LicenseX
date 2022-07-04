package dev.licensex.manager.gui.actions;

import dev.licensex.manager.gui.pages.GenerateLicenseGUI;
import dev.licensex.manager.gui.pages.MainGUI;
import dev.licensex.manager.gui.pages.NewProductCategoryGUI;
import dev.licensex.manager.gui.pages.NewProductGUI;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RightClickProductMenu extends JPopupMenu {

    public RightClickProductMenu() {
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

        JMenuItem deleteMenuItem = new JMenuItem("Delete");
        deleteMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                TreeNode lastSelectedNode = MainGUI.selectedNode.getParent();
//                MainGUI.tree.removeSelectionPath(new TreePath(MainGUI.selectedNode.getPath()));
//
//                // refresh and re-expand the tree
//                ((DefaultTreeModel)MainGUI.tree.getModel()).nodeStructureChanged(MainGUI.rootNode);
//                MainGUI.selectedNode = (DefaultMutableTreeNode) lastSelectedNode;
//                MainGUI.tree.expandPath(new TreePath(MainGUI.selectedNode.getPath()));
            }
        });

        add(generateNewLicenseMenuItem);
        add(new JSeparator());
        add(refreshMenuItem);
        add(new JSeparator());
        add(deleteMenuItem);
    }
}

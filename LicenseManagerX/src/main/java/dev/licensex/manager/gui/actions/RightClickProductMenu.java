package dev.licensex.manager.gui.actions;

import dev.licensex.manager.gui.EventConnector;
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

        JMenuItem deleteMenuItem = new JMenuItem("Delete");
        deleteMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int answerId = JOptionPane.showConfirmDialog(null, "This action is irreversible, are you sure you want to delete this product?", "Action confirmation", JOptionPane.YES_NO_OPTION);
                if (answerId == JOptionPane.YES_OPTION) {
                    //EventConnector.onProductDelete(MainGUI.selectedNode.getParent().toString(), MainGUI.selectedNode.toString(), ""); // ToDo fix this!
                    MainGUI.removeSelectedProductFromCategory();
                }
            }
        });

        add(generateNewLicenseMenuItem);
        add(new JSeparator());
        add(deleteMenuItem);
    }
}

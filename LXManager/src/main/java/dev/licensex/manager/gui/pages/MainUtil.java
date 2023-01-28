package dev.licensex.manager.gui.pages;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.util.LinkedList;
import java.util.List;

public class MainUtil {
    public static void removeSelectedCategory() {
        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) MainGUI.selectedNode.getParent();

        DefaultTreeModel model = (DefaultTreeModel) MainGUI.tree.getModel();
        TreePath path = new TreePath(MainGUI.selectedNode.getPath());

        // remove all the products that's under it

        // remove the category from database

        // remove the category visually
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
        model.removeNodeFromParent(node);

        MainGUI.selectedNode = parent;

        ((DefaultTreeModel) MainGUI.tree.getModel()).nodeStructureChanged(MainGUI.rootNode);
        MainGUI.tree.expandPath(new TreePath(MainGUI.selectedNode.getPath()));

        printLicenses();
    }

    public static void removeCategory(DefaultMutableTreeNode toDelete) {
        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) toDelete.getParent();

        DefaultTreeModel model = (DefaultTreeModel) MainGUI.tree.getModel();
        TreePath path = new TreePath(toDelete.getPath());

        // remove all the products that's under it

        // remove the category from database

        // remove the category visually
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
        model.removeNodeFromParent(node);

        MainGUI.selectedNode = parent;

        ((DefaultTreeModel) MainGUI.tree.getModel()).nodeStructureChanged(MainGUI.rootNode);
        MainGUI.tree.expandPath(new TreePath(toDelete.getPath()));

        printLicenses();
    }

    public static void removeSelectedProductFromCategory() {
        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) MainGUI.selectedNode.getParent();

        DefaultTreeModel model = (DefaultTreeModel) MainGUI.tree.getModel();
        TreePath path = new TreePath(MainGUI.selectedNode.getPath());

        // delete product
        MainGUI.productsLicensesData.remove(MainGUI.selectedNode.toString());

        // remove the product visually
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
        model.removeNodeFromParent(node);

        MainGUI.selectedNode = parent;

        ((DefaultTreeModel) MainGUI.tree.getModel()).nodeStructureChanged(MainGUI.rootNode);
        MainGUI.tree.expandPath(new TreePath(MainGUI.selectedNode.getPath()));

        printLicenses();
    }

    public static void removeProductFromCategory(DefaultMutableTreeNode toDelete) {
        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) toDelete.getParent();

        DefaultTreeModel model = (DefaultTreeModel) MainGUI.tree.getModel();
        TreePath path = new TreePath(toDelete.getPath());

        // delete product
        MainGUI.productsLicensesData.remove(toDelete.toString());

        // remove the product visually
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
        model.removeNodeFromParent(node);

        MainGUI.selectedNode = parent;

        ((DefaultTreeModel) MainGUI.tree.getModel()).nodeStructureChanged(MainGUI.rootNode);
        MainGUI.tree.expandPath(new TreePath(toDelete.getPath()));

        printLicenses();
    }

    public static void removeLicenseFromProduct(String license) {
        if (MainGUI.productsLicensesData.containsKey(MainGUI.selectedNode.toString())) {
            List<String> list = new LinkedList<>(MainGUI.productsLicensesData.get(MainGUI.selectedNode.toString()));
            list.remove(license);
            MainGUI.productsLicensesData.replace(MainGUI.selectedNode.toString(), list);
        }
    }

    public static void saveLicenseToProduct(String license) {
        if (!MainGUI.productsLicensesData.containsKey(MainGUI.selectedNode.toString())) {
            MainGUI.productsLicensesData.put(MainGUI.selectedNode.toString(), List.of(license));
        } else {
            List<String> list = new LinkedList<>(MainGUI.productsLicensesData.get(MainGUI.selectedNode.toString()));
            list.add(license);
            MainGUI.productsLicensesData.replace(MainGUI.selectedNode.toString(), list);
        }
    }

    public static void printLicenses() {
        boolean isProduct = MainGUI.selectedNode != null && MainGUI.selectedNode.getParent() != null && MainGUI.selectedNode.getParent().getParent() != null && MainGUI.selectedNode.getParent().getParent().toString().equals("Database");
        assert MainGUI.selectedNode != null;
        String title = MainGUI.selectedNode.toString().equals("Database") || !isProduct ? "No product selected" : MainGUI.selectedNode.toString();

        MainGUI.contentPaneTitle.setText(title);
        MainGUI.visualLicensesList.clear();
        if (MainGUI.productsLicensesData.containsKey(MainGUI.selectedNode.toString())) {
            for (String license : MainGUI.productsLicensesData.get(MainGUI.selectedNode.toString()))
                MainGUI.visualLicensesList.addElement(license);
        }
    }
}

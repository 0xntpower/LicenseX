package dev.licensex.manager.gui;

import javax.swing.*;

public class RightClickLeftPanelMenu extends JPopupMenu {
    public RightClickLeftPanelMenu() {
        JMenuItem newProductMenuItem = new JMenuItem("New product");
        JMenuItem newProductCategoryMenuItem = new JMenuItem("New product category");

        add(newProductMenuItem);
        add(newProductCategoryMenuItem);
    }
}

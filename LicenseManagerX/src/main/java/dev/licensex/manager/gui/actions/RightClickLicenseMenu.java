package dev.licensex.manager.gui.actions;

import dev.licensex.manager.gui.pages.EditLicensePropertiesGUI;
import dev.licensex.manager.gui.pages.MainGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RightClickLicenseMenu extends JPopupMenu {

    public RightClickLicenseMenu(String licenseId) {

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
                int answerId = JOptionPane.showConfirmDialog(null, "This action is irreversible, are you sure you want to delete this license?", "Action confirmation", JOptionPane.YES_NO_OPTION);
                if (answerId == JOptionPane.YES_OPTION) {
                    MainGUI.removeLicenseFromProduct(licenseId);
                    MainGUI.printLicenses();
                }
            }
        });
        JMenuItem propertiesMenuItem = new JMenuItem("Edit Properties");
        propertiesMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditLicensePropertiesGUI(licenseId);
            }
        });

        add(blockMenuItem);
        add(new JSeparator());
        add(deleteMenuItem);
        add(new JSeparator());
        add(propertiesMenuItem);
    }
}

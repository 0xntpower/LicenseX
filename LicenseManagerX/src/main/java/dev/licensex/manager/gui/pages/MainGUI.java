package dev.licensex.manager.gui.pages;

import dev.licensex.manager.Launcher;
import dev.licensex.manager.gui.actions.*;
import dev.licensex.manager.utils.ThemesUtil;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.JMenuBar;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class MainGUI extends JFrame {

    private static JLabel contentPaneTitle;

    // explorer pane
    public static JPanel leftPanel;

    // tree data
    public static JTree tree;
    public static DefaultMutableTreeNode selectedNode;
    public static DefaultMutableTreeNode rootNode;

    public static DefaultListModel<String> visualLicensesList;
    // temporary licenses data, will use database in the future
    public static Map<String, List<String>> productsLicensesData = new HashMap<>();

    public MainGUI() {
        buildWindow();
        setVisible(true);
    }

    public static void saveLicenseToProduct(String license) {
        if (!productsLicensesData.containsKey(selectedNode.toString())) {
            productsLicensesData.put(selectedNode.toString(), List.of(license));
        } else {
            List<String> list = new LinkedList<>(productsLicensesData.get(selectedNode.toString()));
            list.add(license);
            productsLicensesData.replace(selectedNode.toString(), list);
        }
    }

    public static void printLicenses() {
        contentPaneTitle.setText(selectedNode.toString());
        visualLicensesList.clear();
        if (productsLicensesData.containsKey(selectedNode.toString())) {
            for (String license : productsLicensesData.get(selectedNode.toString()))
                visualLicensesList.addElement(license);
        }
    }

    private void buildWindow() {
        setResizable(false);
        setTitle("LicenseManagerX");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 200, 1100, 680);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // ---- menu bar start
        JMenuBar menuBar = new JMenuBar();

        JMenu productMenu = new JMenu("Product");

        JMenuItem newProductMenuItem = new JMenuItem("New product");
        newProductMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (MainGUI.selectedNode.equals(MainGUI.rootNode)) {
                    JOptionPane.showMessageDialog(null, "No category selected", "Process failed!", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                new NewProductGUI();
            }
        });
        productMenu.add(newProductMenuItem);

        JMenuItem newProductCategoryMenuItem = new JMenuItem("New product category");
        newProductCategoryMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewProductCategoryGUI();
            }
        });
        productMenu.add(newProductCategoryMenuItem);

        productMenu.add(new JSeparator());

        JMenuItem refreshMenuItem = new JMenuItem("Refresh");
        refreshMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultMutableTreeNode lastSelectedNode = selectedNode;
                ((DefaultTreeModel)tree.getModel()).nodeStructureChanged(rootNode);
                tree.expandPath(new TreePath(lastSelectedNode.getPath()));
            }
        });
        productMenu.add(refreshMenuItem);

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ToDo save and close all connections here
                System.exit(0);
            }
        });
        productMenu.add(exitMenuItem);

        menuBar.add(productMenu);

        JMenu licenseMenu = new JMenu("License");

        JMenuItem generateLicenseMenuItem = new JMenuItem("Generate New License");
        generateLicenseMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (MainGUI.selectedNode == null
                        || MainGUI.selectedNode.equals(MainGUI.rootNode)
                        || selectedNode.getParent() != null
                        && selectedNode.getParent().toString().equals("Database")) {
                    JOptionPane.showMessageDialog(null, "No product selected", "Process failed!", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                new GenerateLicenseGUI();
            }
        });
        licenseMenu.add(generateLicenseMenuItem);

        menuBar.add(licenseMenu);

        JMenu ThemesMenu = new JMenu("Themes");

        JMenuItem defaultModeMenuItem = new JMenuItem("Light");
        defaultModeMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ThemesUtil.setDefaultSystemLookAndFeel();
                SwingUtilities.updateComponentTreeUI(Launcher.mainGUI);
            }
        });
        ThemesMenu.add(defaultModeMenuItem);

        ThemesMenu.add(new JSeparator());

        JMenuItem darkModeMenuItem = new JMenuItem("Dark");
        darkModeMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ThemesUtil.setDarkMode();
                SwingUtilities.updateComponentTreeUI(Launcher.mainGUI);
            }
        });
        ThemesMenu.add(darkModeMenuItem);

        menuBar.add(ThemesMenu);

        JMenu HelpMenu = new JMenu("Help");

        JMenuItem checkForUpdatesMenuItem = new JMenuItem("Check For Updates");
        checkForUpdatesMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CheckForUpdatesGUI();
            }
        });
        HelpMenu.add(checkForUpdatesMenuItem);

        HelpMenu.add(new JSeparator());

        JMenuItem aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AboutGUI();
            }
        });
        HelpMenu.add(aboutMenuItem);

        menuBar.add(HelpMenu);

        setJMenuBar(menuBar);
        contentPaneTitle = new JLabel("No product selected", SwingConstants.CENTER);
        getContentPane().add(contentPaneTitle);

        //getContentPane().add(menuBar, BorderLayout.PAGE_START);
        // --- menu bar end

        leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.LEFT);
        leftPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        leftPanel.setPreferredSize(new Dimension(getWidth() / 2, getHeight() - (menuBar.getHeight() + 80)));
        leftPanel.add(label);

        JPanel rightPanel = new JPanel(new BorderLayout(0, 0));
        JProgressBar bar = new JProgressBar();
        bar.setStringPainted(true);
        bar.setOpaque(false);
        bar.setVisible(false);
        rightPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        rightPanel.setPreferredSize(new Dimension(getWidth() / 3, getHeight() - (menuBar.getHeight() + 80)));
        rightPanel.add(bar);

        //create list
        visualLicensesList = new DefaultListModel<>();
        JList<String> list = new JList<>(visualLicensesList);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        JScrollPane listScrollPane = new JScrollPane(list);
        leftPanel.add(listScrollPane);

        MouseListener licenseRightClickListener = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == 3) {
                    // license has been right clicked
                    RightClickLicenseMenu menu = new RightClickLicenseMenu();
                    menu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        };
        list.addMouseListener(licenseRightClickListener);

        list.setBackground(leftPanel.getBackground());
        DefaultListCellRenderer listRenderer = (DefaultListCellRenderer) list.getCellRenderer();
        listRenderer.setBackground(leftPanel.getBackground().darker());

        //add list to panel
        rightPanel.add(list);

        JSplitPane spt = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel) {
            private final int location = 250;

            {
                setDividerLocation(location);
            }

            @Override
            public int getDividerLocation() {
                return location;
            }

            @Override
            public int getLastDividerLocation() {
                return location;
            }
        };

        add(spt, BorderLayout.SOUTH);

        rightPanel.addMouseListener(new RightClickProductListener());

        rootNode = new DefaultMutableTreeNode("Database", true);
        selectedNode = rootNode;

        tree = new JTree(rootNode);

        tree.addTreeSelectionListener(new TreeSelectionListener() {

            @Override
            public void valueChanged(TreeSelectionEvent e) {
                selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            }
        });

        MouseListener treeMouseListener = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {

                if (e.getButton() == 3) {

                    if (selectedNode == null) {
                        contentPaneTitle.setText("No product selected");
                        visualLicensesList.clear();
                        return;
                    }
                    if (selectedNode.toString().equals("Database")) {
                        // the database has been right-clicked
                        RightClickDatabaseMenu menu = new RightClickDatabaseMenu();
                        menu.show(e.getComponent(), e.getX(), e.getY());
                    } else {
                        if (selectedNode.getParent().toString().equals("Database")) {
                            // a category has been right-clicked
                            RightClickCategoryMenu menu = new RightClickCategoryMenu();
                            menu.show(e.getComponent(), e.getX(), e.getY());
                        } else if (selectedNode.getParent().getParent().toString().equals("Database")) {
                            // a product has been right-clicked
                            RightClickProductMenu menu = new RightClickProductMenu();
                            menu.show(e.getComponent(), e.getX(), e.getY());
                        }
                    }

                } else if (e.getButton() == 1) {

                    if (selectedNode.getParent() == null) {
                        contentPaneTitle.setText("No product selected");
                        visualLicensesList.clear();
                        return;
                    }
                    if (selectedNode.getParent().getParent() != null
                            && selectedNode.getParent().getParent().toString().equals("Database")) {
                        // a product has been left-clicked
                        printLicenses();
                    }

                }
            }
        };
        tree.addMouseListener(treeMouseListener);

        // change background color of items
        DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) tree.getCellRenderer();
        renderer.setBackgroundNonSelectionColor(leftPanel.getBackground());

        // change background color of all tree
        tree.setBackground(leftPanel.getBackground());

        leftPanel.add(tree);
    }
}

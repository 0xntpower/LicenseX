package dev.licensex.manager.gui.pages;

import com.formdev.flatlaf.util.StringUtils;
import dev.licensex.manager.Launcher;
import dev.licensex.manager.utils.StringUtil;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;

public class NewProductGUI extends JFrame {

    private static final List<Integer> specialKeys = new LinkedList<>();

    static {
        specialKeys.add(KeyEvent.VK_CONTROL);
        specialKeys.add(KeyEvent.VK_ALT);
        specialKeys.add(KeyEvent.VK_SHIFT);
        specialKeys.add(KeyEvent.VK_CAPS_LOCK);
        specialKeys.add(KeyEvent.VK_TAB);
        specialKeys.add(KeyEvent.VK_WINDOWS);
    }

    public NewProductGUI() {
        buildWindow();
        show();
    }

    private void buildWindow() {
        String os = System.getProperty("os.name");

        setResizable(false);
        setTitle("New Product");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(400, 280, 380, 147);
        setLocationRelativeTo(Launcher.mainGUI);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel productNameLabel = new JLabel("product name: ");
        productNameLabel.setBounds(-50, 10, 220, 20);
        productNameLabel.setFont(new Font(productNameLabel.getFont().getName(), Font.PLAIN, 12));
        productNameLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(productNameLabel);

        JTextField nameTextField = new JTextField();
        nameTextField.setBounds(115, 10, 200, 19);
        nameTextField.setColumns(10);
        panel.add(nameTextField);

        JTextField idTextField = new JTextField();
        idTextField.setBounds(115, 30, 200, 19);
        idTextField.setColumns(10);
        panel.add(idTextField);

        nameTextField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (nameTextField.getText().length() < 2 && e.getKeyCode() == KeyEvent.VK_BACK_SPACE
                        || isInstantFullDelete(e))
                    idTextField.setText("");
                else if (!specialKeys.contains(e.getKeyCode()))
                    idTextField.setText(StringUtil.generateString(6));
            }
        });

        JLabel productIdLabel = new JLabel("product id: ");
        productIdLabel.setBounds(-50, 30, 240, 20);
        productIdLabel.setFont(new Font(productIdLabel.getFont().getName(), Font.PLAIN, 12));
        productIdLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(productIdLabel);

        JButton btnSave = new JButton("Save");
        btnSave.setBounds(90, 63, 180, 20);
        panel.add(btnSave);
        btnSave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (nameTextField.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields", "Process failed!", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (MainGUI.selectedNode.equals(MainGUI.rootNode)) {
                    JOptionPane.showMessageDialog(null, "You can create products only inside categories", "Process failed!", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (MainGUI.selectedNode.getParent() == null) return;
                if (MainGUI.selectedNode.getParent().getParent() != null
                        && MainGUI.selectedNode.getParent().getParent().toString().equals("Database")) {
                    JOptionPane.showMessageDialog(null, "Invalid directory, can't create a product inside of a product", "Process failed!", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // add node to tree
                DefaultMutableTreeNode product = new DefaultMutableTreeNode(nameTextField.getText());
                MainGUI.selectedNode.add(product);

                // refresh and re-expand the tree
                DefaultMutableTreeNode lastSelectedNode = MainGUI.selectedNode;
                ((DefaultTreeModel)MainGUI.tree.getModel()).nodeStructureChanged(MainGUI.rootNode);
                MainGUI.tree.expandPath(new TreePath(lastSelectedNode.getPath()));

                dispose();
            }
        });

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(90, os.contains("Mac OS X") ? 86 : 83, 180, 20);
        panel.add(btnCancel);
        btnCancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setContentPane(panel);
    }

    private boolean isInstantFullDelete(KeyEvent e) {
        if (System.getProperty("os.name").contains("Mac OS X"))
            return e.getKeyCode() == KeyEvent.VK_BACK_SPACE && e.isAltDown();
        return e.getKeyCode() == KeyEvent.VK_BACK_SPACE && e.isControlDown();
    }
}

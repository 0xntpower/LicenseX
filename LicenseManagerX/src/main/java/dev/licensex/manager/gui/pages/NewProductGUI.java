package dev.licensex.manager.gui.pages;

import dev.licensex.manager.Launcher;

import javax.swing.*;

public class NewProductGUI extends JFrame {

    public NewProductGUI() {
        buildWindow();
        show();
    }

    private void buildWindow() {
        setResizable(false);
        setTitle("New Product");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(400, 280, 380, 235);
        setLocationRelativeTo(Launcher.mainGUI);

        JPanel panel = new JPanel();
        panel.setLayout(null);

//        JLabel productNameLabel = new JLabel("product name: ");
//        productNameLabel.setBounds(-50, 19, 240, 20);
//        productNameLabel.setFont(new Font(productNameLabel.getFont().getName(), Font.PLAIN, 12));
//        productNameLabel.setHorizontalAlignment(JLabel.CENTER);
//        panel.add(productNameLabel);
//
//        JTextField nameTextField = new JTextField();
//        nameTextField.setBounds(115, 16, 200, 19);
//        nameTextField.setColumns(10);
//        panel.add(nameTextField);
//
//        JTextField idTextField = new JTextField();
//        idTextField.setBounds(115, 37, 200, 19);
//        idTextField.setColumns(10);
//        panel.add(idTextField);
//
//        nameTextField.addKeyListener(new KeyAdapter() {
//            public void keyPressed(KeyEvent e) {
//                if (nameTextField.getText().length() < 2 && e.getKeyCode() == KeyEvent.VK_BACK_SPACE
//                        || e.getKeyCode() == KeyEvent.VK_BACK_SPACE && e.isControlDown())
//                    idTextField.setText("");
//                else
//                    idTextField.setText(StringUtils.generateString(8));
//            }
//        });
//
//        JLabel productIdLabel = new JLabel("product id: ");
//        productIdLabel.setBounds(-50, 37, 240, 20);
//        productIdLabel.setFont(new Font(productIdLabel.getFont().getName(), Font.PLAIN, 12));
//        productIdLabel.setHorizontalAlignment(JLabel.CENTER);
//        panel.add(productIdLabel);
//
//        JSeparator jSeparator1 = new JSeparator();
//        jSeparator1.setBounds(25, 65, 310, 20);
//        panel.add(jSeparator1);
//
//        JLabel licenseSizeLabel = new JLabel("license size: ");
//        licenseSizeLabel.setBounds(0, 67, 120, 20);
//        licenseSizeLabel.setFont(new Font(licenseSizeLabel.getFont().getName(), Font.PLAIN, 11));
//        licenseSizeLabel.setHorizontalAlignment(JLabel.CENTER);
//        panel.add(licenseSizeLabel);
//
//        String[] sizeOptions = { "Small", "Medium", "Large" };
//        JComboBox<String> dropDownLicenseSize = new JComboBox<String>(sizeOptions);
//        dropDownLicenseSize.setSelectedItem("Medium");
//        dropDownLicenseSize.setBounds(27, 87, 80, 20);
//        dropDownLicenseSize.setVisible(true);
//        panel.add(dropDownLicenseSize);
//
//        JSeparator jSeparator2 = new JSeparator();
//        jSeparator2.setBounds(25, 135, 310, 20);
//        panel.add(jSeparator2);
//
//        JButton btnSave = new JButton("Save");
//        btnSave.setBounds(90, 145, 180, 20);
//        panel.add(btnSave);
//        btnSave.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
//
//        JButton btnCancel = new JButton("Cancel");
//        btnCancel.setBounds(90, 165, 180, 20);
//        panel.add(btnCancel);
//        btnCancel.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                dispose();
//            }
//        });

        setContentPane(panel);
    }
}

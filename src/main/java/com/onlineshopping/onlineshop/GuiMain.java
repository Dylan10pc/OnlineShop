package com.onlineshopping.onlineshop;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class GuiMain extends JFrame {
    ArrayList<Product> ProductList;
    JTable tableproduct;
    TableModelGui tablemodel;
    JTextArea detailArea;

    public GuiMain(ArrayList<Product> ProductList) {
        this.ProductList = ProductList;
        tablemodel = new TableModelGui(ProductList);
        tableproduct = new JTable(tablemodel);

        tableproduct.setColumnModel(new JTable(tablemodel).getColumnModel());
        tableproduct.setAutoCreateRowSorter(true);
        tableproduct.setBorder(new EmptyBorder(5,5,5,5) );

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 3, 10, 10));

        JLabel dropdownLabel = new JLabel("What Would You Like To Search?");
        dropdownLabel.setPreferredSize(new Dimension(10, 30));
        panel.add(dropdownLabel);
        JComboBox dropdownbox = new JComboBox(new String[] { "All", "Electronics", "Clothing" });
        dropdownbox.setPreferredSize(new Dimension(10, 30));
        panel.add(dropdownbox);
        panel.add(Box.createRigidArea(new Dimension(0, 0)));

        JButton cartbutton = new JButton("Shopping Cart");
        panel.add(cartbutton);
        
        panel.setBorder(new EmptyBorder(10, 20, 10, 20));

        JScrollPane TableScrollPanel = new JScrollPane(tableproduct);
        JPanel panel2 = new JPanel();
        panel2.add(TableScrollPanel);

        dropdownbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCategory = (String) dropdownbox.getSelectedItem();
                if (selectedCategory.equals("All")) {
                    tablemodel.setProductList(ProductList);
                } else {
                    ArrayList<Product> filteredList = new ArrayList<>();
                    for (Product product : ProductList) {
                        if (("Electronics".equals(selectedCategory) && product instanceof Electronics) ||
                            ("Clothing".equals(selectedCategory) && !(product instanceof Electronics))) {
                            filteredList.add(product);
                        }
                    }
                    tablemodel.setProductList(filteredList);
                }
            }
        });

        detailArea = new JTextArea();
        detailArea.setEditable(false);
        JPanel paneltextareadetail = new JPanel();
        paneltextareadetail.setLayout(new BorderLayout());
        paneltextareadetail.add(detailArea, BorderLayout.CENTER);

        tableproduct.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = tableproduct.getSelectedRow();

                if (selectedRow >= 0) {
                    Product selectedProduct = ProductList.get(selectedRow);
                    displayProductDetails(selectedProduct);
                }
            }
        });

        for (int i = 0; i < tableproduct.getColumnCount(); i++) {
            TableColumn column = tableproduct.getColumnModel().getColumn(i);
            column.setCellRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                    // Check the condition for row color using TableModelGui method
                    if (table.getModel() instanceof TableModelGui) {
                        TableModelGui model = (TableModelGui) table.getModel();
                        if (model.producthighlightrow(row)) {
                            c.setBackground(Color.RED);
                        } else {
                            c.setBackground(table.getBackground());
                        }
                    }

                    return c;
                }
            });
        }   

        add(panel, BorderLayout.NORTH);
        add(panel2, BorderLayout.CENTER);
        add(paneltextareadetail,BorderLayout.SOUTH);
    }

    private void displayProductDetails(Product product) {
            StringBuilder detailtextarea = new StringBuilder();
            detailtextarea.append("Selected Product - Details").append("\n");
            detailtextarea.append("Product ID: ").append(product.getID()).append("\n");
            detailtextarea.append("Name: ").append(product.getName()).append("\n");
            detailtextarea.append("Category: ").append((product instanceof Electronics) ? "Electronics" : "Clothing").append("\n");
            detailtextarea.append("Price: ").append(product.getPrice()).append("\n");
            if (product instanceof Electronics) {
                Electronics electronicProduct = (Electronics) product;
                detailtextarea.append("Brand: ").append(electronicProduct.getBrand()).append("\n");
                detailtextarea.append("Warranty: ").append(electronicProduct.getWarranty()).append("\n");
            } else if (product instanceof Clothing) {
                Clothing clothingProduct = (Clothing) product;
                detailtextarea.append("Colour: ").append(clothingProduct.getColour()).append("\n");
                detailtextarea.append("Size: ").append(clothingProduct.getSize()).append("\n");
            }
            detailtextarea.append("Items: ").append(product.getNumberofavailableitems()).append("\n");
            detailArea.setText(detailtextarea.toString());
    }

    public void ShowGuiMain() {
        GuiMain mainFrame = new GuiMain(ProductList);
        mainFrame.setTitle("The Product Shop");
        mainFrame.setSize(1000, 1000);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

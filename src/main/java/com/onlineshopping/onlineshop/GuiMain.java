package com.onlineshopping.onlineshop;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class GuiMain extends JFrame {
    ArrayList<Product> ProductList;
    JTable tableproduct;
    TableModelGui tablemodel;
    JTextArea detailArea;
    ShoppingCart shoppingCart;
    JTable cartTable;
    DefaultTableModel cartTableModel;


    public GuiMain(ArrayList<Product> ProductList) {
        this.ProductList = ProductList;
        tablemodel = new TableModelGui(ProductList);
        tableproduct = new JTable(tablemodel);

        JPanel topmenu = new JPanel();
        topmenu.setLayout(new GridLayout(1, 3, 10, 10));

        JLabel dropdownLabel = new JLabel("What Would You Like To Search?");
        dropdownLabel.setPreferredSize(new Dimension(10, 30));
        topmenu.add(dropdownLabel);
        JComboBox dropdownbox = new JComboBox(new String[] { "All", "Electronics", "Clothing" });
        dropdownbox.setPreferredSize(new Dimension(10, 30));
        topmenu.add(dropdownbox);
        topmenu.add(Box.createRigidArea(new Dimension(0, 0)));

        JButton cartbutton = new JButton("Shopping Cart");
        topmenu.add(cartbutton);
        cartbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OnlineCart();
            }
        });

        topmenu.setBorder(new EmptyBorder(10, 20, 10, 20));

        dropdownbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) dropdownbox.getSelectedItem();
                if (selected.equals("All")) {
                    tablemodel.setProductList(ProductList);
                } else {
                    ArrayList<Product> filteredList = new ArrayList<>();
                    for (Product product : ProductList) {
                        if (("Electronics".equals(selected) && product instanceof Electronics) ||
                                ("Clothing".equals(selected) && !(product instanceof Electronics))) {
                            filteredList.add(product);
                        }
                    }
                    tablemodel.setProductList(filteredList);
                }
            }
        });

        tableproduct.setColumnModel(new JTable(tablemodel).getColumnModel());
        tableproduct.setAutoCreateRowSorter(true);
        tableproduct.setBorder(new EmptyBorder(5, 5, 5, 5));

        JScrollPane TableScrollPanel = new JScrollPane(tableproduct);
        JPanel tablearea = new JPanel();
        tablearea.add(TableScrollPanel);

        for (int i = 0; i < tableproduct.getColumnCount(); i++) {
            TableColumn lessitemsColumn = tableproduct.getColumnModel().getColumn(i);
            lessitemsColumn.setCellRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                        boolean hasFocus, int row, int column) {
                    Component colorComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                            row, column);
                    if (table.getModel() instanceof TableModelGui) {
                        TableModelGui model = (TableModelGui) table.getModel();
                        if (model.producthighlightrow(row)) {
                            colorComponent.setBackground(Color.RED);
                        } else {
                            colorComponent.setBackground(table.getBackground());
                        }
                    }
                    return colorComponent;
                }
            });
        }

        detailArea = new JTextArea();
        detailArea.setEditable(false);
        JPanel paneltextareadetail = new JPanel();
        paneltextareadetail.setLayout(new BorderLayout());
        paneltextareadetail.add(detailArea, BorderLayout.CENTER);
        JButton addToCartButton = new JButton("Add to Cart");
        paneltextareadetail.add(addToCartButton, BorderLayout.SOUTH);

        tableproduct.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = tableproduct.getSelectedRow();
                if (selectedRow >= 0) {
                    Product selectedProduct = ProductList.get(selectedRow);
                    detaildisplay(selectedProduct);
                }
            }
        });

        shoppingCart = new ShoppingCart();
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableproduct.getSelectedRow();
                if (selectedRow >= 0) {
                    Product selectedProduct = ProductList.get(selectedRow);
                    shoppingCart.AddProduct(selectedProduct);
                }
            }
        });

        String[] cartColumns = { "Product ID", "Quantity", "Price" };
        cartTableModel = new DefaultTableModel(cartColumns, 0);
        cartTable = new JTable(cartTableModel);

        add(topmenu, BorderLayout.NORTH);
        add(tablearea, BorderLayout.CENTER);
        add(paneltextareadetail, BorderLayout.SOUTH);
    }

    private void detaildisplay(Product product) {
        StringBuilder detailtextarea = new StringBuilder();
        detailtextarea.append("Selected Product - Details").append("\n");
        detailtextarea.append("Product ID: ").append(product.getID()).append("\n");
        detailtextarea.append("Name: ").append(product.getName()).append("\n");
        detailtextarea.append("Category: ").append((product instanceof Electronics) ? "Electronics" : "Clothing")
                .append("\n");
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

    private void OnlineCart() {
        JFrame cartFrame = new JFrame("Shopping Cart");
        cartFrame.setSize(600, 400);

        String[] cartColumns = { "Product ID", "Quantity", "Price" };
        DefaultTableModel cartTableModel = new DefaultTableModel(cartColumns, 0);
        JTable cartTable = new JTable(cartTableModel);

        cartTableModel.setRowCount(0);

        for (Product cartProduct : shoppingCart.getProducts()) {
            Object[] cartRow = new Object[3];
            cartRow[0] = cartProduct.getID() + "  " + getProductDetails(cartProduct) + "  ";
            cartRow[1] = shoppingCart.getProductQuantity(cartProduct);
            cartRow[2] = cartProduct.getPrice() * shoppingCart.getProductQuantity(cartProduct);
            cartTableModel.addRow(cartRow);
        }

        JScrollPane cartTableScrollPanel = new JScrollPane(cartTable);

        double totalPrice = shoppingCart.CalculateTotalPrice();
        JTextArea totalPriceArea = new JTextArea("Total Price: " + totalPrice);
        totalPriceArea.setEditable(false);

        JPanel cartPanel = new JPanel();
        cartPanel.setLayout(new BorderLayout());
        cartPanel.add(cartTableScrollPanel, BorderLayout.CENTER);
        cartPanel.add(totalPriceArea, BorderLayout.SOUTH);

        cartFrame.add(cartPanel);
        cartFrame.setVisible(true);
    }

    private String getProductDetails(Product product) {
        if (product instanceof Electronics) {
            Electronics electronicProduct = (Electronics) product;
            return "Brand: " + electronicProduct.getBrand() + ", Warranty: " + electronicProduct.getWarranty();
        } else if (product instanceof Clothing) {
            Clothing clothingProduct = (Clothing) product;
            return "Size: " + clothingProduct.getSize() + ", Color: " + clothingProduct.getColour();
        } else {
            return "";
        }
    }

    public void ShowGuiMain() {
        GuiMain mainFrame = new GuiMain(ProductList);
        mainFrame.setTitle("The Product Shop");
        mainFrame.setSize(1000, 1000);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

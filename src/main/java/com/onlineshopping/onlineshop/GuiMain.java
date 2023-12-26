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

        //Create and adds components to the top menu panel
        JLabel dropdownLabel = new JLabel("What Would You Like To Search?");
        dropdownLabel.setPreferredSize(new Dimension(10, 30));
        topmenu.add(dropdownLabel);
        JComboBox dropdownbox = new JComboBox(new String[] { "All", "Electronics", "Clothing" });
        dropdownbox.setPreferredSize(new Dimension(10, 30));
        topmenu.add(dropdownbox);
        topmenu.add(Box.createRigidArea(new Dimension(0, 0)));

        JButton cartbutton = new JButton("Shopping Cart");
        topmenu.add(cartbutton);

        //makes button functional when you press it opens up the shopping cart
        cartbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //sends you to the online cart method
                OnlineCart();
            }
        });

        topmenu.setBorder(new EmptyBorder(10, 20, 10, 20));

        //Makes the dropdown button functional so it filters product on the table
        dropdownbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String productselected = (String) dropdownbox.getSelectedItem();
                if (productselected.equals("All")) {
                    tablemodel.setProductList(ProductList);
                } else {
                    //Code filters if i select either one
                    ArrayList<Product> filtered = new ArrayList<>();
                    for (Product product : ProductList) {
                        if (("Electronics".equals(productselected) && product instanceof Electronics) ||
                                ("Clothing".equals(productselected) && !(product instanceof Electronics))) {
                            filtered.add(product);
                        }
                    }
                    tablemodel.setProductList(filtered);
                }
            }
        });

        //sets the product table up

        tableproduct.setColumnModel(new JTable(tablemodel).getColumnModel());
        tableproduct.setAutoCreateRowSorter(true);
        tableproduct.setBorder(new EmptyBorder(5, 5, 5, 5));

        JScrollPane TableScrollPanel = new JScrollPane(tableproduct);
        JPanel tablearea = new JPanel();
        tablearea.add(TableScrollPanel);

        //customise row for background color
        for (int i = 0; i < tableproduct.getColumnCount(); i++) {
            TableColumn lessitemscolumn = tableproduct.getColumnModel().getColumn(i);
            lessitemscolumn.setCellRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    Component colorComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    if (table.getModel() instanceof TableModelGui) {
                        TableModelGui model = (TableModelGui) table.getModel();
                        //if product has less than 3 items it turns red
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

        //sets up the detail area
        detailArea = new JTextArea();
        detailArea.setEditable(false);
        JPanel paneltextareadetail = new JPanel();
        paneltextareadetail.setLayout(new BorderLayout());
        paneltextareadetail.add(detailArea, BorderLayout.CENTER);
        
        JButton addToCartButton = new JButton("Add to Cart");
        paneltextareadetail.add(addToCartButton, BorderLayout.SOUTH);

        //makes it so when i click on a product on a table a list of details appears

        tableproduct.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int rowisselected = tableproduct.getSelectedRow();
                if (rowisselected >= 0) {
                    Product selectedproduct = ProductList.get(rowisselected);
                    //sends you to a detaildisplay methods
                    detaildisplay(selectedproduct);
                }
            }
        });

        shoppingCart = new ShoppingCart();

         // makes cart button functional adds a selected product to the shopping cart
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedproductrow = tableproduct.getSelectedRow();
                if (selectedproductrow >= 0) {
                    Product addtocartproduct = ProductList.get(selectedproductrow);
                    shoppingCart.AddProduct(addtocartproduct);
                }
            }
        });

        add(topmenu, BorderLayout.NORTH);
        add(tablearea, BorderLayout.CENTER);
        add(paneltextareadetail, BorderLayout.SOUTH);
    }

    //display info about the selected product
    private void detaildisplay(Product product) {
        //This stringbuilder appends the strings to the current sequence
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

    //shows the shopping cart interface
    private void OnlineCart() {
        JFrame shoppingcartframe = new JFrame("Shopping Cart");
        shoppingcartframe.setSize(600, 400);

        String[] cartnames = { "Product", "Quantity", "Price" };
        DefaultTableModel shoppingcartproducts = new DefaultTableModel(cartnames, 0);
        JTable carttable = new JTable(shoppingcartproducts);

        //resets the shopping cart table when done
        shoppingcartproducts.setRowCount(0);

        //populate the shopping cart table
        for (Product addedproducts : shoppingCart.getProducts()) {
            Object[] cartRow = new Object[3];
            cartRow[0] = addedproducts.getID() + "   " + getProductDetails(addedproducts) + "   ";
            cartRow[1] = shoppingCart.getProductQuantity(addedproducts);
            cartRow[2] = addedproducts.getPrice() * shoppingCart.getProductQuantity(addedproducts);
            shoppingcartproducts.addRow(cartRow);
        }

        JScrollPane cartTableScrollPanel = new JScrollPane(carttable);

        double totalprice = shoppingCart.TotalPrice();
        double finalprice = shoppingCart.FinalPrice();
    
        JTextArea totalpricearea = new JTextArea("Total Price: " + totalprice);
    
        String textdiscount = "";
        double discountedprice = totalprice - finalprice;
    
        //displays the discount information if needed
        if (discountedprice > 0) {
            textdiscount = "Three Items on Same Category Discount (20%): -" + discountedprice;
        }
    
        JTextArea discountarea = new JTextArea(textdiscount);
        discountarea.setEditable(false);
    
        JTextArea finalpricearea = new JTextArea("Final Price: " + finalprice);
        finalpricearea.setEditable(false);
    
        //create panel for the prices and discount
        JPanel pricepanel = new JPanel();
        pricepanel.setLayout(new BoxLayout(pricepanel, BoxLayout.Y_AXIS));
        pricepanel.add(totalpricearea);
        pricepanel.add(discountarea);
        pricepanel.add(finalpricearea);
    
        //layouts the whole shopping cart gui
        JPanel finalpanel = new JPanel();
        finalpanel.setLayout(new BoxLayout(finalpanel, BoxLayout.Y_AXIS));
        finalpanel.add(cartTableScrollPanel);
        finalpanel.add(pricepanel);
    
        shoppingcartframe.add(finalpanel);
        shoppingcartframe.setVisible(true);
    }

    //gets the rest of the detials depending on if its a electronic or not
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
        mainFrame.setTitle("Westminster Shopping Centre");
        mainFrame.setSize(1000, 1000);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

package com.onlineshopping.onlineshop;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.util.*;

public class GuiMain extends JFrame {
    ArrayList<Product> ProductList;
    JTable tableproduct;
    TableModelGui tablemodel;

    public GuiMain(ArrayList<Product> ProductList) {
        this.ProductList = ProductList;
        tablemodel = new TableModelGui(ProductList);
        tableproduct = new JTable(tablemodel);

        tableproduct.setAutoCreateRowSorter(true);
        


        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 3, 10, 10));

        JLabel dropdownLabel = new JLabel("What Would You Like To Search?");
        dropdownLabel.setPreferredSize(new Dimension(10,30));
        panel.add(dropdownLabel);
        JComboBox dropdownbox = new JComboBox(new String[] { "All", "Electronics", "Clothing" });
        dropdownbox.setPreferredSize(new Dimension(10,30));
        panel.add(dropdownbox);

        panel.add(Box.createRigidArea(new Dimension(0, 0)));

        JButton cartbutton = new JButton("Shopping Cart");
        panel.add(cartbutton);
        panel.setBorder(new EmptyBorder(30, 20, 10, 20));


       JScrollPane TableScrollPanel = new JScrollPane(tableproduct);
       JPanel panel2 = new JPanel();
       panel2.add(TableScrollPanel);

        add(panel, BorderLayout.NORTH);
        add(panel2, BorderLayout.CENTER);

    }

    public void ShowGuiMain() {
        GuiMain mainFrame = new GuiMain(ProductList);
        mainFrame.setTitle("The Product Shop");
        mainFrame.setSize(1000, 1000);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

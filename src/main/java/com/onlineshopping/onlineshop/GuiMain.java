package com.onlineshopping.onlineshop;

import java.awt.*;
import javax.swing.*;


import java.util.*;

public class GuiMain extends JFrame {
    ArrayList<Product> ProductList = new ArrayList<Product>(50);
    JTable tableproduct;

    public GuiMain() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2, 10, 10));

        JLabel dropdownLabel = new JLabel("What Would You Like To Search?");
        dropdownLabel.setPreferredSize(new Dimension(10,30));
        panel.add(dropdownLabel);
        JComboBox dropdownbox = new JComboBox(new String[] { "All", "Electronics", "Clothing" });
        dropdownbox.setPreferredSize(new Dimension(10,30));
        panel.add(dropdownbox);
        

        JPanel panel2 = new JPanel(new BorderLayout());
        JButton cartbutton = new JButton("Shopping Cart");
        panel2.add(cartbutton, BorderLayout.NORTH);
        panel2.add(panel, BorderLayout.CENTER);


       JPanel panel3 = new JPanel(new BorderLayout());
        panel3.add(panel2, BorderLayout.NORTH);
        panel3.add(tableproduct);

        add(panel2, BorderLayout.NORTH);
        add(panel3);

    }

    public void ShowGuiMain() {
        GuiMain mainFrame = new GuiMain();
        mainFrame.setTitle("The Product Shop");
        mainFrame.setSize(600, 600);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

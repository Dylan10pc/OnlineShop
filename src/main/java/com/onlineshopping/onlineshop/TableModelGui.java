package com.onlineshopping.onlineshop;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class TableModelGui extends AbstractTableModel {
    private String[] columnsNames = {"Product ID","Name","Category","Price","Info"};
    private ArrayList<Product> ProductList;

    public TableModelGui(ArrayList<Product> productList) {
        ProductList = productList;
    }

    @Override
    public int  getRowCount() {
        return ProductList.size();
    }

    @Override
    public Object getValueAt(int row, int col ){
        Object temp = null;
        if(col == 0){
            temp = ProductList.get(row).getID();
        }
        else if(col == 1){
            temp = ProductList.get(row).getName();
        }

        else if(col == 2){
                if(ProductList.get(row) instanceof Electronics)
                  temp = "Electronic"; 
                else
                  temp = "Clothing";
        }

        else if(col == 3){
            temp = ProductList.get(row).getPrice();
        }

        else if(col == 4){
            if (ProductList.get(row) instanceof Electronics) {
                temp = ProductList.get(row);
            }
        }
        return temp;


    }

    public String getColumnCount(int col){
        return columnsNames[col];
        }

    public Class getColumnClass(int col){
        if (col ==2) {
            return double.class;
        }
        else{
            return String.class;
        }
    }

    @Override
    public int getColumnCount() {
        return columnsNames.length;
    }    
 
}

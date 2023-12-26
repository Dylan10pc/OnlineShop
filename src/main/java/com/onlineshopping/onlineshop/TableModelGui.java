package com.onlineshopping.onlineshop;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


public class TableModelGui extends AbstractTableModel {
    private String[] columnsNames = {"Product ID","Name","Category","Price","Info"};
    private ArrayList<Product> ProductList;


    public TableModelGui(ArrayList<Product> productlist) {
        ProductList = productlist;
    }

      @Override
    public int getColumnCount() {
        return columnsNames.length;
    }   

    @Override
    public int  getRowCount() {
        return ProductList.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex ){
        Object temp = null;
        if(columnIndex == 0){
            temp = ProductList.get(rowIndex).getID();
        }
        else if(columnIndex == 1){
            temp = ProductList.get(rowIndex).getName();
        }

        else if(columnIndex == 2){
                if(ProductList.get(rowIndex) instanceof Electronics)
                  temp = "Electronic"; 
                else
                  temp = "Clothing";
        }

        else if(columnIndex == 3){
            temp = ProductList.get(rowIndex).getPrice();
        }

        else if(columnIndex == 4){
            if (ProductList.get(rowIndex) instanceof Electronics) {
                Electronics electricinfo = (Electronics) ProductList.get(rowIndex);
                temp = electricinfo.getBrand()+ "   " + electricinfo.getWarranty();
            } else if (ProductList.get(rowIndex) instanceof Clothing) {
                Clothing clothinginfo = (Clothing) ProductList.get(rowIndex);
                temp = clothinginfo.getColour() + "   " + clothinginfo.getSize();
            }       
        }
        return temp;
    }

    @Override
    public String getColumnName(int col){
        return columnsNames[col];
    }


  public Class getColumnClass(int col){
        if (col == 4) {
            return double.class;
        }
        else{
            return String.class;
        }
    } 

    public void setProductList(ArrayList<Product> productList) {
        this.ProductList = productList;
        fireTableDataChanged();
    }

    public boolean producthighlightrow(int rowIndex) {
        return ProductList.get(rowIndex).getNumberofavailableitems() <= 3;
    }

    
}

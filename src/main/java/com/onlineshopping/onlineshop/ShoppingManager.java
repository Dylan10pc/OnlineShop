package com.onlineshopping.onlineshop;

import java.util.ArrayList;

public interface ShoppingManager {
    void AddProduct(ArrayList<Product> ProductList, String Type);
    void DeleteProduct(ArrayList<Product> ProductList);
    void PrintList();
    void SaveList();
    
}

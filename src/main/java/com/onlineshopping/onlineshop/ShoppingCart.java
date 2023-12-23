package com.onlineshopping.onlineshop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {
    private List<Product> productList;
    private Map<Product, Integer> productQuantities;
    
    public ShoppingCart(){
        productList = new ArrayList<>();
        productQuantities = new HashMap<>();
    }

    public void AddProduct(Product product) {
        int quantity = productQuantities.getOrDefault(product, 0);
        productQuantities.put(product, quantity + 1);

        if (!productList.contains(product)) {
        productList.add(product);
    }
    }

    public void RemoveProducts(int listOfProducts){
        listOfProducts = listOfProducts - 1;
    }

    public double CalculateTotalPrice(){
        double totalPrice = 0;
        for (Product product : productList) {
            int quantity = getProductQuantity(product);
            totalPrice += product.getPrice() * quantity;
        }
        return totalPrice;
    }


    public int getProductQuantity(Product product) {
        return productQuantities.getOrDefault(product, 0);
    }

    
    public List<Product> getProducts() {
        return productList;
    }

}

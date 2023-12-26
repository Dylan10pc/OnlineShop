package com.onlineshopping.onlineshop;

import java.util.*;

public class ShoppingCart {
    private List<Product> productList;
    private Map<Product, Integer> productquantities;
    
    public ShoppingCart(){
        productList = new ArrayList<>();
        productquantities = new HashMap<>();
    }

    //Adds a product to the cart
    public void AddProduct(Product product) {
        //if product is already in the cart this adds it to the same product and adds quantity
        int quantity = productquantities.getOrDefault(product, 0);
        productquantities.put(product, quantity + 1);

        // If the product is not already in the list it adds to it
        if (!productList.contains(product)) {
        productList.add(product);
    }
    }

    //Return the quantity of a specific product from the cart
    public int getProductQuantity(Product product) {
        return productquantities.getOrDefault(product, 0);
    }

    public List<Product> getProducts() {
        return productList;
    }

    //calculates the total price of everything in the cart
    public double TotalPrice(){
        double totalprice = 0;
        for (Product product : productList) {
            int quantity = getProductQuantity(product);
            totalprice += product.getPrice() * quantity;
        }
        return totalprice;
    }

    //calculate the final price in case of a discount
    public double FinalPrice() {
        double finalprice = TotalPrice();
    
        //Applies discount if there is three or more of the same item in the cart
        for (Map.Entry<Product, Integer> entry : productquantities.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
    
            if (quantity >= 3) {
                double discount = 0.2;
                finalprice -= product.getPrice() * quantity * discount;
            }
        }
        return finalprice;
    }

}

package com.onlineshopping.onlineshop;

public class ShoppingCart {
    private int ListOfProducts;
    
    public ShoppingCart(int listOfProducts){
        this.ListOfProducts = listOfProducts;
    }

    public ShoppingCart(){
        ListOfProducts = 0;
    }

    public void AddProduct(int listOfProducts){
        listOfProducts = listOfProducts + 1;
    }

    public void RemoveProducts(int listOfProducts){
        listOfProducts = listOfProducts - 1;
    }

    public static void CalculateTotalPrice(double Price){
        double totalPrice = 0;
        totalPrice = totalPrice + Price;
    }

}

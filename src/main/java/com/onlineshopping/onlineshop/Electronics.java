/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.onlineshopping.onlineshop;

/**
 *
 * @author Dylan
 */
public class Electronics extends Product {
    private String Brand;
    private int Warranty;

    //initialise instance variables that belong to the subclass and to the superclass
    public Electronics(String id, String name, int items, double price, String brand, int warranty){
        super(id, name, items, price);
        Brand = brand;
        Warranty = warranty;

    }

    //set the objects
    public void setBrand(String brand) {
        Brand = brand;
    }

    public void setWarranty(int warranty) {
        Warranty = warranty;
    }

    //return the objects
    public String getBrand() {
        return Brand;
    }

    public int getWarranty() {
        return Warranty;

    }

    //Overrides so that the arraylist is readable
    @Override
    public String toString() {
        return "Id: " + ID + " Name: " + Name + " Items: " + Numberofavailableitems + " Price: " + Price + " Warranty: " + Warranty + " Brand: " + Brand;
    }

    //Ovverides to be able to sort the arraylist based on Ids
    @Override
    public int compareTo(Product ProList) {
        return ID.compareTo(ProList.ID);
    }
}

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

    public Electronics(String id, String name, int items, double price, String brand, int warranty){
        super(id, name, items, price);
        Brand = brand;
        Warranty = warranty;

    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public void setWarranty(int warranty) {
        Warranty = warranty;
    }

    public String getBrand() {
        return Brand;
    }

    public int getWarranty() {
        return Warranty;

    }

    @Override
    public String toString() {
        return "Id: " + ID + " Name: " + Name + " Items: " + Numberofavailableitems + " Price: " + Price + " Warranty: " + Warranty + " Brand: " + Brand;
    }

    @Override
    public int compareTo(Product ProList) {
        return ID.compareTo(ProList.ID);
    }
}

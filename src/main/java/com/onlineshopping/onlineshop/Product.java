/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.onlineshopping.onlineshop;

/**
 *
 * @author Dylan
 */

public abstract class Product implements Comparable<Product> {
    //Made Protected so that is accessed in same class, subclass or same package
    protected String ID;
    protected String Name;
    protected int Numberofavailableitems;
    protected double Price;
    


    public Product(String id, String name, int items, double price){
        this.ID = id;
        this.Name = name;
        this.Numberofavailableitems = items;
        this.Price = price;
    }

    //set The objects
    public void setID(String id){
        ID = id;
    }

    public void setName(String name){
        Name = name;        
    }

    public void setNumberofavailableitems(int items){
        Numberofavailableitems = items;
    }

    public void setPrice(double price){
        Price = price;
    }

    //Returns the objects 
    public String getID(){
        return ID;
    }

    public String getName(){
        return Name;
    }

    public int getNumberofavailableitems(){
        return Numberofavailableitems;
    }

    public double getPrice(){
        return Price;
    }

}

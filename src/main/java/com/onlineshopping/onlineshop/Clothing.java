/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.onlineshopping.onlineshop;

/**
 *
 * @author Dylan
 */
public class Clothing extends Product{
    private String Colour;
    private String Size;
    
    //initialise instance variables that belong to the subclass and to the superclass
    public Clothing(String id, String name, int items, double price, String colour, String size){
        super(id, name, items, price);
        Colour = colour;
        Size = size;
    }

    //setters and getters
    public void setColour(String colour){
        Colour = colour;
    }  
    
    public void setSize(String size){
        Size = size;
    }

    public String getColour(){
        return Colour;
    }

    public String getSize(){
        return Size;
    }

    //Overrides so that the arraylist is readable
    @Override
    public String toString(){
        return "Id: " + ID + " Name: " + Name + " Items: " + Numberofavailableitems + " Price: " + Price + " Colour: " + Colour + " Size: " + Size;
    }

    //Ovverides to be able to sort the arraylist based on Ids
    @Override
    public int compareTo(Product ProList) {
        return ID.compareTo(ProList.ID);
    }
}

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
    

    public Clothing(String id, String name, int items, double price, String colour, String size){
        super(id, name, items, price);
        Colour = colour;
        Size = size;
    }

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

    @Override
    public String toString(){
        return "Id: " + ID + " Name: " + Name + " Items: " + Numberofavailableitems + " Price: " + Price + " Colour: " + Colour + " Size: " + Size;
    }

    @Override
    public int compareTo(Product ProList) {
        return ID.compareTo(ProList.ID);
    }
}

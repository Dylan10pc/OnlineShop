package com.onlineshopping.onlineshop;

import java.util.*;
import java.io.*;

public class WestminsterShoppingManager implements ShoppingManager {
    ArrayList<Product> ProductList = new ArrayList<Product>(50);
    private String Type;

    public WestminsterShoppingManager() {
        this.ProductList = ProductList;
        this.Type = Type;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        Type = Type;
    }

    public void ManagerMenu(String id) {

        boolean Done = true;
        while (Done) {
            System.out.println("Please Select An Option:");
            System.out.println("1) Add a Product");
            System.out.println("2) Delete a product");
            System.out.println("3) Print the list of products");
            System.out.println("4) Save the list of products");
            System.out.println("5) Show The Shop");

            System.out.println("Enter An Option");

            Scanner input = new Scanner(System.in);
            int option = input.nextInt();
            switch (option) {
                case 1:
                    AddProduct(ProductList, Type);
                    break;

                case 2:
                    DeleteProduct(ProductList);
                    break;

                case 3:
                    PrintList();
                    break;

                case 4:
                    SaveList();
                    break;

                case 5:
                    GuiMain Gui = new GuiMain();
                    Gui.ShowGuiMain();
                    break;

            }
        }
    }

    public void AddProduct(ArrayList<Product> ProductList, String Type) {
        Scanner input = new Scanner(System.in);

        System.out.println("Is the product a 'electronic' or 'clothing' product?");
        Type = input.next();
        if (Type.equals("electronic")) {
            if (ProductList.size() < 50) {
                System.out.println("What is the Brand Name");
                String brand = input.next();

                System.out.println("What is the warranty");
                int warranty = input.nextInt();

                System.out.println("What is the Id of the Product");
                String id = input.next();

                System.out.println("What is the name of the Product");
                String name = input.next();

                System.out.println("How many items does the Product have");
                int items = input.nextInt();

                System.out.println("How much will the Product be");
                double price = input.nextDouble();

                ProductList.add(new Electronics(id, name, items, price, brand, warranty));
            } else {
                System.out.println("Cannot Add More Products, Please Delete A Product Before Trying Again");
            }

        } else if (Type.equals("clothing")) {
            if (ProductList.size() < 50) {
                System.out.println("What is the colour of the clothing");
                String colour = input.next();

                System.out.println("What is the size of the Clothing");
                String size = input.next();

                System.out.println("What is the Id of the Product");
                String id = input.next();

                System.out.println("What is the name of the Product");
                String name = input.next();

                System.out.println("How many items does the Product have");
                int items = input.nextInt();

                System.out.println("How much will the Product be");
                double price = input.nextDouble();

                ProductList.add(new Clothing(id, name, items, price, colour, size));
            } else {
                System.out.println("Cannot Add More Products, Please Delete A Product Before Trying Again");
            }

        }
    }

    public void DeleteProduct(ArrayList<Product> ProductList) {

        Scanner input = new Scanner(System.in);
        System.out.println("What Product Would You like to Delete Please Give ID");
        String iddelete = input.next();

        int idofproduct = 0;
        for (int i = 0; i < ProductList.size(); i++) {
            if (iddelete.equals(ProductList.get(i).getID())) {
                idofproduct = i;
                ProductList.remove(idofproduct);
            }
        }
    }

    public void PrintList() {
        Collections.sort(ProductList);
        for (Product printlist : ProductList) {
            System.out.println((printlist instanceof Electronics ? "Electronics" : "Clothing") + " " + printlist + " ");
        }
    }

    // THIS STILL NEEDS TO BE COMPLETED TOO
    public void SaveList() {
        Scanner input = new Scanner(System.in);
        System.out.println("Would You like to save or load the list");
        String whichtype = input.next();

        if (whichtype.equals("save")) {
            try {

                FileWriter file1 = new FileWriter("savedProduct.txt");
                BufferedWriter write = new BufferedWriter(file1);

                write.newLine();

                for (Product printlist : ProductList) {
                    write.write(printlist.toString());
                    write.newLine();
                }
                write.close();
            } catch (IOException e) {
                System.out.println("Error Saving");
                e.printStackTrace();
            }

        } else if (whichtype.equals("load")) {
            try {
                FileReader file = new FileReader("savedProduct.txt");
                BufferedReader reader = new BufferedReader(file);
                

            } catch (IOException e) {
                System.out.println("Error Reading file");
                e.printStackTrace();
            }

        }

    }

}

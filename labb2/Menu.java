package se.iths.labborationer.labb2;

import java.util.Scanner;

public class Menu {
        InventoryBalance balance;
        Scanner scanner;

    public Menu(InventoryBalance balance, Scanner scanner) {
        this.balance = balance;
        this.scanner = scanner;
    }

    public void start(){
        String input = scanner.nextLine();
        boolean loop = true;

        this.balance.add(new Product(new ProductCategory("Dairy"), "Milk", 5, 10938));
        this.balance.add(new Product(new ProductCategory("D"), "Mil", 5, 10938));
        this.balance.add(new Product(new ProductCategory("Vegetable"), "Carrot", 5, 10938));
        this.balance.add(new Product(new ProductCategory("Dairy"), "Milk", 5, 10938));

        System.out.println("Menu");


            System.out.println();

            printAddProductToCategory();

            while(loop){
                switch(input){
                case "1" -> createNewCategory();
                case "2" -> printAddProductToCategory();
                case "3" -> printInventoryBalance();
                case "4" -> searchByCategory();
                case "5" -> searchBetweenPrices();
                case "e" -> loop = false;
                }
            }

    }

    public void greeting(){
        System.out.println("Hej och välkommen till Kortedala mataffär");
        System.out.println("Skapa ny kategori");
        System.out.println("lägg till vara");
        System.out.println("Läg");
        System.out.println("printa ut lagersaldo");
        System.out.println("sök via kategori");
        System.out.println("sök inom ett prisintervall");
        System.out.println("Sök efter varor");
        System.out.println("Avsluta");
    }

    public void printAddProductToCategory(){
            System.out.println("Vänligen välj kategorin varan tillhör.");

        for (int i = 0; i < balance.getAllCategories().size(); i++) {
            System.out.println((i+1) + " " + balance.getAllCategories().get(i));

        }

    }
}


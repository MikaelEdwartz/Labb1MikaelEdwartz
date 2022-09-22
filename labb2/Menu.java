package se.iths.labborationer.labb2;

import java.util.Scanner;

public class Menu {
        InventoryBalance balance;
        Scanner scanner;

    public Menu(InventoryBalance balance, Scanner scanner) {
        this.balance = balance;
        this.scanner = scanner;


        startupkategorier();
    }

    public void start(){
        boolean loop = true;


        System.out.println("Meny");

            greeting();



            while(loop){
                    String input = scanner.nextLine();

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
        System.out.println("1: Skapa ny kategori");
        System.out.println("2: Lägg till vara");
        System.out.println("3: Printa ut lagersaldo");
        System.out.println("4: Sök via kategori");
        System.out.println("5: Sök inom ett prisintervall");
        System.out.println("e: Avsluta");

    }

    public void printAddProductToCategory(){
            System.out.println("Vänligen välj kategorin varan tillhör.");

        for (int i = 0; i < balance.getAllCategories().size(); i++) {
            System.out.println((i+1) + " " + balance.getAllCategories().get(i));

        }

    }

    public void createNewCategory() {

    }

    public void searchByCategory() {

    }


    public void printInventoryBalance() {
    }

    public void searchBetweenPrices() {

    }


    public void startupkategorier() {
        this.balance.add(new Product(new ProductCategory("Dairy"), "Milk", 5, 10938));
        this.balance.add(new Product(new ProductCategory("Dairy"), "Milk", 9, 10938));
        this.balance.add(new Product(new ProductCategory("Meat"), "Chicken", 25, 10938));
        this.balance.add(new Product(new ProductCategory("Meat"), "Beef", 51, 10938));
        this.balance.add(new Product(new ProductCategory("Fruit"), "Apples", 54, 10938));
        this.balance.add(new Product(new ProductCategory("Vegetable"), "Carrot", 25, 10938));
        this.balance.add(new Product(new ProductCategory("Fruit"), "Banana", 15, 10938));
    }
}


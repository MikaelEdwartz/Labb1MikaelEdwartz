package se.iths.labborationer.labb2;

//TODO

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

import static java.math.BigDecimal.*;

public class Menu {
    InventoryBalance balance;
    ArrayList<ProductCategory> categories;
    Scanner scanner;

    public Menu(InventoryBalance balance,ArrayList<ProductCategory> categories, Scanner scanner) {
        this.balance = balance;
        this.categories = categories;
        this.scanner = scanner;



        startupkategorier();
    }

    public void start() {
        boolean loop = true;



        while (loop) {
            greeting();
            var input = scanner.nextLine();

            switch (input) {
                case "1" -> addProductToInventoryBalance(userCategoryChoice()); //skapar kategori
                case "2" -> System.out.println("");//printAddProductToCategory();
                case "3" -> printInventoryBalance();
                case "4" -> searchByCategory();
                case "5" -> searchBetweenPrices();
                case "e" -> loop = false;
            }
        }

    }
    

    
    public void greeting() {

        System.out.println("Hej och välkommen till Kortedala mataffär");
        System.out.println("1: Lägg till vara/skapa kategori");
        System.out.println("2: ta bort varor");
        System.out.println("3: Printa ut lagersaldo");
        System.out.println("4: Sök via kategori");
        System.out.println("5: Sök inom ett prisintervall");
        System.out.println("e: Avsluta");

    }

    public void addProductToInventoryBalance(ProductCategory category) {

        balance.add(new Product(category, getProductName(), getProductPrice(), getProductSerialCode()));

        
    }

    private int getProductSerialCode() {
        System.out.println("Vänligen skriv in streckkod på varan.");
        return scanner.nextInt();
    }

    private BigDecimal getProductPrice() {
        System.out.println("Vänligen skriv in pris på varan.");
        return scanner.nextBigDecimal();
    }

    private String getProductName() {
        System.out.println("Vänligen skriv in namn på varan");
        return scanner.nextLine();
    }

    public void createNewCategory(Scanner scanner) {
        System.out.println("Vänligen skriv in namn på kategori");
        categories.add(new ProductCategory(scanner.nextLine()));

    }

    public ProductCategory userCategoryChoice(){
        printCategories();
        
        while(true) {
            String userChoice = scanner.nextLine();
            
            if (Integer.parseInt(userChoice) == 1)
                createNewCategory(scanner);
            else 
                return categories.get(Integer.parseInt(userChoice)-2);
            
            printCategories();
        }
        
    }

    private void printCategories() {
        System.out.println("1. Lägg till ny kategori eller välj kategori.");
        if(categories.size() >= 1) {
            for (int i = 0; i < categories.size(); i++) {
                System.out.println((i + 2) + ". " + categories.get(i) + ".");
            }
        }
    }


    public void searchByCategory() {

    }


    public void printInventoryBalance() {
    }

    public void searchBetweenPrices() {

    }


    public void startupkategorier() {
        this.balance.add(new Product(new ProductCategory("Dairy"), "Milk", valueOf(199), 10938));
        this.balance.add(new Product(new ProductCategory("Dairy"), "Milk", valueOf(11), 10938));
        this.balance.add(new Product(new ProductCategory("Meat"), "Chicken", valueOf(12), 10938));
        this.balance.add(new Product(new ProductCategory("Meat"), "Beef", valueOf(1), 10938));
        this.balance.add(new Product(new ProductCategory("Fruit"), "Apples", valueOf(11), 10938));
        this.balance.add(new Product(new ProductCategory("Vegetable"), "Carrot", valueOf(17), 10938));
        this.balance.add(new Product(new ProductCategory("Fruit"), "Banana", valueOf(14), 10938));
    }
}


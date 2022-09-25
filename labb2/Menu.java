package se.iths.labborationer.labb2;

//TODO

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

import static java.math.BigDecimal.*;

public class Menu {
    private InventoryBalance balance;
    private ArrayList<ProductCategory> categories;
    private Scanner scanner;

    public Menu(InventoryBalance balance, ArrayList<ProductCategory> categories, Scanner scanner) {
        this.balance = balance;
        this.categories = categories;
        this.scanner = scanner;


        startupkategorier();
    }

    public void start() {
        boolean loop = true;
        greeting();
        startupkategorier();


        while (loop) {
            var input = scanner.nextLine();

            switch (input) {
                case "1" -> addProductToInventoryBalance(userCategoryChoice()); //skapar kategori
                case "2" -> removeProduct();//printAddProductToCategory();
                case "3" -> this.balance.printBalance();
                case "4" -> searchByCategory();
                case "5" -> searchBetweenPrices();
                case "e" -> loop = false;
            }
            greeting();
        }

    }


    public void greeting() {
        System.out.println("-----------------------------------------");
        System.out.println("Hej och välkommen till Kortedala mataffär");
        System.out.println("1: Lägg till vara/skapa kategori");
        System.out.println("2: ta bort varor");
        System.out.println("3: Printa ut lagersaldo");
        System.out.println("4: Sök via kategori");
        System.out.println("5: Sök inom ett prisintervall");
        System.out.println("e: Avsluta");

    }

    public void addProductToInventoryBalance(ProductCategory category) {
        this.balance.add(new Product(category, getProductName(), getProductPrice(), getProductSerialCode()));
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
        System.out.println("Vänligen skriv in namn på ny kategori.");
        this.categories.add(new ProductCategory(scanner.nextLine()));

    }

    public ProductCategory userCategoryChoice() {
        addFirstCategory();
        listCategoriesToAdd();

        while (true) {

            int userChoice = Integer.parseInt(scanner.nextLine());

            if (userChoice == 1)
                createNewCategory(scanner);
            else
                return this.categories.get(userChoice - 2);
            listCategoriesToAdd();
        }

    }

    private void addFirstCategory() {
        if (this.categories.isEmpty()) {
            System.out.println("Det verkar inte finnas någon kategori för matvaror än.");
            createNewCategory(scanner);
        }
    }

    private ProductCategory getCategoryAtIndex(int i) {
        return this.categories.get(i);
    }

    private void listCategoriesToAdd() {

        System.out.println("1. Lägg till ytterligare kategori");

        if (this.categories.size() >= 1) {
            for (int i = 0; i < this.categories.size(); i++) {
                System.out.println((i + 2) + ". " + getCategoryAtIndex(i) + ".");
            }
        }
    }

    /*¨
    * Radering ska vi ha varor med olika streckkoder
    * Ska vi kunna ha olika priser på varorna?
    * Ska vi radera utifrån namn, eller andra kriterier
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    * */

    private void removeProduct() {
        int productToRemove = 1;
        int productToRemove1;
        var chosenProduct = listCategoriesttoRemove();
        var tempList = new ArrayList<Product>();
        for (int i = 0; i < this.balance.size(); i++) {
            if (this.balance.getCategory(i).equals(chosenProduct)) {
                tempList.add(this.balance.getProduct(i));
            }
        }
        for (int i = 0; i < tempList.size(); i++) {
            System.out.println(productToRemove + this.balance.getProduct(i).product() + " med streckod: " + this.balance.getProduct(i).productNumber());
        }

    }

    private ProductCategory listCategoriesttoRemove() {
        System.out.println("Välj vilken kategori du vill radera en vara från.");

        for (int i = 0; i < this.categories.size(); i++) {
            System.out.println((i + 1) + " " + getCategoryAtIndex(i));

        }

        return this.categories.get(scanner.nextInt() - 1);
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


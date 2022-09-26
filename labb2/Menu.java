package se.iths.labborationer.labb2;

//TODO

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

import static java.math.BigDecimal.*;

public class Menu {
    private InventoryBalance balance;
    private ArrayList<ProductCategory> categories;
    private Scanner scanner;

    public Menu(InventoryBalance balance, ArrayList<ProductCategory> categories, Scanner scanner) {
        this.balance = balance;
        this.categories = categories;
        this.scanner = scanner;



    }

    public void start() {
        boolean loop = true;
        greeting();


        while (loop) {
            var input = scanner.nextLine();

            switch (input) {
                case "1" -> addProductToInventoryBalance(); //skapar kategoriuserCategoryChoice()
                case "2" -> removeProduct();//printAddProductToCategory();
                case "3" -> printInventoryBalance();
                case "4" -> searchByCategory();
                case "5" -> searchBetweenPrices();
                case "e" -> loop = false;
            }
            greeting();
        }

    }
//Första menyn "Admin eller kund"


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

    public void addProductToInventoryBalance() {
        startupkategorier();
        //ProductCategory category
        //this.balance.add(new Product(category, getProductName(), getProductPrice(), getProductSerialCode()));
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

    public ProductCategory addProductsAndCategories() {
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

    private void removeProduct() {
        System.out.println("Välj vilken kategori du vill radera en vara från.");
        var category = userCategoryChoice();
        var tempList = new ArrayList<Integer>();
        int count = 1;
        for (int i = 0; i < this.balance.size(); i++) {
            if (categoryMatch(category, i)) {
                System.out.println(count + " " + this.balance.printBalance(i));
                tempList.add(i);
                count++;
            }
        }
        System.out.println("Välj vilken vara du vill ta bort från lagret");

        int productToRemove = scanner.nextInt();
        this.balance.remove(tempList.get(productToRemove-1));

    }

    private boolean categoryMatch(ProductCategory category, int i) {
        return this.balance.getCategory(i).equals(category);
    }

    public void searchByCategory() {
        var category = userCategoryChoice();
        for (int i = 0; i < this.balance.size(); i++) {
            if(categoryMatch(category, i))
                System.out.println(this.balance.printBalance(i));
        }
    }

    private ProductCategory userCategoryChoice() {

        for (int i = 0; i < this.categories.size(); i++) {
            System.out.println((i + 1) + " " + getCategoryAtIndex(i));
        }

        return this.categories.get(scanner.nextInt() - 1);
    }



    public void printInventoryBalance() {
        for (int i = 0; i < this.balance.size(); i++) {
            System.out.println(this.balance.printBalance(i));
        }

    }

    public void searchBetweenPrices() {
        System.out.println("Vad är det lägsta priset");
        var lowestPrice = scanner.nextBigDecimal();
        System.out.println("Vad är det högsta priset");
        var highestPrice = scanner.nextBigDecimal();

        for (int i = 0; i < this.balance.size(); i++) {
            if(this.balance.getProduct(i).price().compareTo(lowestPrice) >= 0 && (this.balance.getProduct(i).price().compareTo(highestPrice) <= 0)) {
                System.out.println(this.balance.printBalance(i));
            }

        }
    }


    public void startupkategorier() {
        for (int i = 0; i < this.balance.size(); i++) {
            this.categories.add(this.balance.getCategory(i));
        }
        this.balance.add(new Product(new ProductCategory("Dairy"), "Milk", valueOf(199), 10938));
        this.balance.add(new Product(new ProductCategory("Dairy"), "Cream", valueOf(11), 1098));
        this.balance.add(new Product(new ProductCategory("Meat"), "Chicken", valueOf(12), 10938));
        this.balance.add(new Product(new ProductCategory("Meat"), "Beef", valueOf(1), 10938));
        this.balance.add(new Product(new ProductCategory("Fruit"), "Apples", valueOf(11), 10938));
        this.balance.add(new Product(new ProductCategory("Vegetable"), "Carrot", valueOf(17), 10938));
        this.balance.add(new Product(new ProductCategory("Fruit"), "Banana", valueOf(14), 10938));


        }
    }




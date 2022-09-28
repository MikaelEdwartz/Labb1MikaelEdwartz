package se.iths.labborationer.labb2;

//TODO

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

import static java.math.BigDecimal.*;
import static java.util.stream.Collectors.toList;

public class Menu {
    private ArrayList<Product> balance;
    private ArrayList<ProductCategory> categories;
    private Scanner scanner;

    public Menu(ArrayList<Product> balance, ArrayList<ProductCategory> categories, Scanner scanner) {
        this.balance = balance;
        this.categories = categories;
        this.scanner = scanner;
    }

    public void start() {
        boolean loop = true;
        startUpGreeting();
        startUpMenu(loop);

    }

    private void startUpMenu(boolean loop) {
        while(loop){
            var input = scanner.next();
            switch(input){
                case "1" -> adminMenu(loop);
                case "2" -> customerMenu(loop);
                case "e" -> loop = false;
            }
        }
    }

    private void customerMenu(boolean loop) {

        while(loop){
            var input = scanner.next();

            switch(input){
                case "1" -> loopThroughCategories();
                case "2" -> listProductCategory();
            }
        }
    }

    private void listProductCategory() {

        var category = getUserCategoryChoice(1);
        

    }

    private void loopThroughCategories() {
    }

    private void adminMenu(boolean loop) {
        while (loop) {
            var input = scanner.nextLine();

            switch (input) {
                case "1" -> startupkategorier();//addProductToInventoryBalance(addProductsAndCategories());
                case "2" -> removeProduct();
                case "3" -> printInventoryBalance();
                case "4" -> searchByCategory();
                case "5" -> searchBetweenPrices();
                case "e" -> loop = false;
            }
            adminMenuGreeting();
        }
    }

    private void startUpGreeting() {
        System.out.println("Tryck 1 för admin meny");
        System.out.println("Tryck 2 för kund meny");
        System.out.println("Tryck e för att avsluta.");
    }

    private void costumerMenuGreeting(){
        System.out.println("1. Vill gå igenom alla kategorier en i taget?");
        System.out.println("2. Välj en specifik kategori");
        System.out.println("e. Gå till kassan och betala");
    }

    private void adminMenuGreeting() {
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

    public void createNewCategory(Scanner scanner) {
        System.out.println("Vänligen skriv in namn på ny kategori.");
        this.categories.add(new ProductCategory(scanner.next()));

    }

    public ProductCategory addProductsAndCategories() {
        addFirstCategory();
        listCategoriesToAdd();

        while (true) {

            int userChoice = Integer.parseInt(scanner.next());

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

        printCategoriesInOrder(2);
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
        return scanner.next();
    }

    private void removeProduct() {
        System.out.println("Välj vilken kategori du vill radera en vara från.");
        var category = getUserCategoryChoice(1);

        System.out.println("Välj vilken vara du vill ta bort från lagret");
        var tempList = new ArrayList<Integer>();
        int count = 1;
        for (int i = 0; i < this.balance.size(); i++) {
            if (categoryMatch(category, i)) {
                System.out.println(count + " " + printBalance(i));
                tempList.add(i);
                count++;
            }
        }

        int productToRemove = scanner.nextInt();
        int indexToRemove = tempList.get(productToRemove-1);
        this.balance.remove(indexToRemove);
    }



    private ArrayList<Product> addCategoryProductsToList() {
        var category = getUserCategoryChoice(1);
        var tempList = new ArrayList<Product>();
        int count = 1;
        for (int i = 0; i < this.balance.size(); i++) {
            if (categoryMatch(category, i)) {
                System.out.println(count + " " + printBalance(i));
                tempList.add(this.balance.get(i));
                count++;
            }
        }
        return tempList;
    }


    private boolean categoryMatch(ProductCategory category, int i) {
        return this.balance.get(i).category().equals(category);
    }

    public void searchByCategory() {
        var category = getUserCategoryChoice(2);
        for (int i = 0; i < this.balance.size(); i++) {
            if(categoryMatch(category, i))
                System.out.println(printBalance(i));
        }

        this.categories.stream().filter(category -> category != )

    }

    private ProductCategory getUserCategoryChoice(int number) {
        
        printCategoriesInOrder(number);
        return this.categories.get(scanner.nextInt() - 1);
    }
    
    private void printCategoriesInOrder(int number) {
        if (this.categories.size() >= 1) {
            for (int i = 0; i < this.categories.size(); i++) {
                System.out.println((i + number) + ". " + getCategoryAtIndex(i) + ".");
            }
        }
    }




    public void printInventoryBalance() {
        this.balance.forEach(System.out::println);
    }

    public void searchBetweenPrices() {
        BigDecimal lowestPrice = getLowestSearchPrice();
        BigDecimal highestPrice = getHighestSearchPrice();

        this.balance.stream().filter(p-> p.price()
                .compareTo(highestPrice) <= 0)
                .filter(p -> p.price().compareTo(lowestPrice) >= 0)
                .forEach(System.out::println);
        }

    private BigDecimal getHighestSearchPrice() {
        System.out.println("Vad är det högsta priset");
        var highestPrice = scanner.nextBigDecimal();
        return highestPrice;
    }

    private BigDecimal getLowestSearchPrice() {
        System.out.println("Vad är det lägsta priset");
        var lowestPrice = scanner.nextBigDecimal();
        return lowestPrice;
    }


    public String printBalance(int i){
        return this.balance.get(i).category() + ", " + this.balance.get(i).product() + ", "  + this.balance.get(i).price() +   ", " + this.balance.get(i).productNumber();
    }

    public void startupkategorier() {



        this.balance.add(new Product(new ProductCategory("Dairy"), "Milk", valueOf(199), 10938));
        this.balance.add(new Product(new ProductCategory("Dairy"), "Cream", valueOf(11), 1098));
        this.balance.add(new Product(new ProductCategory("Meat"), "Chicken", valueOf(12), 10938));
        this.balance.add(new Product(new ProductCategory("Meat"), "Beef", valueOf(1), 10938));
        this.balance.add(new Product(new ProductCategory("Fruit"), "Apples", valueOf(11), 10938));
        this.balance.add(new Product(new ProductCategory("Vegetable"), "Carrot", valueOf(17), 10938));
        this.balance.add(new Product(new ProductCategory("Fruit"), "Banana", valueOf(14), 10938));

        for (int i = 0; i < this.balance.size(); i++) {
            if(!(this.categories.contains(this.balance.get(i).category())))
                this.categories.add(this.balance.get(i).category());

        }


        }
    }




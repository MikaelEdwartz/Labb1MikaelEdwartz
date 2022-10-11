package se.iths.labborationer.labb2;

import com.google.gson.Gson;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.math.BigDecimal.valueOf;

public class AdminInterface {

    private final InventoryBalance inventory;
    private final List<ProductCategory> categoryList;
    private final Scanner scanner;
    private final Menu menu;
    private final JsonReader reader;

    public AdminInterface(InventoryBalance inventory, List<ProductCategory> categories, Scanner scanner, Menu menu, JsonReader reader){
        this.inventory = inventory;
        this.scanner = scanner;
        this.categoryList = categories;
        this.menu = menu;
        this.reader = reader;
    }

    public void start() {

        boolean loop = true;
       while (loop) {
            var input = scanner.nextLine();
            switch (input) {
                case "1" -> addProductsToInventory(addProductsAndCategories());
                case "2" -> removeProduct();
                case "3" -> printProducts();
                case "4" -> printCategory(getUserCategoryChoice(1));
                case "5" -> searchBetweenPrices();
                case "6" -> menu.start();
                case "e" -> loop = false;
            }
            adminMenuGreeting();
        }
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

    public void startupkategorier() {
        this.inventory.add(new Product(new ProductCategory("Dairy"), "Milk", valueOf(199), 10938));
        this.inventory.add(new Product(new ProductCategory("Dairy"), "Milk", valueOf(199), 10938));
        this.inventory.add(new Product(new ProductCategory("Dairy"), "Milk", valueOf(199), 10938));
        this.inventory.add(new Product(new ProductCategory("Dairy"), "Milk", valueOf(199), 10938));
        this.inventory.add(new Product(new ProductCategory("Dairy"), "Milk", valueOf(199), 10938));
        this.inventory.add(new Product(new ProductCategory("Dairy"), "Cream", valueOf(11), 1098));
        this.inventory.add(new Product(new ProductCategory("Meat"), "Chicken", valueOf(12), 109));
        this.inventory.add(new Product(new ProductCategory("Meat"), "Chicken", valueOf(12), 109));
        this.inventory.add(new Product(new ProductCategory("Meat"), "Chicken", valueOf(12), 109));
        this.inventory.add(new Product(new ProductCategory("Meat"), "Beef", valueOf(1), 10));
        this.inventory.add(new Product(new ProductCategory("Fruit"), "Apples", valueOf(11), 1038));
        this.inventory.add(new Product(new ProductCategory("Fruit"), "Apples", valueOf(11), 1038));
        this.inventory.add(new Product(new ProductCategory("Fruit"), "Apples", valueOf(11), 1038));
        this.inventory.add(new Product(new ProductCategory("Vegetable"), "Carrot", valueOf(17), 938));
        this.inventory.add(new Product(new ProductCategory("Vegetable"), "Carrot", valueOf(17), 938));
        this.inventory.add(new Product(new ProductCategory("Fruit"), "Banana", valueOf(14), 18));
        this.inventory.add(new Product(new ProductCategory("Fruit"), "Banana", valueOf(14), 18));

        for (int i = 0; i < this.inventory.size(); i++) {
            if (!(this.categoryList.contains(this.inventory.getCategory(i))))
                this.categoryList.add(this.inventory.getCategory(i));
        }
    }

    public void addProductsToInventory(ProductCategory category) {
        this.inventory.add(new Product(category, getProductName(), getProductPrice(), getProductSerialCode()));
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
    //_________________________________________________________________________________________________
    public ProductCategory addProductsAndCategories() {
        addFirstCategory();
        listCategoriesToAdd();

        while (true) {
            String userChoice = scanner.next();
            if (Integer.parseInt(userChoice) == 1)
                createNewCategory(scanner);
            else if (userChoice.equals("e"))
                start();
            else
                return chosenCategory(Integer.parseInt(userChoice) - 2);

            listCategoriesToAdd();
        }
    }

    private ProductCategory chosenCategory(int userChoice) {
        return this.categoryList.get(userChoice);
    }

    private void addFirstCategory() {
        if (this.categoryList.isEmpty()) {
            System.out.println("Det verkar inte finnas någon kategori för matvaror än.");
            createNewCategory(scanner);
        }
    }
    public void createNewCategory(Scanner scanner) {
        System.out.println("Vänligen skriv in namn på ny kategori.");
        this.categoryList.add(new ProductCategory(scanner.next()));
    }
    private void listCategoriesToAdd() {
        System.out.println("1. Lägg till ytterligare kategori");
        printCategoriesInOrder(2);
    }
    private void printCategoriesInOrder(int number) {
        if (this.categoryList.size() >= 1) {
            printCategoriesFormated(number);
        }
    }
    private void printCategoriesFormated(int number) {
        for (int i = 0; i < this.categoryList.size(); i++) {
            System.out.println((i + number) + ". " + chosenCategory(i) + ".");
        }
    }

//_________________________________________________________________________________________________
    private void removeProduct() {
    System.out.println("Välj vilken kategori du vill radera en vara från.");

    List<Integer> productsToRemoveList = getProductIndexes();
    int productToRemove = Integer.parseInt(getChoice());
    this.inventory.remove(productsToRemoveList.get(productToRemove - 1));

}
    private ArrayList<Integer> getProductIndexes() {
        var category = getUserCategoryChoice(1);
        var tempList = new ArrayList<Integer>();
        int count = 1;
        printMatchingProductsAndAddToLoop(category, tempList, count);
        return tempList;
    }

    private void printMatchingProductsAndAddToLoop(ProductCategory category, ArrayList<Integer> tempList, int count) {
        for (int i = 0; i < this.inventory.size(); i++) {
            if (categoryMatch(category, i)) {
                System.out.println(count + " " + this.inventory.printBalance(i));
                tempList.add(i);
                count++;
            }
        }
    }

    private String getChoice() {
        System.out.println("Välj vilken vara du vill ta bort från lagret");
        return scanner.next();
    }
    //_________________________________________________________________________________________________
    private void printProducts() {
        this.inventory.printbalancetest();
    }
    //_________________________________________________________________________________________________
    private void printCategory(ProductCategory category) {
        this.inventory.printProductWithCategory(category);
    }
    private ProductCategory getUserCategoryChoice(int number) {
        printCategoriesInOrder(number);
        return chosenCategory(scanner.nextInt() - 1);
    }
    private boolean categoryMatch(ProductCategory category, int i) {
        return this.inventory.getCategory(i).equals(category);
    }

//_________________________________________________________________________________________________
    public void searchBetweenPrices() {
        BigDecimal lowestPrice = getLowestSearchPrice();
        BigDecimal highestPrice = getHighestSearchPrice();
        this.inventory.printBetweenPrices(lowestPrice, highestPrice);
    }
    private BigDecimal getHighestSearchPrice() {
        System.out.println("Vad är det högsta priset");
        return scanner.nextBigDecimal();
    }
    private BigDecimal getLowestSearchPrice() {
        System.out.println("Vad är det lägsta priset");
        return scanner.nextBigDecimal();
    }
//_________________________________________________________________________________________________









}

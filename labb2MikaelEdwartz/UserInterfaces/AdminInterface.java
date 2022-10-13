package se.iths.labborationer.labb2MikaelEdwartz.UserInterfaces;


import se.iths.labborationer.labb2MikaelEdwartz.FileHandling.GsonReader;
import se.iths.labborationer.labb2MikaelEdwartz.Inventory.InventoryBalance;
import se.iths.labborationer.labb2MikaelEdwartz.Products.Product;
import se.iths.labborationer.labb2MikaelEdwartz.Products.ProductCategory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

import static java.math.BigDecimal.valueOf;

public class AdminInterface {

    private final InventoryBalance inventory;
    private final List<ProductCategory> categoryList;
    private final Scanner scanner;
    private final Menu menu;
    private final GsonReader reader;

    public AdminInterface(InventoryBalance inventory, List<ProductCategory> categories, Scanner scanner, Menu menu, GsonReader reader){
        this.inventory = inventory;
        this.scanner = scanner;
        this.categoryList = categories;
        this.menu = menu;
        this.reader = reader;
    }

    public void start() {
       while (true) {
           Greetings.adminMenuGreeting();
           var input = scanner.nextLine();
           adminMenu(input);
       }
    }

    private void adminMenu(String input) {
        switch (input) {
            case "1" -> addProductsToInventory(addProductsAndCategories()); //autoAddProducts();
            case "2" -> removeProduct();
            case "3" -> printProducts();
            case "4" -> printCategory(getUserCategoryChoice());
            case "5" -> searchBetweenPrices();
            case "6" -> menu.start();
            case "7" -> reader.save(this.inventory);
            case "8" -> autoAddProducts();
            case "e" -> System.exit(0);
        }
    }

    private void addProductsToInventory(ProductCategory category) {
        this.inventory.add(new Product(category, getProductName(), getProductPrice(), getProductSerialCode()));
    }

    private int getProductSerialCode() {
        System.out.println("Vänligen skriv in streckkod på varan.");
        return Integer.parseInt(scanner.nextLine());
    }

    private BigDecimal getProductPrice() {
        System.out.println("Vänligen skriv in pris på varan.");
        return valueOf(Long.parseLong(scanner.nextLine()));
    }

    private String getProductName() {
        System.out.println("Vänligen skriv in namn på varan");
        return scanner.nextLine();
    }

    private ProductCategory addProductsAndCategories() {
        addFirstCategoryIfEmpty();
        listCategoriesToAdd();
        return addNewCategoryOrReturnCategory();
    }

    private void addFirstCategoryIfEmpty() {
        if(isListEmpty())
            addFirstCategory();
    }

    private boolean isListEmpty() {
        return this.categoryList.isEmpty();
    }

    private ProductCategory addNewCategoryOrReturnCategory() {
        while (true) {
            String userChoice = scanner.nextLine();
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
            System.out.println("Det verkar inte finnas någon kategori för matvaror än.");
            createNewCategory(scanner);
    }

    private void createNewCategory(Scanner scanner) {
        System.out.println("Vänligen skriv in namn på ny kategori.");
        this.categoryList.add(new ProductCategory(scanner.nextLine()));
    }

    private void listCategoriesToAdd() {
        System.out.println("1. Lägg till ytterligare kategori");
        printCategoriesInOrder(2);
    }

    private void printCategoriesInOrder(int number) {
        if (this.categoryList.size() >= 1)
            printCategoriesFormatted(number);
    }

    private void printCategoriesFormatted(int number) {
        for (int i = 0; i < this.categoryList.size(); i++)
            System.out.println((i + number) + ". " + chosenCategory(i) + ".");
    }

    private void removeProduct() {
        getCategoryChoiceAndPrintProducts();
        getProductAndRemove();
    }

    private void getCategoryChoiceAndPrintProducts() {
        getCategoryAndPrintProducts();
        System.out.println("Skriv in produktnamnet på varan du vill ta bort");
    }

    private void getCategoryAndPrintProducts() {
        System.out.println("Välj vilken kategori du vill radera en vara från.");
        this.inventory.printProductWithCategory(getUserCategoryChoice());
    }

    private void getProductAndRemove() {
        String input = scanner.nextLine();
        String choice = removeAllProductsChoice();
        removeOption(input, choice);
    }

    private String removeAllProductsChoice() {
        System.out.println("Vill du radera alla varor? (J/N)");
        return scanner.nextLine();
    }

    private void removeOption(String input, String choice) {
        if(choice.equals("J"))
            this.inventory.remove(input);
        else
            removeMultipleProducts(input);
    }

    private void removeMultipleProducts(String input) {
        int nrToRemove = printAndGetNrToRemove();
        var listOfProductsToRemove = this.inventory.listToRemove(input);
        removeFromList(listOfProductsToRemove, nrToRemove);
    }

    private int printAndGetNrToRemove() {
        System.out.println("Hur många vill du ta bort?");
        return Integer.parseInt(scanner.nextLine());
    }

    private void removeFromList(List<Product> tempList, int nrToRemove) {
        for (int i = 0; i < nrToRemove; i++)
            this.inventory.remove(tempList.get(i));
    }

    private void printProducts() {
        printHeader();
        this.inventory.printBalance();
    }

    private void printHeader() {
        System.out.println("Kategori" + printTab(2) + "Varunamn" + printTab(2) + "Pris" + printTab(1) + "EAN-kod" + printTab(2) + " lagersaldo" );
    }

    private void printCategory(ProductCategory category) {
        printHeader();
        this.inventory.printProductWithCategory(category);
    }

    private ProductCategory getUserCategoryChoice() {
        printCategoriesInOrder(1);
        int option = getInput();
        return chosenCategory(option-1);
    }

    private int getInput() {
        int option = Integer.parseInt(scanner.nextLine());

        while(!(option <= this.categoryList.size() && option  > 0))
            option = printErrorMessageOrReturnNumber();

        return option;
    }

    private int printErrorMessageOrReturnNumber() {
        int option;
        System.out.println("Felaktig inmatning, skriv in ny siffra.");
        option = Integer.parseInt(scanner.nextLine());
        return option;
    }

    private void searchBetweenPrices() {
        BigDecimal lowestPrice = getLowestSearchPrice();
        BigDecimal highestPrice = getHighestSearchPrice();
        printHeader();
        this.inventory.printBetweenPrices(lowestPrice, highestPrice);
    }

    private BigDecimal getHighestSearchPrice() {
        System.out.println("Vad är det högsta priset");
        return valueOf(Long.parseLong(scanner.nextLine()));
    }

    private BigDecimal getLowestSearchPrice() {
        System.out.println("Vad är det lägsta priset");
        return valueOf(Long.parseLong(scanner.nextLine()));
    }

    private String printTab(int nrOfTabs){
        return "\t".repeat(Math.max(0, nrOfTabs));
    }

    private void autoAddProducts() {
        addProducts();
        addCategories();
    }

    private void addCategories() {
        IntStream.range(0, this.inventory.size())
                .filter(i -> !(this.categoryList.contains(this.inventory.getCategory(i))))
                .forEach(i -> this.categoryList.add(this.inventory.getCategory(i)));

    }

    private void addProducts() {
        for (int i = 0; i < 20; i++) {
        this.inventory.add(new Product(new ProductCategory("Mejeri"), "Mjölk", valueOf(16), 10938));
        this.inventory.add(new Product(new ProductCategory("Mejeri"), "Grädde", valueOf(11), 10958));
        this.inventory.add(new Product(new ProductCategory("Kött"), "Kyckling", valueOf(67), 19452));
        this.inventory.add(new Product(new ProductCategory("Kött"), "Köttfärs", valueOf(70), 10512));
        this.inventory.add(new Product(new ProductCategory("Frukt"), "Äpple", valueOf(7), 83713));
        this.inventory.add(new Product(new ProductCategory("Grönsaker"), "Morötter", valueOf(23), 12845));
        this.inventory.add(new Product(new ProductCategory("Frukt"), "Banan", valueOf(6), 86437));
        }
    }
}

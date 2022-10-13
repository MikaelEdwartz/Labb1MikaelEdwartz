package se.iths.labborationer.labb2;

import java.util.List;
import java.util.Scanner;


public class Menu {
    private final Scanner scanner;
    private final CustomerInterface costumerInterface;
    private final AdminInterface adminInterface;
    private final Menu menu;
    private final InventoryBalance inventory;

    public Menu(InventoryBalance inventory, List<ProductCategory> categories, Scanner scanner, GsonReader reader) {
        this.scanner = scanner;
        this.menu = this;
        this.inventory = inventory;
        costumerInterface = new CustomerInterface(this.inventory, categories, scanner, this.menu, reader);
        adminInterface = new AdminInterface(this.inventory, categories, this.scanner, this.menu, reader);
    }

    public void start() {
        boolean loop = true;
        startUpGreeting();
        startUpMenu(loop);
    }

    private void startUpMenu(boolean loop) {
        while (loop) {
            var input = scanner.nextLine();
            switch (input) {
                case "1" -> adminInterface.start();
                case "2" -> costumerInterface.start(true);
                case "e" -> loop = false;
            }
        }
    }

    private void startUpGreeting() {
        if(inventoryIsEmpty())
            firstGreeting();
        printGreeting();

    }

    private static void printGreeting() {
        System.out.println("Välkommen! vänligen logga in genom att välja ett alternativ nedan.");
        System.out.println("Tryck 1 för att logga in som admin");
        System.out.println("Tryck 2 för att börja shoppa");
        System.out.println("Tryck e för att avsluta.");
    }

    private boolean inventoryIsEmpty() {
        return this.inventory.getInventory().isEmpty();
    }

    private void firstGreeting(){
        System.out.println("Det verkar som att din affär öppnas för första gången. Du dirigeras vidare till admin menyn!");
        adminInterface.start();
    }
}




























/*
  private void startUpMenu(boolean loop) {
        while (loop) {
            var input = scanner.next();
            switch (input) {
                case "1" -> adminMenu(loop);
                case "2" -> customerMenu(loop);
                case "e" -> loop = false;
            }
        }
    }

    private void customerMenu(boolean loop) {
        var register = new Register();
        startupkategorier();
        while (loop) {

            costumerMenuGreeting();
            var input = scanner.next();

            System.out.println("====================");
            switch (input) {
                case "1" -> loopThroughCategories();
                case "2" -> getSpecificCategor(register);
                case "3" -> listAllProducts(register);
                case "4" -> register.printRegister();
                case "e" -> loop = false;
            }
            System.out.println("====================");
        }
    }

    private void getSpecificCategor(Register register) {
        var categoryChoice = getUserCategoryChoice(1);


        var list = new InventoryBalance(this.balance.getProductWithCategory(categoryChoice));

        addToRegister(register, list);

    }

    public void searchByCategory() {

    }

    private void printCategory(ProductCategory category) {
        this.balance.printProductWithCategory(category);
    }




    private void loopThroughCategories() {
        String input = "categori";
    }

    private void listAllProducts(Register register) {

        var list = new InventoryBalance(this.balance.getProducts(this.balance.getInventory()));
        addToRegister(register, list);

    }

    private void addToRegister(Register register, InventoryBalance list) {
        printProductsInStore(register, list);

        int choice = getChoice("Skriv in ett nummer för att lägga till produkten i varukorg");
        long nrOfProducts = getNrOfProducts("Hur många vill du köpa?");

        if(isProductAvailable(register, list, choice, nrOfProducts))
            addNrOfProductsToRegister(register, list, choice, nrOfProducts);
        else
            System.out.println("Finns inte tillräckligt med varor");
    }

    private void printProductsInStore(Register register, InventoryBalance list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + " " + list.getProduct(i) + " " + actualProductsInStore(list, register, i));
        }
    }

    private boolean isProductAvailable(Register register, InventoryBalance list, int choice, long nrOfProducts) {
        return nrOfProducts <= actualProductsInStore(list, register, (choice - 1)) && actualProductsInStore(list, register, (choice - 1)) >= 0;
    }

    private long getNrOfProducts(String x) {
        System.out.println(x);
        long nrOfProducts = scanner.nextInt();
        return nrOfProducts;
    }

    private int getChoice(String x) {
        System.out.println(x);
        int choice = scanner.nextInt();
        return choice;
    }

    private static void addNrOfProductsToRegister(Register register, InventoryBalance list, int choice, long nrOfProducts) {
        for (int i = 0; i < nrOfProducts; i++) {
            register.add(list.getProduct(choice - 1));
        }
    }

    private int actualProductsInStore(InventoryBalance list, Register register, int i ){
        int produtsInStore = (int) this.balance.nrOfProducts(list.getProduct(i));

        for (int j = 0; j < register.sameProductsInRegister(list.getProduct(i)) ; j++) {
            produtsInStore--;
        }
        return produtsInStore;
    }


    private void printProducts() {
        this.balance.printbalancetest();
    }




































    private void adminMenu(boolean loop) {
        while (loop) {
            var input = scanner.nextLine();

            switch (input) {
                case "1" -> startupkategorier();//addProductToInventoryBalance(addProductsAndCategories(loop));
                case "2" -> removeProduct();
                case "3" -> printProducts();
                case "4" -> printCategory(getUserCategoryChoice(1));
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

    private void costumerMenuGreeting() {
        System.out.println("1. Vill gå igenom alla kategorier en i taget?");
        System.out.println("2. Välj en specifik kategori");
        System.out.println("3. Printa ut alla produkter");
        System.out.println("4. print your register");
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


    public ProductCategory addProductsAndCategories(boolean loop) {
        addFirstCategory();
        listCategoriesToAdd();

        while (true) {
            String userChoice = scanner.next();
            if (Integer.parseInt(userChoice) == 1)
                createNewCategory(scanner);
            else if (userChoice.equals("e"))
                adminMenu(loop);
            else
                return this.categories.get(Integer.parseInt(userChoice) - 2);

            listCategoriesToAdd();
        }
    }

    public void addProductToInventoryBalance(ProductCategory category) {
        this.balance.add(new Product(category, getProductName(), getProductPrice(), getProductSerialCode()));
    }

    public void createNewCategory(Scanner scanner) {
        System.out.println("Vänligen skriv in namn på ny kategori.");
        this.categories.add(new ProductCategory(scanner.next()));

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

        ArrayList<Integer> tempList = getProductIndexes();
        int productToRemove = getChoice("Välj vilken vara du vill ta bort från lagret");
        this.balance.remove(tempList.get(productToRemove - 1));

    }

    private ArrayList<Integer> getProductIndexes() {
        var category = getUserCategoryChoice(1);
        var tempList = new ArrayList<Integer>();
        int count = 1;
        for (int i = 0; i < this.balance.size(); i++) {
            if (categoryMatch(category, i)) {
                System.out.println(count + " " + this.balance.printBalance(i));
                tempList.add(i);
                count++;
            }
        }
        return tempList;
    }

    private ArrayList<Product> addCategoryProductsToList() {
        var category = getUserCategoryChoice(1);
        var tempList = new ArrayList<Product>();
        int count = 1;
        for (int i = 0; i < this.balance.size(); i++) {
            if (categoryMatch(category, i)) {
                System.out.println(count + " " + this.balance.printBalance(i));
                tempList.add(this.balance.getProduct(i));
                count++;
            }
        }
        return tempList;
    }


    private boolean categoryMatch(ProductCategory category, int i) {
        return this.balance.getCategory(i).equals(category);
    }




    private ProductCategory getUserCategoryChoice(int number) {
        printCategoriesInOrder(number);
        return this.categories.get(scanner.nextInt() - 1);
    }

    private void printCategoriesInOrder(int number) {
        if (this.categories.size() >= 1) {
            printCategoriesFormated(number);
        }
    }

    private void printCategoriesFormated(int number) {
        for (int i = 0; i < this.categories.size(); i++) {
            System.out.println((i + number) + ". " + getCategoryAtIndex(i) + ".");
        }
    }


    public void printInventoryBalance() {
        this.balance.printBalance();
    }

    public void searchBetweenPrices() {
        BigDecimal lowestPrice = getLowestSearchPrice();
        BigDecimal highestPrice = getHighestSearchPrice();
        this.balance.printBetweenPrices(lowestPrice, highestPrice);
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



    public void startupkategorier() {
        this.balance.add(new Product(new ProductCategory("Dairy"), "Milk", valueOf(199), 10938));
        this.balance.add(new Product(new ProductCategory("Dairy"), "Milk", valueOf(199), 10938));
        this.balance.add(new Product(new ProductCategory("Dairy"), "Milk", valueOf(199), 10938));
        this.balance.add(new Product(new ProductCategory("Dairy"), "Milk", valueOf(199), 10938));
        this.balance.add(new Product(new ProductCategory("Dairy"), "Milk", valueOf(199), 10938));
        this.balance.add(new Product(new ProductCategory("Dairy"), "Cream", valueOf(11), 1098));
        this.balance.add(new Product(new ProductCategory("Meat"), "Chicken", valueOf(12), 109));
        this.balance.add(new Product(new ProductCategory("Meat"), "Chicken", valueOf(12), 109));
        this.balance.add(new Product(new ProductCategory("Meat"), "Chicken", valueOf(12), 109));
        this.balance.add(new Product(new ProductCategory("Meat"), "Beef", valueOf(1), 10));
        this.balance.add(new Product(new ProductCategory("Fruit"), "Apples", valueOf(11), 1038));
        this.balance.add(new Product(new ProductCategory("Fruit"), "Apples", valueOf(11), 1038));
        this.balance.add(new Product(new ProductCategory("Fruit"), "Apples", valueOf(11), 1038));
        this.balance.add(new Product(new ProductCategory("Vegetable"), "Carrot", valueOf(17), 938));
        this.balance.add(new Product(new ProductCategory("Vegetable"), "Carrot", valueOf(17), 938));
        this.balance.add(new Product(new ProductCategory("Fruit"), "Banana", valueOf(14), 18));
        this.balance.add(new Product(new ProductCategory("Fruit"), "Banana", valueOf(14), 18));

        for (int i = 0; i < this.balance.size(); i++) {
            if (!(this.categories.contains(this.balance.getCategory(i))))
                this.categories.add(this.balance.getCategory(i));
        }
    }*/



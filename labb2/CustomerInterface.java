package se.iths.labborationer.labb2;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import static java.math.BigDecimal.valueOf;

public class CustomerInterface {
    private final InventoryBalance inventory;
    private final List<ProductCategory> categories;
    private final Scanner scanner;
    private int count;
    private final Register register;
    private final Menu menu;
    private final GsonReader reader;

    public CustomerInterface(InventoryBalance inventory, List<ProductCategory> categories, Scanner scanner, Menu menu, GsonReader reader) {
        this.inventory = inventory;
        this.categories = categories;
        this.scanner = scanner;
        this.register = new Register();
        this.menu = menu;
        this.reader = reader;
    }

    public void start(boolean loop) {
        while (loop) {
            costumerMenuGreeting();
            var input = scanner.nextLine();
            System.out.println("-----------------------------------------");
            switch (input) {
                case "1" -> loopThroughCategories(register);
                case "2" -> getSpecificCategory(register);
                case "3" -> listAllProducts(register);
                case "4" -> printRegister();
                case "5" -> checkOut(register);
                case "6" -> menu.start();
                case "e" -> loop = false;
            }
        }
    }

    private void printRegister() {
        System.out.println("Din varukorg består av:");
        register.printRegister();
    }

    private void costumerMenuGreeting() {
        System.out.println("1. Vill gå igenom alla kategorier en i taget?");
        System.out.println("2. Välj en specifik kategori");
        System.out.println("3. Printa ut alla produkter");
        System.out.println("4. print your register");
        System.out.println("5. Gå till kassan och betala");
    }

    private void loopThroughCategories(Register register) {
        for (int i = 0; i < this.categories.size(); i++)
            printChosenProductsAndAdd(register, i);
    }

    private void printChosenProductsAndAdd(Register register, int i) {
        var list = getListWithProductsWithCategoryToPrint(register, i);
        int choice = getProductOrQuit();
        if(choice == 0)
            return;
        askAndAddProduct(register, list, choice);
    }

    private InventoryBalance getListWithProductsWithCategoryToPrint(Register register, int i) {
        var category = this.categories.get(i);
        var list = new InventoryBalance(this.inventory.getListWithChosenCategory(category));
        printProductAndOption(register, list);
        return list;
    }

    private void printProductAndOption(Register register, InventoryBalance list) {
        System.out.println("0 Nästa kategori");
        printProductsInStore(register, list);
    }

    private void printProductsInStore(Register register, InventoryBalance list) {
        printHeader();
        this.count = 1;
        for (int i = 0; i < list.size(); i++)
            printProductsFormatted(register, list, i);
        System.out.println(count + " För att gå tillbaka");
    }

    private void printProductsFormatted(Register register, InventoryBalance list, int i) {
        System.out.println((i + 1) + " " + printCostumerProduct(list, i)
                + " " + actualProductsInStore(list, register, i) + "st");
        count++;
    }

    private static String printCostumerProduct(InventoryBalance list, int i) {
        var product = list.getProduct(i);
        return product.toStringCustomer();
    }

    private void printHeader() {
        System.out.println("Kategori" + printTab(2)
                + "Varunamn" + printTab(2) + "Pris"
                + printTab(2) + "EAN-kod" + printTab(2)
                + "Lagersaldo" );
    }

    private int getProductOrQuit() {
        int choice = getChoice();
        if (choice == count)
            start(true);
        return choice;
    }

    private void askAndAddProduct(Register register, InventoryBalance list, int choice) {
        int nrOfProducts = getNrOfProducts();
        addProductOrPrintErrorMessage(register, list, choice, nrOfProducts);
    }

    private void addProductOrPrintErrorMessage(Register register, InventoryBalance list, int choice, int nrOfProducts) {
        if(isProductAvailable(register, list, choice, nrOfProducts))
            addNrOfProductsToRegister(register, list, choice, nrOfProducts);
        else
            System.out.println("Finns inte tillräckligt med varor");
    }

    private int actualProductsInStore(InventoryBalance list, Register register, int i ){
        return getProductsInStore(list, register, i);
    }

    private int getProductsInStore(InventoryBalance list, Register register, int i) {
        int productsInStore = (int) this.inventory.nrOfProducts(list.getProduct(i));
        for (int j = 0; j < register.sameProductsInRegister(list.getProduct(i)) ; j++)
            productsInStore--;
        return productsInStore;
    }

    private int getChoice() {
        System.out.println("Skriv siffran på produkten du vill ha eller gå tillbaka.");
        return Integer.parseInt(scanner.nextLine());
    }

    private int getNrOfProducts() {
        System.out.println("Hur många vill du köpa?");
        return Integer.parseInt(scanner.nextLine());
    }

    private boolean isProductAvailable(Register register, InventoryBalance list, int choice, int nrOfProducts) {
        return nrOfProducts <= actualProductsInStore(list, register, (choice - 1)) && actualProductsInStore(list, register, (choice - 1)) >= 0;
    }

    private static void addNrOfProductsToRegister(Register register, InventoryBalance list, int choice, long nrOfProducts) {
        for (int i = 0; i < nrOfProducts; i++)
            register.add(list.getProduct(choice - 1));
    }

    private void getSpecificCategory(Register register) {
        var categoryChoice = getUserCategoryChoice(1);
        var list = new InventoryBalance(this.inventory.getListWithChosenCategory(categoryChoice));
        addToRegister(register, list);
    }

    private ProductCategory getUserCategoryChoice(int number) {
        printCategoriesInOrder(number);
        return this.categories.get(scanner.nextInt() - 1);
    }

    private void addToRegister(Register register, InventoryBalance list) {
        printProductsInStore(register, list);
        int choice = getProductOrQuit();
        askAndAddProduct(register, list, choice);
    }

    private void printCategoriesInOrder(int number) {
        if (this.categories.size() >= 1) {
            printCategoriesFormatted(number);
        }
    } private void printCategoriesFormatted(int number) {
        for (int i = 0; i < this.categories.size(); i++)
            System.out.println((i + number) + ". " + getCategoryAtIndex(i) + ".");
    }

    private ProductCategory getCategoryAtIndex(int i) {
        return this.categories.get(i);
    }

    private void listAllProducts(Register register) {

        var list = new InventoryBalance(this.inventory.getDistinctProducts(this.inventory.getInventory()));
        do addToRegister(register, list);
        while (true);

    }

    private void checkOut(Register register) {
        var distinctRegister = new Register(register.getDistinctRegister(register.register));
        var priceToPay = valueOf(0);
        printReceipt(distinctRegister, priceToPay);
        saveInventoryToFile();
        PrintFarewellAndExit();
    }

    private void printReceipt(Register distinctRegister, BigDecimal priceToPay) {
        System.out.println("Produkt" + printTab(3) + "á-pris" + printTab(2) + "st" + printTab(2) + "totalpris");

        for (int i = 0; i < distinctRegister.size(); i++)
            priceToPay = printProductsAndGetTotalPrice(distinctRegister, priceToPay, i);

        BigDecimal discountedPrice = priceToPay;
        discountedPrice = getDiscountedPrice(priceToPay, discountedPrice);
        System.out.println();
        printPriceAndDiscount(priceToPay, discountedPrice);
    }

    private static void PrintFarewellAndExit() {
        System.out.println("Tack för ditt köp, välkommen åter!");
        System.exit(0);
    }

    private void saveInventoryToFile() {
        removeRegisterItemsFromBalance();
        reader.save(this.inventory);
    }

    private BigDecimal printProductsAndGetTotalPrice(Register distinctRegister, BigDecimal priceToPay, int i) {
        printProductsInRegister(distinctRegister, i);
        priceToPay = priceToPay.add(totalPrice(getProduct(distinctRegister, i)));
        return priceToPay;
    }

    private void printPriceAndDiscount(BigDecimal priceToPay, BigDecimal discountedPrice) {
        System.out.println("Ord. pris " + printTab(7)+ priceToPay + " kr");
        printDiscountValue(priceToPay);
        System.out.println(printTab(9) + "__________");
        System.out.println("Att betala "+ printTab(7) + discountedPrice + " kr");
    }

    private void printProductsInRegister(Register distinctRegister, int i) {
        System.out.println(getProduct(distinctRegister, i).product()
             + printTab(getNrOfTabs(getProduct(distinctRegister, i)))
             + getProduct(distinctRegister, i).price()
             + printTab(3) + nrOfProducts(getProduct(distinctRegister, i))
             + "st" + printTab(3) + totalPrice(getProduct(distinctRegister, i))
             + "kr");
    }

    private static Product getProduct(Register distinctRegister, int i) {
        return distinctRegister.getProduct(i);
    }

    private static int getNrOfTabs(Product product) {
        int length = product.product().length();
        if(length >= 12)
            return 1;
        if(length >=8)
            return 2;
        if(length >= 4)
            return 3;

        return 0;
    }

    private BigDecimal getDiscountedPrice(BigDecimal priceToPay, BigDecimal discountedPrice) {
        if(priceToPay.compareTo(valueOf(2000)) >= 0)
            return applyDiscount(new TwentyPercent(), priceToPay);
        else if(priceToPay.compareTo(valueOf(1000)) >= 0)
            return applyDiscount(new TenPercent(), priceToPay);

        return discountedPrice;
    }

    private String printTab(int nrOfTabs){
        return "\t".repeat(Math.max(0, nrOfTabs));
    }

    private BigDecimal totalPrice(Product product) {
        var nrOfProducts = valueOf(nrOfProducts(product));
        return product.price().multiply(nrOfProducts);
    }

    private  void printDiscountValue(BigDecimal priceToPay) {
        var discount = getDiscount(priceToPay);
        if(discount.compareTo(valueOf(1)) > 0)
            System.out.println("Rabatt " + printTab(8) + discount + " kr");
    }

    private static BigDecimal getDiscount(BigDecimal priceToPay) {
        var discount = valueOf(0);

        if(priceToPay.compareTo(valueOf(2000)) > 0)
             discount = priceToPay.multiply(valueOf(0.2));
        else if(priceToPay.compareTo(valueOf(1000)) > 0)
             discount = priceToPay.multiply(valueOf(0.1));

        return discount;
    }

    private BigDecimal applyDiscount(Discounter discount, BigDecimal amount){
        return discount.apply(amount);

    }

    private void removeRegisterItemsFromBalance(){
        for (int i = 0; i < register.size(); i++)
            this.inventory.remove(getProduct(this.register, i));
    }

    private int nrOfProducts(Product product){
        return (int) register.sameProductsInRegister(product);
    }

}


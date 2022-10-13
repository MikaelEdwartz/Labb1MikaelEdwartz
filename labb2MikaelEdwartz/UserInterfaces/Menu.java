package se.iths.labborationer.labb2MikaelEdwartz.UserInterfaces;

import se.iths.labborationer.labb2MikaelEdwartz.FileHandling.GsonReader;
import se.iths.labborationer.labb2MikaelEdwartz.Inventory.InventoryBalance;
import se.iths.labborationer.labb2MikaelEdwartz.Products.ProductCategory;

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
        Greetings.firstMenuGreeting();
    }

    private boolean inventoryIsEmpty() {
        return this.inventory.getInventory().isEmpty();
    }

    private void firstGreeting(){
        System.out.println("Det verkar som att din affär öppnas för första gången. Du dirigeras vidare till admin menyn!");
        adminInterface.start();
    }
}


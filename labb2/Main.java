package se.iths.labborationer.labb2;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        var balance = new InventoryBalance();
        var categories = new ArrayList<ProductCategory>();
        var scanner = new Scanner(System.in);
        var menu = new Menu(balance, categories, scanner);
        menu.start();

    }
}
//TODO Kunna skapa kategorier                                               J
//TODO Kunna skapa produkter                                                J
//TODO Lägga till produkter i ArrayList                                     J
//TODO Ändra pris på produkt??
//TODO Hålla koll på hur många av en specifik vara som finns i lager
//TODO
//TODO Ta bort produkter från ArrayList
//TODO Lista vald kategori
//TODO Sortera produkter från biligast till dyrast
//TODO Sortera mellan ett visst prisintervall
//TODO
//TODO
//TODO
//TODO
//TODO
//TODO
//TODO
//TODO
//TODO
//TODO
//TODO
//TODO
//TODO
//TODO
//TODO
//TODO
//TODO
//TODO
//TODO
//TODO

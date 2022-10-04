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

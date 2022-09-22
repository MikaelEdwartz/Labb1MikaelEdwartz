package se.iths.labborationer.labb2;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        InventoryBalance balance = new InventoryBalance();
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu(balance, scanner);
        menu.start();

    }
}

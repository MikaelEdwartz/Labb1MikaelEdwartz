package se.iths.labborationer.labb2;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        var categories = new ArrayList<ProductCategory>();
        var scanner = new Scanner(System.in);
        var reader = new GsonReader(categories);
        var menu = new Menu(reader.read(), categories, scanner, reader);
        menu.start();
    }
}

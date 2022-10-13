package se.iths.labborationer.labb2MikaelEdwartz;
import se.iths.labborationer.labb2MikaelEdwartz.FileHandling.GsonReader;
import se.iths.labborationer.labb2MikaelEdwartz.Products.ProductCategory;
import se.iths.labborationer.labb2MikaelEdwartz.UserInterfaces.Menu;

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

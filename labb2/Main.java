package se.iths.labborationer.labb2;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        var categories = new ArrayList<ProductCategory>();

        var scanner = new Scanner(System.in);
        var reader = new JsonReader(categories);
        var menu = new Menu(reader.read(), categories, scanner, reader);
        menu.start();

    }
}

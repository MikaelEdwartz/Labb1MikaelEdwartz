package se.iths.labborationer.labb2;

public class Main {

    public static void main(String[] args) {
        InventoryBalance balance = new InventoryBalance();
        for (int i = 0; i < 10; i++) {

           balance.add(new Product(new ProductCategory("Dairy"), "Milk", 5, 10938));

        }


        balance.printBalance();
    }
}

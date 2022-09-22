package se.iths.labborationer.labb2;

import java.util.ArrayList;

public class InventoryBalance {
    private ArrayList<Product> inventory;

    public InventoryBalance() {
        this.inventory = new ArrayList<>();

    }

    public void add(Product product){
        this.inventory.add(product);

    }

    public void printBalance(){
        for (int i = 0; i < this.inventory.size(); i++) {

            System.out.println(this.inventory.get(i));
        }

    }
}

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

    public ArrayList<String> getAllCategories(){
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < this.inventory.size(); i++) {
            String category = String.valueOf(this.inventory.get(i).category());

            if (!(list.contains(category)))
                list.add(category);
        }

        return list;
    }

    public void printBalance(){
        for (int i = 0; i < inventory.size(); i++) {

            System.out.println(inventory.get(i));
        }




    }

    @Override
    public String toString() {
        return "InventoryBalance{" +
                "inventory=" + inventory +
                '}';
    }

    public int size() {
        return inventory.size();
    }
}

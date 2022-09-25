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


    public ArrayList<Product> getInventory() {
        return inventory;
    }

    public Product getProduct(int i){
        return this.inventory.get(i);
    }
    public ProductCategory getCategory(int i){
        return this.inventory.get(i).category();
    }
    public String getProductName(int i){
        return this.inventory.get(i).product();
    }
    public ArrayList<String> getAllCategories(){
        var list = new ArrayList<String>();

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

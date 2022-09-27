package se.iths.labborationer.labb2;

import java.util.ArrayList;
import java.util.Objects;

public class InventoryBalance<T> {
    private ArrayList<Product> inventory;

    public InventoryBalance() {
        this.inventory = new ArrayList<>();

    }

    public void add(Product product){
        this.inventory.add(product);

    }

    public void remove(int i){
        this.inventory.remove(this.getProduct(i));
    }

    public int getSerialCode(int i ){
        return this.inventory.get(i).productNumber();
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
        this.inventory.forEach(System.out::println);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryBalance that = (InventoryBalance) o;
        return Objects.equals(inventory, that.inventory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inventory);
    }

    public String printBalance(int i) {
        return this.inventory.get(i).category() + ", " + this.inventory.get(i).product() + ", "  + this.inventory.get(i).price() +   ", " + this.inventory.get(i).productNumber();
    }

    public int size() {
        return inventory.size();
    }
}

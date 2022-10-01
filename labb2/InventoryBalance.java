package se.iths.labborationer.labb2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InventoryBalance {
    private ArrayList<Product> inventory;

    public InventoryBalance() {
        this.inventory = new ArrayList<>();

    }
    public InventoryBalance(ArrayList<Product> list){
        this.inventory = new ArrayList<>(list);
    }

    public void add(Product product){
        this.inventory.add(product);

    }

    public void remove(int i){
        this.inventory.remove(this.getProduct(i));
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



    public void printProductWithCategory(ProductCategory category){
        this.inventory.stream()
                .filter(p -> productMatch(p.category(), category))
                .forEach(System.out::println);
    }

    public ArrayList<Product> getProductWithCategory(ProductCategory category){

        List<Product> list = this.inventory.stream()
                .filter(p -> productMatch(p.category(), category))
                .distinct()
                .toList();
        ArrayList<Product> sortedList = new ArrayList<>(list);

        return sortedList;
    }

    public long nrOfProducts(Product number){
        return this.inventory.stream().filter(product -> product.matchingEanCode(number.productNumber())).count();
    }


    public boolean productMatch(ProductCategory p ,ProductCategory o){
        return p.category().equals(o.category());
    }

    public void printBetweenPrices(BigDecimal lowestInputPrice, BigDecimal highestInputPrice){
            BigDecimal lowestPrice = lowestInputPrice;
            BigDecimal highestPrice = highestInputPrice;
            this.inventory.stream().filter(p-> p.price()
                            .compareTo(highestPrice) <= 0)
                            .filter(p -> p.price().compareTo(lowestPrice) >= 0)
                            .forEach(System.out::println);
        }


    public void printBalance(){
        this.inventory.forEach(System.out::println);

    }

    public void printbalancetest(){
        var list = new ArrayList<>(this.inventory);

        list.stream()
                .distinct()
                .forEach(p -> System.out.println(p + " " +  nrOfProducts(p) + " st i lager"));

        list.stream().distinct().toList();
    }

    public ArrayList<Product> getProducts(ArrayList<Product> listIn){
        List<Product> lists = new ArrayList<>(listIn);
        lists = lists.stream().distinct().toList();
        ArrayList<Product> list = new ArrayList<>(lists);
        return list;
    }

    public String printBalance(int i) {
        return this.inventory.get(i).category() + ", " + this.inventory.get(i).product() + ", "  + this.inventory.get(i).price() +   ", " + this.inventory.get(i).productNumber();
    }

    private boolean categoryMatch(ProductCategory category, int i) {
        return this.inventory.get(i).category().equals(category);
    }

    public int size() {
        return inventory.size();
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


}











//    public ArrayList<String> getAllCategories(){
//        var list = new ArrayList<String>();
//
//        for (int i = 0; i < this.inventory.size(); i++) {
//            String category = String.valueOf(this.inventory.get(i).category());
//
//            if (!(list.contains(category)))
//                list.add(category);
//        }
//
//        return list;
//    }

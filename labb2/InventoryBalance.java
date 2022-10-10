package se.iths.labborationer.labb2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InventoryBalance {
    private List<Product> inventory;

    public InventoryBalance() {
        this.inventory = new ArrayList<>();

    }
    public InventoryBalance(List<Product> list){
        this.inventory = new ArrayList<>(list);
    }

    public void add(Product product){
        this.inventory.add(product);
    }

    public void remove(int i){
        this.inventory.remove(this.getProduct(i));
    }

    public void remove(int EanNumber, String name){

    }



    public List<Product> getInventory() {
        return inventory;
    }

    public Product getProduct(int i){
        return this.inventory.get(i);
    }



    public ProductCategory getCategory(int i){
        return this.inventory.get(i).category();
    }



    public void printProductWithCategory(ProductCategory category){
        System.out.println("Kategori \t Produkt \t\t Pris \t Artikelnummer");
        this.inventory.stream()
                .filter(p -> productMatch(p.category(), category))
                .distinct()
                .forEach(this::printProductSaldo);
    }

    public List<Product> getListWithChosenCategory(ProductCategory category){
        return this.inventory.stream()
                .filter(p -> productMatch(p.category(), category))
                .distinct()
                 .toList();
    }

    public long nrOfProducts(Product number){
        return this.inventory.stream()
                .filter(product -> product.matchingEanCode(number.productNumber()))
                .count();
    }


    public boolean productMatch(ProductCategory p ,ProductCategory o){
        return p.category().equals(o.category());
    }

    public void printBetweenPrices(BigDecimal lowestInputPrice, BigDecimal highestInputPrice){
            BigDecimal lowestPrice = lowestInputPrice;
            BigDecimal highestPrice = highestInputPrice;
            this.inventory.stream().filter(p-> isLowerThan(highestPrice, p))
                            .filter(p -> isHigherThan(lowestPrice, p))
                            .distinct()
                            .forEach(this::printProductSaldo);
        }

    private static boolean isLowerThan(BigDecimal highestPrice, Product p) {
        return p.price().compareTo(highestPrice) <= 0;
    }

    private static boolean isHigherThan(BigDecimal lowestPrice, Product p) {
        return p.price().compareTo(lowestPrice) >= 0;
    }

    public void printBalance(){
        this.inventory.forEach(System.out::println);
    }

    public void printbalancetest(List<Product> inventory){
        inventory.stream()
                .distinct()
                .forEach(p -> printProductSaldo(p));

    }

    private void printProductSaldo(Product p) {
        System.out.println(p + " " +  nrOfProducts(p) + " st i lager");
    }

    public List<Product> getDistinctProducts(List<Product> listIn){
        return listIn
                .stream()
                .distinct()
                .toList();
    }

    public String printBalance(int i) {
        return this.inventory.get(i).category() + ", "
                + this.inventory.get(i).product() + ", "
                + this.inventory.get(i).price() +   ", "
                + this.inventory.get(i).productNumber();
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











//    public List<String> getAllCategories(){
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

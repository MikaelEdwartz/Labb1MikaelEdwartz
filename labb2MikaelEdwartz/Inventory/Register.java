package se.iths.labborationer.labb2MikaelEdwartz.Inventory;

import se.iths.labborationer.labb2MikaelEdwartz.Products.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class Register {
    private final List<Product> register;

    public Register() {
        this.register = new ArrayList<>();
    }

    public Register(List<Product> list){
        this.register = list;
    }

    public List<Product> getRegister(){
        return this.register;
    }

    public int size(){
        return this.register.size();
    }

    public Product getProduct(int i ){
        return this.register.get(i);
    }

    public void printRegister(){
            this.register.stream()
                    .distinct()
                    .sorted(sortByNaturalOrder())
                    .forEach(this::printProductsInRegister);
    }

    private static Comparator<Product> sortByNaturalOrder() {
        return Comparator.comparing(InventoryBalance.compareCategoryOrder())
                .thenComparing(Product::product);
    }

    private void printProductsInRegister(Product p) {
        System.out.println(p + " " + sameProductsInRegister(p) + " st");
    }

    public long sameProductsInRegister(Product productN){
        return this.register.stream()
                .filter(isSameProduct(productN))
                .count();
    }

    private Predicate<Product> isSameProduct(Product productN) {
        return product -> isSameProduct(productN, product);
    }

    private static boolean isSameProduct(Product productN, Product product) {
        return product.matchingEanCode(productN.productNumber());
    }

    public List<Product> getDistinctRegister(List<Product> listIn){
        return listIn.stream()
                .distinct()
                .toList();
    }

    public void add(Product product){
        this.register.add(product);
    }
}

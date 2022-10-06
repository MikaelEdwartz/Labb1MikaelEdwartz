package se.iths.labborationer.labb2;

import java.util.ArrayList;
import java.util.List;

public class Register {
    List<Product> register;

    public Register() {
        this.register = new ArrayList<>();
    }
    public Register(List<Product> list){
        this.register = list;
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
                    .forEach(this::printProductsInRegister);
    }

    private void printProductsInRegister(Product p) {
        System.out.println(p + " " + sameProductsInRegister(p));
    }

    public long sameProductsInRegister(Product productN){
        return this.register.stream()
                .filter(product -> isSameProduct(productN, product))
                .count();
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

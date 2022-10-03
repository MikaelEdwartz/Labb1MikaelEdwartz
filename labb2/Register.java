package se.iths.labborationer.labb2;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Register {
    ArrayList<Product> register;

    public Register() {
        this.register = new ArrayList<>();
    }
    public Register(ArrayList<Product> list){
        this.register = list;
    }

    public int size(){
        return this.register.size();
    }
    public Product getProduct(int i ){
        return this.register.get(i);
    }
    public void printRegister(){
            this.register.stream().distinct().forEach(p -> System.out.println(p + " " + sameProductsInRegister(p)));
    }

    public long sameProductsInRegister(Product productN){
        return this.register.stream().filter(product -> product.matchingEanCode(productN.productNumber())).count();
    }
    public ArrayList<Product> getRegister(ArrayList<Product> listIn){
        List<Product> lists = new ArrayList<>(listIn);
        lists = lists.stream().distinct().toList();
        ArrayList<Product> list = new ArrayList<>(lists);
        return list;
    }

    public void add(Product product){
        this.register.add(product);
    }
}

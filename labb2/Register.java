package se.iths.labborationer.labb2;

import java.util.ArrayList;

public class Register {
    ArrayList<Product> register;

    public Register() {
        this.register = new ArrayList<>();
    }

    public int size(){
        return this.register.size();
    }
    public void printRegister(){
            this.register.stream().distinct().forEach(p -> System.out.println(p + " " + sameProductsInRegister(p)));
    }

    public long sameProductsInRegister(Product number){
        return this.register.stream().filter(product -> product.matchingEanCode(number.productNumber())).count();
    }

    public void add(Product product){
        this.register.add(product);
    }
}

package se.iths.labborationer.labb2;

import java.util.ArrayList;

public class Register {
    ArrayList<Product> register;

    public Register() {
        this.register = new ArrayList<>();
    }

    public void add(Product product){
        this.register.add(product);
    }
}

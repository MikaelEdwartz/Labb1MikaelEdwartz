package se.iths.labborationer.labb2;

public class ProductCategory {

    private final String category;

    public ProductCategory(String category){
        this.category = category;


    }


    public String getCategory() {
        return category;
    }


    @Override
    public String toString() {
        return category;
    }



}

package se.iths.labborationer.labb2;

public class Vegetables extends Product{

    private final String productCategory;

    public Vegetables(String productCategory, String product, float price, int productNumber){
        super(product, price, productNumber);
        this.productCategory = "Vegetables";

    }

    public String getProductCategory() {
        return productCategory;
    }

    @Override
    public String toString() {
        return this.productCategory + "," + super.getProduct() + "," + super.getPrice() + "," + super.getProductNumber();
    }

}

package se.iths.labborationer.labb2;

public class Meat extends Product{

    private final String productCategory;

    public Meat(String product, float price, int productNumber, String productCategory) {
        super(product, price, productNumber);
        this.productCategory = productCategory;
    }

    public String getProductCategory() {
        return productCategory;
    }

    @Override
    public String toString() {
        return this.productCategory + "," + super.getProduct() + "," + super.getPrice() + "," + super.getProductNumber();
    }


}

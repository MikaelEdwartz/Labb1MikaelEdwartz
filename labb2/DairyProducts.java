package se.iths.labborationer.labb2;

public class DairyProducts extends Product{


    private final String productCategory;

    public DairyProducts(String productCategory, String product, float price, int productNumber) {
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

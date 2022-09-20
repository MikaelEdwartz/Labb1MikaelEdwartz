package se.iths.labborationer.labb2;

public abstract class Product {
    private final String product;
    private final float price;
    private final int productNumber;


    public Product(String product, float price, int productNumber){
        this.product = product;
        this.price = price;
        this.productNumber = productNumber;

    }

    public String getProduct() {
        return product;
    }

    public float getPrice() {
        return price;
    }

    public int getProductNumber() {
        return productNumber;
    }
}

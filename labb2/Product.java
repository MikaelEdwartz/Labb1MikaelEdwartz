package se.iths.labborationer.labb2;

public class Product {
    private final ProductCategory category;
    private final String product;
    private final int price;
    private final int productNumber;

    public Product(ProductCategory category, String product, int price, int productNumber) {
        this.category = category;
        this.product = product;
        this.price = price;
        this.productNumber = productNumber;
    }

    public ProductCategory getCategory() {
        return category;
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

    @Override
    public String toString() {
        return
                "category=" + category +
                ", product=" + product +
                ", price=" + price +
                ", productNumber=" + productNumber;
    }
}

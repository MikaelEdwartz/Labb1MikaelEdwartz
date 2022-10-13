package se.iths.labborationer.labb2MikaelEdwartz.Products;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.function.Predicate;

public final class Product implements Predicate<Product> {
    private final ProductCategory category;
    private final String product;
    private final BigDecimal price;
    private final int productNumber;

    public Product(ProductCategory category, String product, BigDecimal price, int productNumber) {
        this.category = category;
        this.product = product;
        this.price = price;
        this.productNumber = productNumber;
    }

    public ProductCategory category() {
        return category;
    }

    public String product() {
        return product;
    }

    public BigDecimal price() {
        return price;
    }

    public int productNumber() {
        return productNumber;
    }

    public boolean matchingEanCode(int productNumber) {
        return this.productNumber == productNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product1 = (Product) o;
        if (productNumber != product1.productNumber) return false;
        if (!Objects.equals(category, product1.category)) return false;
        if (!Objects.equals(product, product1.product)) return false;
        return Objects.equals(price, product1.price);
    }

    @Override
    public int hashCode() {
        int result = category != null ? category.hashCode() : 0;
        result = 31 * result + (product != null ? product.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + productNumber;
        return result;
    }

    @Override
    public boolean test(Product product) {
        return false;
    }

    public String formattedString(String item, boolean nrBeforeProduct){
        int length = item.length();
        if(nrBeforeProduct)
            return printFirstTab(item, length);
        else
            return printTab(item, length);
    }

    private String printFirstTab(String item, int length) {
        if (length >=6)
            return item + printTab(2);
        return item + printTab(3);
    }

    private String printTab(String item, int length) {
        if (length >= 14)
            return item + printTab(1);
        if (length >= 8)
            return item + printTab(2);
        return item + printTab(3);
    }

    private String printTab(int nrOfTabs){
        return "\t".repeat(Math.max(0, nrOfTabs));
    }

    @Override
    public String toString() {
        return formattedString(category.category(), false)
                + "" + formattedString(product, false)+ price
                +   "kr "+ printTab(1) + productNumber + printTab(1);
    }

    public String toStringCustomer(){
        return formattedString(category.category(), true)
                + "" + formattedString(product, false)+ price
                +   "kr "+ printTab(2) + productNumber + printTab(2);
    }
}

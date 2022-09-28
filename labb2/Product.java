package se.iths.labborationer.labb2;

import java.math.BigDecimal;

public record Product(ProductCategory category, String product, BigDecimal price, int productNumber) {
    public boolean matchingEanCode(int productNumber) {
        return this.productNumber == productNumber;
    }
    public String toString(){
        return category + ", " + product + ", "  + price +   ", " + productNumber;
    }
}

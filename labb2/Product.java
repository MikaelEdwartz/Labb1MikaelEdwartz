package se.iths.labborationer.labb2;

import java.math.BigDecimal;
import java.util.Objects;

public record Product(ProductCategory category, String product, BigDecimal price, int productNumber) {
}

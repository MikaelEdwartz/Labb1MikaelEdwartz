package se.iths.labborationer.labb2MikaelEdwartz.Discount;

import java.math.BigDecimal;
import java.util.function.UnaryOperator;

import static java.math.BigDecimal.valueOf;

public interface Discounter extends UnaryOperator<BigDecimal> {
    BigDecimal HIGHEST_DISCOUNT = valueOf(2000);
    BigDecimal LOWEST_DISCOUNT = valueOf(1000);


    static BigDecimal applyDiscount(BigDecimal amount){
        if (amount.compareTo(LOWEST_DISCOUNT) > 0 && amount.compareTo(HIGHEST_DISCOUNT) < 0)
            return new TenPercent().apply(amount);
        else if (amount.compareTo(HIGHEST_DISCOUNT) > 0)
            return new TwentyPercent().apply(amount);
        else
            return amount;
    }
}

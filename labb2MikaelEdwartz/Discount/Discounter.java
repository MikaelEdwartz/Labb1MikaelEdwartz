package se.iths.labborationer.labb2MikaelEdwartz.Discount;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

import static java.math.BigDecimal.valueOf;

public interface Discounter extends UnaryOperator<BigDecimal> {
    BigDecimal HIGHEST_DISCOUNT = valueOf(2000);
    BigDecimal LOWEST_DISCOUNT = valueOf(1000);


    static BigDecimal applyDiscount(BigDecimal amount) {
        return List.of(new TenPercent(), new TwentyPercent())
                .stream()
                .map(d -> d.apply(amount))
                .reduce(BigDecimal::min)
                .get();

    }
}

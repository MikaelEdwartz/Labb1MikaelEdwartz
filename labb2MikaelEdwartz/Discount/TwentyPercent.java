package se.iths.labborationer.labb2MikaelEdwartz.Discount;

import java.math.BigDecimal;

public class TwentyPercent implements Discounter{

    @Override
    public BigDecimal apply(BigDecimal amount){
        if (amount.compareTo(HIGHEST_DISCOUNT) > 0)
            return amount.multiply(BigDecimal.valueOf(0.8));
        else
            return amount;
    }
}

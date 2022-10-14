package se.iths.labborationer.labb2MikaelEdwartz.Discount;

import java.math.BigDecimal;

public class TenPercent implements Discounter{

    @Override
    public BigDecimal apply(BigDecimal amount){
        if (amount.compareTo(LOWEST_DISCOUNT) > 0 && amount.compareTo(HIGHEST_DISCOUNT) < 0)
             return amount.multiply(BigDecimal.valueOf(0.9));
        else
            return amount;
    }
}

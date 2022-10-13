package se.iths.labborationer.labb2MikaelEdwartz.Discount;

import java.math.BigDecimal;

public class TenPercent implements Discounter{


    @Override
    public BigDecimal apply(BigDecimal amount){
        return amount.multiply(BigDecimal.valueOf(0.9));
    }
}

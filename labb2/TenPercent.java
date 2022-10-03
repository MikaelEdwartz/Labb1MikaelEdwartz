package se.iths.labborationer.labb2;

import java.math.BigDecimal;

public class TenPercent implements Discounter{

    @Override
    public BigDecimal apply(BigDecimal amount){
        return amount.multiply(BigDecimal.valueOf(0.9));
    }
}

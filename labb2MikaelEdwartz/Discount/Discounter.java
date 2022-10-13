package se.iths.labborationer.labb2MikaelEdwartz.Discount;

import java.math.BigDecimal;
import java.util.function.UnaryOperator;

public interface Discounter extends UnaryOperator<BigDecimal> {

    default Discounter discount (Discounter disc){
        return price -> disc.apply(this.apply(price));
    }

}

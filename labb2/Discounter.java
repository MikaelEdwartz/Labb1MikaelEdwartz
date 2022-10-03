package se.iths.labborationer.labb2;

import java.math.BigDecimal;
import java.util.function.UnaryOperator;

public interface Discounter extends UnaryOperator<BigDecimal> {

    default Discounter(){
        return amount -> amount.multiply(BigDecimal.valueOf(0.9));
    }
}

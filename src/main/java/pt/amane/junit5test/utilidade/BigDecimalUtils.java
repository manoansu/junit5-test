package pt.amane.junit5test.utilidade;

import java.math.BigDecimal;

public class BigDecimalUtils {

    public static boolean iguais(BigDecimal x, BigDecimal y) {
        return x.compareTo(y) == 0;
    }

}

package helpers;

import com.google.common.base.CharMatcher;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

public class PriceHelper {

    public BigInteger stringToBigInt(String textInput) {
        String digits = CharMatcher.inRange('0', '9').retainFrom(textInput);
        return BigInteger.valueOf(Integer.parseInt(digits));
    }

    public List<BigInteger> extractIntegers(final List<String> allPrices) {
        return allPrices
                .stream()
                .map(this::stringToBigInt)
                .collect(Collectors.toList());
    }
}

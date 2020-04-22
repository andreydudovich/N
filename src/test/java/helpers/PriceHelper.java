package helpers;

import com.google.common.base.CharMatcher;
import lombok.extern.java.Log;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Log
public class PriceHelper {

    public BigInteger stringToBigInt(String textInput) {
        log.info("Converting String to BigInteger.");
        String digits = CharMatcher.inRange('0', '9').retainFrom(textInput);
        return BigInteger.valueOf(Integer.parseInt(digits));
    }

    public List<BigInteger> extractIntegers(final List<String> allPrices) {
        log.info("Extracting BigInteger");
        return allPrices
                .stream()
                .map(this::stringToBigInt)
                .collect(Collectors.toList());
    }
}

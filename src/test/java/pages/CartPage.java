package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import helpers.PriceHelper;
import io.qameta.allure.Step;
import lombok.extern.java.Log;
import org.assertj.core.api.Assertions;

import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigInteger;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.visible;

@Log
public class CartPage {

    private static final String HISTORY_PAGE = "https://www.rdveikals.lv/cart/lv/";
    private static final SelenideElement LOGO = $(".btn--link");
    private static final SelenideElement TOTAL_SUM = $("#total_products_num_price");
    private static final ElementsCollection PRICES_OF_PRODUCT = $$(".product__info__part > .price");
    private static final ElementsCollection CART_PRODUCT_LIST = $$(".cart-product-list > .product");
    private static final ElementsCollection REMOVE_PRODUCT_BUTTON = $$(".product__controls--small > a");

    @Step("Open Cart page")
    public CartPage open() {
        log.info("Cart page opening");
        Selenide.open(HISTORY_PAGE);
        LOGO.shouldBe(visible);
        return this;
    }

    @Step("Get list of prices in Cart page")
    public List<String> getAllPrices() {
        log.info("Getting prices of products");
        return PRICES_OF_PRODUCT
                .stream()
                .map(SelenideElement::getText)
                .collect(Collectors.toList());
    }

    @Step("Calculate sum of all products in Cart page")
    public BigInteger calculateSumOfAllProductsInCart() {
        log.info("Calculating sum of all products in Cart page");
        return new PriceHelper()
                .extractIntegers(getAllPrices())
                .stream()
                .reduce(BigInteger.ZERO, BigInteger::add);
    }

    @Step("Assert that Total sum and sum of all products in Cart page are the same")
    public void assertTotalSumIsCorrect() {
        BigInteger totalSum = new PriceHelper().stringToBigInt(TOTAL_SUM.getText());
        BigInteger sumOfAllProducts = calculateSumOfAllProductsInCart();

        assertThat(sumOfAllProducts)
                .withFailMessage("Total sum mismatch:" +
                        "\n Actual result:   " + sumOfAllProducts +
                        "\n Expected result: " + totalSum)
                .isEqualTo(totalSum);
    }

    @Step("Assert that Cart product count is correct")
    public CartPage assertCartProductsCountIsCorrect(int expectedProductCount) {
        Assertions.assertThat(expectedProductCount)
                .withFailMessage("Cart product count mismatch:" +
                        "\n Actual result:   " + CART_PRODUCT_LIST.size() +
                        "\n Expected result: " + expectedProductCount)
                .isEqualTo(CART_PRODUCT_LIST.size());
        return this;
    }

    private CartPage removeOneRandomProduct() {
        REMOVE_PRODUCT_BUTTON
                .get(getRandomProductFromCart())
                .scrollIntoView("{block: \"center\"}")
                .shouldBe(visible).click();
        return this;
    }

    @Step("Remove random product(s)")
    public CartPage removeFewRandomProducts(int productAmount) {
        if (productAmount > 0) {
            for (int i = 1; i <= productAmount; i++) {
                log.info("Removing random product");
                removeOneRandomProduct();
                sleep(3000); //Website removing products with delay
            }
        } else {
            throw new InvalidParameterException("Product amount should be > 0");
        }
        return this;
    }

    public int getRandomProductFromCart() {
        Random random = new Random();
        return random.nextInt(CART_PRODUCT_LIST.size());
    }
}

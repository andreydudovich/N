package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import io.qameta.allure.Step;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.InvalidParameterException;
import java.util.Random;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.Math.abs;

@Log
public class HomePage {
    private static final String RD_MAIN_PAGE = "https://www.rdveikals.lv";
    private static final SelenideElement LOGO = $(".btn--link");
    private static final SelenideElement ADD_TO_CART_BTN = $(".btn--280");
    private static final ElementsCollection PRODUCTS = $$(".js-touch-hover");

    @Step("Open Home page")
    public HomePage open() {
        log.info("Home page opening");
        Selenide.open(RD_MAIN_PAGE);
        LOGO.shouldBe(visible);
        return this;
    }

    @Step("Open Laptop category")
    public HomePage openLaptopCategory() {
        String LAPTOP_CATEGORY_RANDOM_FROM_40_PAGES = "/categories/ru/150/sort/1/filter/0_0_0_0" +
                "/page/" + (new Random().nextInt(40) + 1)  + "/Datortehnika.html";
        Selenide.open(RD_MAIN_PAGE + LAPTOP_CATEGORY_RANDOM_FROM_40_PAGES);
        LOGO.shouldBe(visible);
        return this;
    }

    private HomePage clickOneRandomProduct(int number) {
        openLaptopCategory();
        PRODUCTS.get(abs(getRandomProduct() - number))
                .shouldBe(visible).click();
        return this;
    }

    @Step("Click on random product(s)")
    public HomePage clickRandomProducts(int productAmount) {
        if (productAmount > 0) {
            for (int i = 1; i <= productAmount; i++) {
                log.info("Clicking on random product");
                clickOneRandomProduct(productAmount);
            }
        } else {
            throw new InvalidParameterException("Product amount should be > 0");
        }
        return this;
    }

    private HomePage addToCartOneRandomProduct(int number) {
        openLaptopCategory().
                clickOneRandomProduct(number);
        ADD_TO_CART_BTN.scrollIntoView("{block: \"center\"}").click();
        return this;
    }

    @Step("Add to Cart random product(s)")
    public HomePage addToCartRandomProducts(int productAmount) {
        if (productAmount > 0) {
            for (int i = 1; i <= productAmount; i++) {
                log.info("Adding to cart random product");
                addToCartOneRandomProduct(productAmount);
            }
        } else {
            throw new InvalidParameterException("Product amount should be > 0");
        }
        return this;
    }

    public int getRandomProduct() {
        Random random = new Random();
        return random.nextInt(PRODUCTS.size());
    }
}


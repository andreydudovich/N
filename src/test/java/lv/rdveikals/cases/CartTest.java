package lv.rdveikals.cases;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import lombok.extern.java.Log;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import lv.rdveikals.pages.CartPage;
import lv.rdveikals.pages.HomePage;
import org.junit.jupiter.api.Test;
import lv.rdveikals.utils.TestResultLoggerExtension;

import static com.codeborne.selenide.Selenide.closeWindow;

@Log
@ExtendWith(TestResultLoggerExtension.class)
public class CartTest {
    @AfterEach
    public void closeBrowser() {
        log.info("Closing browser");
        closeWindow();
    }

    @Test
    @Story("Total sum in Cart page after adding few random products")
    @Description("Task 2\n" +
            "1. Open the home page\n" +
            "2. Add any 5 different products to the cart\n" +
            "3. Check that total sum is correct")
    public void cartSumTest() {
        new HomePage()
                .open()
                .addToCartRandomProducts(5);

        new CartPage()
                .open()
                .assertTotalSumIsCorrect();
    }

    @Test
    @Story("Total sum in Cart page after adding and removing few random products")
    @Description("Task 3\n" +
            "1. Open the home page\n" +
            "2. Add any 5 different products to the cart\n" +
            "3. Remove any 2 different products\n" +
            "4. Check that 2 products are removed form the cart and total sum is correct")
    public void cartSumAfterChangedTest() {
        new HomePage()
                .open()
                .addToCartRandomProducts(5);

        new CartPage()
                .open()
                .assertCartProductsCountIsCorrect(5)
                .removeFewRandomProducts(2)
                .assertCartProductsCountIsCorrect(3)
                .assertTotalSumIsCorrect();
    }
}

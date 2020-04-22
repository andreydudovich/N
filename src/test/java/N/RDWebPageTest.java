package N;

import com.codeborne.selenide.junit5.ScreenShooterExtension;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import lombok.extern.java.Log;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.CartPage;
import pages.HistoryPage;
import pages.HomePage;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.closeWindow;

@Log
@ExtendWith({ScreenShooterExtension.class})
public class RDWebPageTest {
    @AfterEach
    public void closeBrowser() {
        closeWindow();
    }

    @Test
    @Story("Count of few random products in History page")
    @Description("Task 1\n" +
            "1. Open the home page\n" +
            "2. Open any 2 different products\n" +
            "3. Check that 2 products are visible in the history views")
    public void historyProductTest() {
        new HomePage()
                .open()
                .clickRandomProducts(2);

        new HistoryPage()
                .open()
                .assertHistoryProductsCountIsCorrect(2);
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

    @Test
    @Story("Failed test. Count of few random products in History page")
    @Description("Task 4. Failed test.\n" +
            "1. Open the home page\n" +
            "2. Open any 2 different products\n" +
            "3. Wrong expectation: Check that 1 products are visible in the history views")
    public void failedHistoryProductTest() {
        new HomePage()
                .open()
                .clickRandomProducts(2);

        new HistoryPage()
                .open()
                .assertHistoryProductsCountIsCorrect(1);
    }
}

package N;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import pages.CartPage;
import pages.HistoryPage;
import pages.HomePage;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.closeWindow;

public class RDWebPageTest {

    @AfterEach
    public void closeBrowser(){
        closeWindow();
    }

    @Story("Count of few random products in History page")
    @Description("Task 1\n" +
            "1. Open the home page\n" +
            "2. Open any 2 different products\n" +
            "3. Check that products are visible in the history views")
    @Test
    public void historyProductTest() {
        new HomePage()
                .open()
                .clickRandomProducts(2);

        new HistoryPage()
                .open()
                .assertHistoryProductsCountIsCorrect(2);
    }

    @Story("Total sum in Cart page after adding few random products")
    @Description("Task 2\n" +
            "1. Open the home page\n" +
            "2. Add any 5 different products to the cart\n" +
            "3. Check that total sum is correct")
    @Test
    public void cartSumTest() {
        new HomePage()
                .open()
                .addToCartRandomProducts(5);

        new CartPage()
                .open()
                .assertTotalSumIsCorrect();
    }

    @Story("Total sum in Cart page after adding and removing few random products")
    @Description("Task 3\n" +
            "1. Open the home page\n" +
            "2. Add any 5 different products to the cart\n" +
            "3. Remove any 2 different products\n" +
            "4. Check that products are removed form the cart and total sum is correct  ")
    @Test
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

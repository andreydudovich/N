package lv.rdveikals.cases;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import lombok.extern.java.Log;
import lv.rdveikals.pages.HistoryPage;
import lv.rdveikals.pages.HomePage;
import lv.rdveikals.utils.TestResultLoggerExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.closeWindow;

@Log
@ExtendWith(TestResultLoggerExtension.class)
public class HistoryViewTest {
    @AfterEach
    public void closeBrowser() {
        log.info("Closing browser");
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

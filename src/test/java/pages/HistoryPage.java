package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.java.Log;
import org.assertj.core.api.Assertions;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Log
public class HistoryPage {

    private static final String HISTORY_PAGE = "https://www.rdveikals.lv/recent_history/lv/";
    private static final SelenideElement LOGO = $(".btn--link");
    private static final ElementsCollection PRODUCTS = $$(".js-touch-hover");

    @Step("Open History page")
    public HistoryPage open() {
        log.info("History page opening");
        Selenide.open(HISTORY_PAGE);
        LOGO.shouldBe(visible);
        return this;
    }

    @Step("Check that product count in History page is correct")
    public void assertHistoryProductsCountIsCorrect(int expectedProductAmount) {
        Assertions.assertThat(expectedProductAmount)
                .withFailMessage("History product count mismatch:" +
                        "\n Actual result:   " + PRODUCTS.size() +
                        "\n Expected result: " + expectedProductAmount)
                .isEqualTo(PRODUCTS.size());
    }
}

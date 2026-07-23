package dev.valvar.orangehrm.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$;

/**
 * Страница "Моя информация".
 */
public class MyInfoPage {

    private static final Logger logger = LoggerFactory.getLogger(MyInfoPage.class);

    private final SelenideElement PERSONAL_DETAILS_HEADER = $("h6.orangehrm-main-title");

    /**
     * Проверить, что страница открыта.
     */
    @Step("Проверка, что страница открыта")
    public MyInfoPage verifyIsOpen() {
        logger.debug("Проверка, что страница открыта");

        PERSONAL_DETAILS_HEADER.shouldHave(Condition.text("Personal Details"));

        return this;
    }

}

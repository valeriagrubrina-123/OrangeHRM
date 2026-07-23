package dev.valvar.orangehrm.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

/**
 * Страница "Моя информация".
 */
public class MyInfoPage {

    private final SelenideElement PERSONAL_DETAILS_HEADER = $("h6.orangehrm-main-title");

    /**
     * Проверить, что страница открыта.
     */
    public MyInfoPage verifyIsOpen() {
        PERSONAL_DETAILS_HEADER.shouldHave(Condition.text("Personal Details"));

        return this;
    }

}

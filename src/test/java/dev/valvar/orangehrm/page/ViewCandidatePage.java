package dev.valvar.orangehrm.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

/**
 * Страница просмотра кандидата.
 */
public class ViewCandidatePage {

    private final SelenideElement PROFILE_HEADER = $("h6.orangehrm-main-title");

    @Step("Проверить, что страница просмотра кандидата открыта")
    public ViewCandidatePage verifyIsOpen() {
        PROFILE_HEADER.shouldHave(Condition.text("Application Stage"));

        return this;
    }

}
package dev.valvar.orangehrm.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$;

/**
 * Страница просмотра кандидата.
 */
public class ViewCandidatePage {

    private static final Logger logger = LoggerFactory.getLogger(ViewCandidatePage.class);

    private final SelenideElement PROFILE_HEADER = $("h6.orangehrm-main-title");

    /**
     * Проверить, что страница открыта.
     */
    @Step("Проверка, что страница открыта")
    public ViewCandidatePage verifyIsOpen() {
        logger.debug("Проверка, что страница открыта");

        PROFILE_HEADER.shouldHave(Condition.text("Application Stage"));

        return this;
    }

}
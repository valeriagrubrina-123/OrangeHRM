package dev.valvar.orangehrm.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Страница подбора персонала.
 */
public class RecruitmentPage {

    private static final Logger logger = LoggerFactory.getLogger(RecruitmentPage.class);

    private final SelenideElement CANDIDATE_NAME_INPUT = $x("//label[text()='Candidate Name']/parent::div/following-sibling::div//input[@placeholder='Type for hints...']");
    private final SelenideElement AUTOCOMPLETE_OPTION = $x("//div[@role='listbox']//div[@role='option']//span");
    private final SelenideElement SEARCH_BUTTON = $x("//button[normalize-space()='Search']");
    private final SelenideElement TABLE_BODY = $(".oxd-table-body");
    private final SelenideElement EYE_ICON = $(".bi-eye-fill");

    /**
     * Осуществить поиск кандидата по имени.
     *
     * @param name имя.
     */
    @Step("Поиск кандидата по имени")
    public RecruitmentPage searchCandidateByName(String name) {
        logger.debug("Поиск кандидата по имени");

        CANDIDATE_NAME_INPUT.setValue(name);
        AUTOCOMPLETE_OPTION.shouldBe(Condition.visible).click();
        SEARCH_BUTTON.click();

        return this;
    }

    /**
     * Проверить, что кандидат найден.
     *
     * @param name имя.
     */
    @Step("Проверка, что кандидат найден")
    public RecruitmentPage verifyCandidateFound(String name) {
        logger.debug("Проверка, что кандидат найден");

        TABLE_BODY.shouldHave(Condition.text(name));

        return this;
    }

    /**
     * Кликнуть на иконку просмотра кандидата.
     */
    @Step("Клик на иконку просмотра кандидата")
    public ViewCandidatePage clickViewCandidateIcon() {
        logger.debug("Клик на иконку просмотра кандидата");

        EYE_ICON.shouldBe(Condition.visible).click();

        return new ViewCandidatePage();
    }

}
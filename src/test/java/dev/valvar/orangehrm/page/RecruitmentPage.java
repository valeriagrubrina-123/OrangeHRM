package dev.valvar.orangehrm.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Страница подбора персонала.
 */
public class RecruitmentPage {

    private final SelenideElement CANDIDATE_NAME_INPUT = $x("//label[text()='Candidate Name']/parent::div/following-sibling::div//input[@placeholder='Type for hints...']");
    private final SelenideElement AUTOCOMPLETE_OPTION = $x("//div[@role='listbox']//div[@role='option']//span");
    private final SelenideElement SEARCH_BUTTON = $x("//button[normalize-space()='Search']");
    private final SelenideElement TABLE_BODY = $(".oxd-table-body");
    private final SelenideElement EYE_ICON = $(".bi-eye-fill");

    @Step("Осуществить поиск кандидата по имени '{name}'")
    public RecruitmentPage searchCandidateByName(String name) {
        CANDIDATE_NAME_INPUT.setValue(name);
        AUTOCOMPLETE_OPTION.shouldBe(Condition.visible).click();
        SEARCH_BUTTON.click();

        return this;
    }

    @Step("Проверить, что кандидат '{name}' найден в результатах поиска")
    public RecruitmentPage verifyCandidateFound(String name) {
        TABLE_BODY.shouldHave(Condition.text(name));

        return this;
    }

    @Step("Кликнуть на иконку просмотра карточки кандидата")
    public ViewCandidatePage clickViewCandidateIcon() {
        EYE_ICON.shouldBe(Condition.visible).click();

        return new ViewCandidatePage();
    }

}
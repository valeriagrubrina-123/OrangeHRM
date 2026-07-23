package dev.valvar.orangehrm.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Страница редактирования сотрудника.
 */
public class EditEmployeePage {

    private final SelenideElement MIDDLE_NAME_INPUT = $("input[name='middleName']");
    private final SelenideElement SAVE_BUTTON = $x("//h6[text()='Personal Details']/following-sibling::form//button[@type='submit']");
    private final SelenideElement SUCCESS_TOAST = $(".oxd-toast--success");

    /**
     * Изменить отчество.
     *
     * @param middleName отчество.
     */
    public EditEmployeePage editMiddleName(String middleName) {
        MIDDLE_NAME_INPUT.setValue(middleName);

        return this;
    }

    /**
     * Сохранить.
     */
    public EditEmployeePage save() {
        SAVE_BUTTON.click();

        return this;
    }

    /**
     * Проверить, что сохранение прошло успешно.
     */
    public EditEmployeePage verifySavedSuccessfully() {
        SUCCESS_TOAST.shouldBe(Condition.visible, Duration.ofSeconds(10));

        return this;
    }

}

package dev.valvar.orangehrm.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

/**
 * Страница добавления сотрудника.
 */
public class AddEmployeePage {

    private final SelenideElement FIRST_NAME_INPUT = $("input[name='firstName']");
    private final SelenideElement LAST_NAME_INPUT = $("input[name='lastName']");
    private final SelenideElement SAVE_BUTTON = $("button[type='submit']");
    private final SelenideElement SUCCESS_TOAST = $(".oxd-toast--success");

    @Step("Заполнить данные формы сотрудника: имя {firstName}, фамилия {lastName}")
    public AddEmployeePage fillForm(
            String firstName,
            String lastName
    ) {
        FIRST_NAME_INPUT.setValue(firstName);
        LAST_NAME_INPUT.setValue(lastName);

        return this;
    }

    @Step("Кликнуть кнопку сохранить")
    public AddEmployeePage save() {
        SAVE_BUTTON.click();

        return this;
    }

    @Step("Проверить успешное сохранение сотрудника)")
    public AddEmployeePage verifySavedSuccessfully() {
        SUCCESS_TOAST.shouldBe(Condition.visible, Duration.ofSeconds(10));

        return this;
    }

}

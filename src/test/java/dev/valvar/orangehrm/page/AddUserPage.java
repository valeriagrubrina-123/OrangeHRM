package dev.valvar.orangehrm.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import dev.valvar.orangehrm.OrangeHrmUtils;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

/**
 * Страница добавления пользователя.
 */
public class AddUserPage {

    private final SelenideElement EMPLOYEE_NAME_INPUT = $x("//input[@placeholder='Type for hints...']");
    private final SelenideElement EMPLOYEE_NAME_AUTOCOMPLETE_OPTION = $x("//div[@role='listbox']//div[@role='option']//span");
    private final SelenideElement USERNAME_INPUT = $x("//label[text()='Username']/parent::div/following-sibling::div/input");
    private final SelenideElement PASSWORD_INPUT = $x("//label[text()='Password']/parent::div/following-sibling::div/input");
    private final SelenideElement CONFIRM_PASSWORD_INPUT = $x("//label[text()='Confirm Password']/parent::div/following-sibling::div/input");
    private final SelenideElement SAVE_BUTTON = $x("//button[@type='submit']");
    private final SelenideElement SUCCESS_TOAST = $x("//div[@id='oxd-toaster_1']");

    /**
     * Заполнить данные формы.
     *
     * @param userRole         роль пользователя.
     * @param employeeFullName имя/фамилия сотрудника.
     * @param status           статус.
     * @param username         имя пользователя.
     * @param password         пароль.
     */
    @Step("Заполнить данные формы")
    public AddUserPage fillForm(
            String userRole,
            String employeeFullName,
            String status,
            String username,
            String password
    ) {
        OrangeHrmUtils.selectDropdownOption("User Role", userRole);
        EMPLOYEE_NAME_INPUT.setValue(employeeFullName);
        EMPLOYEE_NAME_AUTOCOMPLETE_OPTION.shouldBe(Condition.visible).click();
        OrangeHrmUtils.selectDropdownOption("Status", status);
        USERNAME_INPUT.setValue(username);
        PASSWORD_INPUT.setValue(password);
        CONFIRM_PASSWORD_INPUT.setValue(password);

        return this;
    }

    /**
     * Сохранить.
     */
    @Step("Сохранить")
    public AddUserPage save() {
        SAVE_BUTTON.click();

        return this;
    }

    /**
     * Проверить, что сохранение прошло успешно.
     */
    @Step("Проверить, что сохранение прошло успешно")
    public AddUserPage verifySavedSuccessfully() {
        SUCCESS_TOAST.shouldBe(Condition.visible, Duration.ofSeconds(10));

        return this;
    }

}

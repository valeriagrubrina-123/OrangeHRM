package dev.valvar.orangehrm.page;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Страница авторизации.
 */
public class LoginPage {

    private final SelenideElement USERNAME_INPUT = $("input[name='username']");
    private final SelenideElement PASSWORD_INPUT = $("input[name='password']");
    private final SelenideElement LOGIN_BUTTON = $("button[type='submit']");
    private final SelenideElement ERROR_MESSAGE = $(".oxd-alert-content-text");
    private final ElementsCollection REQUIRED_VALIDATION_MESSAGES = $$(".oxd-input-field-error-message");

    /**
     * Открыть страницу.
     */
    public LoginPage open() {
        Selenide.open("/web/index.php/auth/login");

        return this;
    }

    /**
     * Авторизоваться.
     *
     * @param username имя пользователя.
     * @param password пароль.
     */
    public DashboardPage login(
            String username,
            String password
    ) {
        USERNAME_INPUT.setValue(username);
        PASSWORD_INPUT.setValue(password);
        LOGIN_BUTTON.click();

        return new DashboardPage();
    }

    @Step("Авторизоваться под пользователем '{username}' с ожиданием ошибки")
    public LoginPage loginExpectingError(
            String username,
            String password
    ) {
        USERNAME_INPUT.setValue(username);
        PASSWORD_INPUT.setValue(password);
        LOGIN_BUTTON.click();

        return this;
    }

    @Step("Проверить, что появилось сообщение об ошибке: '{expectedMessage}'")
    public LoginPage verifyErrorMessage(String expectedMessage) {
        ERROR_MESSAGE.shouldHave(Condition.text(expectedMessage));

        return this;
    }

    @Step("Проверить появление сообщений валидации об обязательности заполнения полей")
    public LoginPage verifyRequiredValidationMessages() {
        REQUIRED_VALIDATION_MESSAGES.shouldHave(CollectionCondition.size(2));
        REQUIRED_VALIDATION_MESSAGES.get(0).shouldHave(Condition.text("Required"));
        REQUIRED_VALIDATION_MESSAGES.get(1).shouldHave(Condition.text("Required"));

        return this;
    }

    @Step("Проверить, что страница авторизации открыта")
    public LoginPage verifyIsOpen() {
        LOGIN_BUTTON.shouldBe(Condition.visible);

        return this;
    }

}

package dev.valvar.orangehrm.page;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Страница авторизации.
 */
public class LoginPage {

    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

    private final SelenideElement USERNAME_INPUT = $("input[name='username']");
    private final SelenideElement PASSWORD_INPUT = $("input[name='password']");
    private final SelenideElement LOGIN_BUTTON = $("button[type='submit']");
    private final SelenideElement ERROR_MESSAGE = $(".oxd-alert-content-text");
    private final ElementsCollection REQUIRED_VALIDATION_MESSAGES = $$(".oxd-input-field-error-message");

    /**
     * Открыть страницу.
     */
    @Step("Открыть страницу")
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
    @Step("Авторизоваться")
    public DashboardPage login(
            String username,
            String password
    ) {
        USERNAME_INPUT.setValue(username);
        PASSWORD_INPUT.setValue(password);
        LOGIN_BUTTON.click();

        return new DashboardPage();
    }

    /**
     * Авторизоваться, но с ожиданием ошибки (без перехода на страницу панели управления).
     *
     * @param username имя пользователя.
     * @param password пароль.
     */
    @Step("Авторизоваться, но с ожиданием ошибки (без перехода на страницу панели управления)")
    public LoginPage loginExpectingError(
            String username,
            String password
    ) {
        USERNAME_INPUT.setValue(username);
        PASSWORD_INPUT.setValue(password);
        LOGIN_BUTTON.click();

        return this;
    }

    /**
     * Проверить сообщение ошибки.
     *
     * @param expectedMessage ожидаемое сообщение.
     */
    @Step("Проверить сообщение ошибки")
    public LoginPage verifyErrorMessage(String expectedMessage) {
        ERROR_MESSAGE.shouldHave(Condition.text(expectedMessage));

        return this;
    }

    /**
     * Проверить сообщения валидации на обязательность заполнения.
     */
    @Step("Проверить сообщения валидации на обязательность заполнения")
    public LoginPage verifyRequiredValidationMessages() {
        REQUIRED_VALIDATION_MESSAGES.shouldHave(CollectionCondition.size(2));
        REQUIRED_VALIDATION_MESSAGES.get(0).shouldHave(Condition.text("Required"));
        REQUIRED_VALIDATION_MESSAGES.get(1).shouldHave(Condition.text("Required"));

        return this;
    }

    /**
     * Проверить, что страница открыта.
     */
    @Step("Проверить, что страница открыта")
    public LoginPage verifyIsOpen() {
        LOGIN_BUTTON.shouldBe(Condition.visible);

        return this;
    }

}

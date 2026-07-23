package dev.valvar.orangehrm.test;

import dev.valvar.orangehrm.utils.ConfigReader;
import org.testng.annotations.Test;

@Test(description = "Авторизация")
public class AuthorizationTest extends BaseTest {

    @Test(description = "Успешная авторизация с валидными кредами (переход на страницу Dashboard)")
    public void successfulAuthorizationWithValidCredentialsTest() {
        loginPage
                .open()
                .login(username, password)
                .verifyIsOpen();
    }

    @Test(description = "Ошибка авторизации при неправильном пароле")
    public void authorizationErrorDueToIncorrectPasswordTest() {
        String password = ConfigReader.getProperty("invalidPassword");

        loginPage
                .open()
                .loginExpectingError(username, password)
                .verifyErrorMessage("Invalid credentials");
    }

    @Test(description = "Ошибка авторизации при пустых полях \"Имя пользователя\" и \"Пароль\"")
    public void authorizationErrorWithEmptyUsernameAndPasswordFieldsTest() {
        loginPage
                .open()
                .loginExpectingError("", "")
                .verifyRequiredValidationMessages();
    }

    @Test(description = "Успешный выход из системы")
    public void successfulLogoutTest() {
        loginPage
                .open()
                .login(username, password)
                .logout()
                .verifyIsOpen();
    }

    @Test(description = "Отображение виджетов на главной странице после авторизации")
    public void displayingWidgetsOnMainPageAfterAuthorizationTest() {
        loginPage
                .open()
                .login(username, password)
                .verifyWidgetsAreDisplayed();
    }

}

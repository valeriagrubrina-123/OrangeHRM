package dev.valvar.orangehrm.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$x;

/**
 * Страница администрирования.
 */
public class AdminPage {

    private static final Logger logger = LoggerFactory.getLogger(AdminPage.class);

    private final SelenideElement ADD_BUTTON = $x("//button[normalize-space()='Add']");
    private final SelenideElement SEARCH_USERNAME_INPUT = $x("//label[text()='Username']/parent::div/following-sibling::div/input");
    private final SelenideElement SEARCH_BUTTON = $x("//button[@type='submit']");
    private final SelenideElement TABLE_BODY = $x("//div[@class='oxd-table-body']");
    private final SelenideElement DELETE_ICON = $x("//i[contains(@class, 'bi-trash')]");
    private final SelenideElement CONFIRM_DELETE_BUTTON = $x("//button[normalize-space()='Yes, Delete']");

    /**
     * Кликнуть на кнопку "Добавить пользователя".
     */
    @Step("Клик на кнопку \"Добавить пользователя\"")
    public AddUserPage clickAddUserButton() {
        logger.debug("Клик на кнопку \"Добавить пользователя\"");

        ADD_BUTTON.shouldBe(Condition.visible).click();

        return new AddUserPage();
    }

    /**
     * Осуществить поиск пользователя по имени пользователя.
     *
     * @param username имя пользователя.
     */
    @Step("Поиск пользователя по имени пользователя")
    public AdminPage searchUserByUsername(String username) {
        logger.debug("Поиск пользователя по имени пользователя");

        SEARCH_USERNAME_INPUT.shouldBe(Condition.visible).setValue(username);
        SEARCH_BUTTON.click();
        TABLE_BODY.shouldBe(Condition.visible);

        return this;
    }

    /**
     * Проверить, что пользователь найден.
     *
     * @param username имя пользователя.
     */
    @Step("Проверка того, что пользователь найден")
    public AdminPage verifyUserFound(String username) {
        logger.debug("Проверка того, что пользователь найден");

        TABLE_BODY.shouldHave(Condition.text(username));

        return this;
    }

    /**
     * Удалить пользователя.
     */
    @Step("Удаление пользователя")
    public AdminPage deleteUser() {
        logger.debug("Удаление пользователя");

        DELETE_ICON.shouldBe(Condition.visible).click();
        CONFIRM_DELETE_BUTTON.shouldBe(Condition.visible).click();

        return this;
    }

    /**
     * Проверить, что пользователь не найден.
     *
     * @param username имя пользователя.
     */
    @Step("Проверка того, что пользователь не найден")
    public AdminPage verifyUserNotFound(String username) {
        logger.debug("Проверка того, что пользователь не найден");

        TABLE_BODY.shouldNotHave(Condition.text(username));

        return this;
    }

}

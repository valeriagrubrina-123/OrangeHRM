package dev.valvar.orangehrm.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

/**
 * Страница добавления сотрудника.
 */
public class AddEmployeePage {

    private static final Logger logger = LoggerFactory.getLogger(AddEmployeePage.class);

    private final SelenideElement FIRST_NAME_INPUT = $("input[name='firstName']");
    private final SelenideElement LAST_NAME_INPUT = $("input[name='lastName']");
    private final SelenideElement SAVE_BUTTON = $("button[type='submit']");
    private final SelenideElement SUCCESS_TOAST = $(".oxd-toast--success");

    /**
     * Заполнить данные формы.
     *
     * @param firstName имя.
     * @param lastName  фамилия.
     */
    @Step("Заполнение данных формы")
    public AddEmployeePage fillForm(
            String firstName,
            String lastName
    ) {
        logger.debug("Заполнение данных формы");

        FIRST_NAME_INPUT.setValue(firstName);
        LAST_NAME_INPUT.setValue(lastName);

        return this;
    }

    /**
     * Сохранить.
     */
    @Step("Сохранение")
    public AddEmployeePage save() {
        logger.debug("Сохранение");

        SAVE_BUTTON.click();

        return this;
    }

    /**
     * Проверить, что сохранение прошло успешно.
     */
    @Step("Проверка успешного сохранения")
    public AddEmployeePage verifySavedSuccessfully() {
        logger.debug("Проверка успешного сохранения");

        SUCCESS_TOAST.shouldBe(Condition.visible, Duration.ofSeconds(10));

        return this;
    }

}

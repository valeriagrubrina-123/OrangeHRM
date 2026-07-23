package dev.valvar.orangehrm.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Страница "PIM".
 */
public class PimPage {

    private static final Logger logger = LoggerFactory.getLogger(PimPage.class);

    private final SelenideElement ADD_BUTTON = $x("//button[normalize-space()='Add']");
    private final SelenideElement EMPLOYEE_NAME_INPUT = $x("//label[normalize-space()='Employee Name']/parent::div/following-sibling::div//input");
    private final SelenideElement SEARCH_BUTTON = $x("//button[normalize-space()='Search']");
    private final SelenideElement TABLE_BODY = $(".oxd-table-body");
    private final SelenideElement EDIT_ICON = $(".bi-pencil-fill");
    private final SelenideElement DELETE_ICON = $(".bi-trash");
    private final SelenideElement CONFIRM_DELETE_BUTTON = $(".oxd-button--label-danger");
    private final SelenideElement SUCCESS_TOAST = $(".oxd-toast--success");

    /**
     * Кликнуть на кнопку "Добавить сотрудника".
     */
    @Step("Клик на кнопку \"Добавить сотрудника\"")
    public AddEmployeePage clickAddEmployeeButton() {
        logger.debug("Клик на кнопку \"Добавить сотрудника\"");

        ADD_BUTTON.click();

        return new AddEmployeePage();
    }

    /**
     * Осуществить поиск сотрудника по имени/фамилии.
     *
     * @param fullName имя/фамилия.
     */
    @Step("Поиск сотрудника по имени/фамилии")
    public PimPage searchEmployeeByFullName(String fullName) {
        logger.debug("Поиск сотрудника по имени/фамилии");

        EMPLOYEE_NAME_INPUT.setValue(fullName);
        SEARCH_BUTTON.click();

        return this;
    }

    /**
     * Проверить, что сотрудник найден.
     *
     * @param firstName имя.
     * @param lastName  фамилия.
     */
    @Step("Проверка, что сотрудник найден")
    public PimPage verifyEmployeeFound(
            String firstName,
            String lastName
    ) {
        logger.debug("Проверка, что сотрудник найден");

        TABLE_BODY.shouldHave(Condition.text(firstName), Condition.text(lastName));

        return this;
    }

    /**
     * Кликнуть на иконку редактирования сотрудника.
     */
    @Step("Клик на иконку редактирования сотрудника")
    public EditEmployeePage clickEditEmployeeIcon() {
        logger.debug("Клик на иконку редактирования сотрудника");

        EDIT_ICON.shouldBe(Condition.visible).click();

        return new EditEmployeePage();
    }

    /**
     * Удалить сотрудника.
     */
    @Step("Удаление сотрудника")
    public PimPage deleteEmployee() {
        logger.debug("Удаление сотрудника");

        DELETE_ICON.shouldBe(Condition.visible).click();
        CONFIRM_DELETE_BUTTON.shouldBe(Condition.visible).click();
        SUCCESS_TOAST.shouldBe(Condition.visible);

        return this;
    }

    /**
     * Проверить, что сотрудник не найден.
     *
     * @param firstName имя.
     * @param lastName  фамилия.
     */
    @Step("Проверка, что сотрудник не найден")
    public PimPage verifyEmployeeNotFound(
            String firstName,
            String lastName
    ) {
        logger.debug("Проверка, что сотрудник не найден");

        TABLE_BODY.shouldNotHave(Condition.text(firstName), Condition.text(lastName));

        return this;
    }

}

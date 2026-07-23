package dev.valvar.orangehrm.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Страница "PIM".
 */
public class PimPage {

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
    @Step("Кликнуть на кнопку \"Добавить сотрудника\"")
    public AddEmployeePage clickAddEmployeeButton() {
        ADD_BUTTON.click();

        return new AddEmployeePage();
    }

    /**
     * Осуществить поиск сотрудника по имени/фамилии.
     *
     * @param fullName имя/фамилия.
     */
    @Step("Осуществить поиск сотрудника по имени/фамилии")
    public PimPage searchEmployeeByFullName(String fullName) {
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
    @Step("Проверить, что сотрудник найден")
    public PimPage verifyEmployeeFound(
            String firstName,
            String lastName
    ) {
        TABLE_BODY.shouldHave(Condition.text(firstName), Condition.text(lastName));

        return this;
    }

    /**
     * Кликнуть на иконку редактирования сотрудника.
     */
    @Step("Кликнуть на иконку редактирования сотрудника")
    public EditEmployeePage clickEditEmployeeIcon() {
        EDIT_ICON.shouldBe(Condition.visible).click();

        return new EditEmployeePage();
    }

    /**
     * Удалить сотрудника.
     */
    @Step("Удалить сотрудника")
    public PimPage deleteEmployee() {
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
    @Step("Проверить, что сотрудник не найден")
    public PimPage verifyEmployeeNotFound(
            String firstName,
            String lastName
    ) {
        TABLE_BODY.shouldNotHave(Condition.text(firstName), Condition.text(lastName));

        return this;
    }

}

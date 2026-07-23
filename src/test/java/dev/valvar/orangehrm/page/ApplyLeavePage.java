package dev.valvar.orangehrm.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import dev.valvar.orangehrm.OrangeHrmUtils;
import io.qameta.allure.Step;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Страница добавления отпуска.
 */
public class ApplyLeavePage {

    private final SelenideElement FROM_DATE_INPUT = $x("//label[text()='From Date']/parent::div/following-sibling::div//input");
    private final SelenideElement APPLY_BUTTON = $("button[type='submit']");
    private final SelenideElement SUCCESS_TOAST = $(".oxd-toast--success");

    /**
     * Добавить.
     *
     * @param leaveType тип отпуска.
     * @param fromDate  дата.
     */
    @Step("Добавить")
    public ApplyLeavePage apply(
            String leaveType,
            LocalDate fromDate
    ) {
        OrangeHrmUtils.selectDropdownOption("Leave Type", leaveType);
        FROM_DATE_INPUT.setValue(fromDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        $("h6").click(); // Кликаем где-нибудь снаружи, чтобы скрыть всплывающий календарь.
        APPLY_BUTTON.click();

        return this;
    }

    /**
     * Проверить, что добавление прошло успешно.
     */
    @Step("Проверить, что добавление прошло успешно")
    public ApplyLeavePage verifyAppliedSuccessfully() {
        SUCCESS_TOAST.shouldBe(Condition.visible, Duration.ofSeconds(10));

        return this;
    }

}
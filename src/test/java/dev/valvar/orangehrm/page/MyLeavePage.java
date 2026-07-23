package dev.valvar.orangehrm.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;

/**
 * Страница "Мой отпуск".
 */
public class MyLeavePage {

    private final SelenideElement TABLE_BODY = $(".oxd-table-body");

    /**
     * Проверить, что отпуск существует.
     *
     * @param expectedDate дата отпуска.
     */
    @Step("Проверить, что отпуск существует")
    public MyLeavePage verifyLeaveExists(LocalDate expectedDate) {
        // Формат даты нестабилен, может быть какой угодно. Так как требования неизвестны - проверяем все варианты.
        TABLE_BODY.shouldHave(Condition.oneOfTexts(
                expectedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                expectedDate.format(DateTimeFormatter.ofPattern("yyyy-dd-MM")),
                expectedDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                expectedDate.format(DateTimeFormatter.ofPattern("MM-dd-yyyy")),
                expectedDate.format(DateTimeFormatter.ofPattern("dd-yyyy-MM")),
                expectedDate.format(DateTimeFormatter.ofPattern("MM-yyyy-dd"))
        ));

        return this;
    }

}
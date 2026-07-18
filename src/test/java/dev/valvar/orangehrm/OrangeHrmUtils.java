package dev.valvar.orangehrm;

import com.codeborne.selenide.Condition;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

import static com.codeborne.selenide.Selenide.$x;

/**
 * Утилиты для работы с сайтом.
 */
public final class OrangeHrmUtils {

    private OrangeHrmUtils() {
    }

    /**
     * Выбрать значение в выпадашке.
     *
     * @param labelText "наименование" выпадашки.
     * @param option    значение.
     */
    public static void selectDropdownOption(
            String labelText,
            String option
    ) {
        $x(String.format("//label[text()='%s']/parent::div/following-sibling::div//div[contains(@class, 'oxd-select-text')]", labelText))
                .shouldBe(Condition.visible)
                .click();

        $x(String.format("//div[@role='listbox']//span[text()='%s']", option))
                .shouldBe(Condition.visible)
                .click();
    }

    /**
     * Сгенерировать рандомную дату в определенном диапазоне.
     *
     * @param startInclusive дата (с, включительно).
     * @param endInclusive   дата (по, включительно).
     * @return дата.
     */
    public static LocalDate generateRandomDate(
            LocalDate startInclusive,
            LocalDate endInclusive
    ) {
        long startEpochDay = startInclusive.toEpochDay();
        long endEpochDay = endInclusive.toEpochDay();

        long randomEpochDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay + 1);

        return LocalDate.ofEpochDay(randomEpochDay);
    }

}

package dev.valvar.orangehrm.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$x;

/**
 * Страница отпуска.
 */
public class LeavePage {

    private static final Logger logger = LoggerFactory.getLogger(LeavePage.class);

    private final SelenideElement APPLY_MENU = $x("//a[normalize-space()='Apply']");
    private final SelenideElement MY_LEAVE_MENU = $x("//a[normalize-space()='My Leave']");

    /**
     * Открыть страницу добавления отпуска.
     */
    @Step("Открыть страницу добавления отпуска")
    public ApplyLeavePage openApplyLeavePage() {
        APPLY_MENU.shouldBe(Condition.visible).click();

        return new ApplyLeavePage();
    }

    /**
     * Открыть страницу "Мой отпуск".
     */
    @Step("Открыть страницу \"Мой отпуск\"")
    public MyLeavePage openMyLeavePage() {
        MY_LEAVE_MENU.shouldBe(Condition.visible).click();

        return new MyLeavePage();
    }

}
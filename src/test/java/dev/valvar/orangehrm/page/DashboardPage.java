package dev.valvar.orangehrm.page;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Страница панели управления.
 */
public class DashboardPage {

    private static final Logger logger = LoggerFactory.getLogger(DashboardPage.class);

    private final SelenideElement HEADER_TITLE = $(".oxd-topbar-header-breadcrumb-module");
    private final SelenideElement USER_DROPDOWN = $(".oxd-userdropdown-tab");
    private final SelenideElement LOGOUT_LINK = $("a[href*='logout']");
    private final ElementsCollection WIDGETS = $$(".orangehrm-dashboard-widget");
    private final SelenideElement PIM_MENU_ITEM = $("a[href*='viewPimModule']");
    private final SelenideElement LEAVE_MENU_ITEM = $("a[href*='viewLeaveModule']");
    private final SelenideElement ADMIN_MENU_ITEM = $("a[href*='viewAdminModule']");
    private final SelenideElement RECRUITMENT_MENU_ITEM = $("a[href*='viewRecruitmentModule']");
    private final SelenideElement MY_INFO_MENU_ITEM = $("a[href*='viewMyDetails']");

    /**
     * Проверить, что страница открыта.
     */
    @Step("Проверка того, что страница открыта")
    public DashboardPage verifyIsOpen() {
        logger.debug("Проверка того, что страница открыта");

        HEADER_TITLE.shouldHave(Condition.text("Dashboard"));

        return this;
    }

    /**
     * Выйти из системы.
     */
    @Step("Выход из системы")
    public LoginPage logout() {
        logger.debug("Выход из системы");

        USER_DROPDOWN.click();
        LOGOUT_LINK.shouldBe(Condition.visible).click();

        return new LoginPage();
    }

    /**
     * Проверить, что виджеты отображаются.
     */
    @Step("Проверка того, что виджеты отображаются")
    public DashboardPage verifyWidgetsAreDisplayed() {
        logger.debug("Проверка того, что виджеты отображаются");

        WIDGETS.shouldHave(CollectionCondition.sizeGreaterThan(0));

        return this;
    }

    /**
     * Открыть страницу "PIM".
     */
    @Step("Открытие страницы \"PIM\"")
    public PimPage openPimPage() {
        logger.debug("Открытие страницы \"PIM\"");

        PIM_MENU_ITEM.click();

        return new PimPage();
    }

    /**
     * Открыть страницу отпуска.
     */
    @Step("Открытие страницы отпуска")
    public LeavePage openLeavePage() {
        logger.debug("Открытие страницы отпуска");

        LEAVE_MENU_ITEM.click();

        return new LeavePage();
    }

    /**
     * Открыть страницу администрирования.
     */
    @Step("Открытие страницы администрирования")
    public AdminPage openAdminPage() {
        logger.debug("Открытие страницы администрирования");

        ADMIN_MENU_ITEM.click();

        return new AdminPage();
    }

    /**
     * Открыть страницу подбора персонала.
     */
    @Step("Открытие страницы подбора персонала")
    public RecruitmentPage openRecruitmentPage() {
        logger.debug("Открытие страницы подбора персонала");

        RECRUITMENT_MENU_ITEM.click();

        return new RecruitmentPage();
    }

    /**
     * Открыть страницу "Моя информация".
     */
    @Step("Открытие страницы \"Моя информация\"")
    public MyInfoPage openMyInfoPage() {
        logger.debug("Открытие страницы \"Моя информация\"");

        MY_INFO_MENU_ITEM.click();

        return new MyInfoPage();
    }

}

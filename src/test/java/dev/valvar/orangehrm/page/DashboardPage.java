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
    @Step("Проверить, что страница открыта")
    public DashboardPage verifyIsOpen() {
        HEADER_TITLE.shouldHave(Condition.text("Dashboard"));

        return this;
    }

    /**
     * Выйти из системы.
     */
    @Step("Выйти из системы")
    public LoginPage logout() {
        USER_DROPDOWN.click();
        LOGOUT_LINK.shouldBe(Condition.visible).click();

        return new LoginPage();
    }

    /**
     * Проверить, что виджеты отображаются.
     */
    @Step("Проверить, что виджеты отображаются")
    public DashboardPage verifyWidgetsAreDisplayed() {
        WIDGETS.shouldHave(CollectionCondition.sizeGreaterThan(0));

        return this;
    }

    /**
     * Открыть страницу "PIM".
     */
    @Step("Открыть страницу \"PIM\"")
    public PimPage openPimPage() {
        PIM_MENU_ITEM.click();

        return new PimPage();
    }

    /**
     * Открыть страницу отпуска.
     */
    @Step("Открыть страницу отпуска")
    public LeavePage openLeavePage() {
        LEAVE_MENU_ITEM.click();

        return new LeavePage();
    }

    /**
     * Открыть страницу администрирования.
     */
    @Step("Открыть страницу администрирования")
    public AdminPage openAdminPage() {
        ADMIN_MENU_ITEM.click();

        return new AdminPage();
    }

    /**
     * Открыть страницу подбора персонала.
     */
    @Step("Открыть страницу подбора персонала")
    public RecruitmentPage openRecruitmentPage() {
        RECRUITMENT_MENU_ITEM.click();

        return new RecruitmentPage();
    }

    /**
     * Открыть страницу "Моя информация".
     */
    @Step("Открыть страницу \"Моя информация\"")
    public MyInfoPage openMyInfoPage() {
        MY_INFO_MENU_ITEM.click();

        return new MyInfoPage();
    }

}

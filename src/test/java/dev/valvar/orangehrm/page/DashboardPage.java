package dev.valvar.orangehrm.page;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Страница панели управления.
 */
public class DashboardPage {

    private final SelenideElement HEADER_TITLE = $(".oxd-topbar-header-breadcrumb-module");
    private final SelenideElement USER_DROPDOWN = $(".oxd-userdropdown-tab");
    private final SelenideElement LOGOUT_LINK = $("a[href*='logout']");
    private final ElementsCollection WIDGETS = $$(".orangehrm-dashboard-widget");
    private final SelenideElement PIM_MENU_ITEM = $("a[href*='viewPimModule']");
    private final SelenideElement LEAVE_MENU_ITEM = $("a[href*='viewLeaveModule']");
    private final SelenideElement ADMIN_MENU_ITEM = $("a[href*='viewAdminModule']");
    private final SelenideElement RECRUITMENT_MENU_ITEM = $("a[href*='viewRecruitmentModule']");
    private final SelenideElement MY_INFO_MENU_ITEM = $("a[href*='viewMyDetails']");


    @Step("Проверить, что страница панели управления (Dashboard) открыта")
    public DashboardPage verifyIsOpen() {
        HEADER_TITLE.shouldHave(Condition.text("Dashboard"));

        return this;
    }

    @Step("Выйти из системы")
    public LoginPage logout() {
        USER_DROPDOWN.click();
        LOGOUT_LINK.shouldBe(Condition.visible).click();

        return new LoginPage();
    }

    @Step("Убедиться, что на странице Dashboard отображаются виджеты")
    public DashboardPage verifyWidgetsAreDisplayed() {
        WIDGETS.shouldHave(CollectionCondition.sizeGreaterThan(0));

        return this;
    }

    @Step("Перейти в раздел 'PIM' через боковое меню")
    public PimPage openPimPage() {
        PIM_MENU_ITEM.click();

        return new PimPage();
    }

    @Step("Перейти в раздел 'Leave' через боковое меню")
    public LeavePage openLeavePage() {
        LEAVE_MENU_ITEM.click();

        return new LeavePage();
    }

    @Step("Перейти в раздел 'Admin' (Администрирование) через боковое меню")
    public AdminPage openAdminPage() {
        ADMIN_MENU_ITEM.click();

        return new AdminPage();
    }

    @Step("Перейти в раздел 'Recruitment' через боковое меню")
    public RecruitmentPage openRecruitmentPage() {
        RECRUITMENT_MENU_ITEM.click();

        return new RecruitmentPage();
    }

    @Step("Перейти в раздел 'My Info' через боковое меню")
    public MyInfoPage openMyInfoPage() {
        MY_INFO_MENU_ITEM.click();

        return new MyInfoPage();
    }

}
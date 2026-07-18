package dev.valvar.orangehrm.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import dev.valvar.orangehrm.ConfigReader;
import dev.valvar.orangehrm.page.DashboardPage;
import dev.valvar.orangehrm.page.LoginPage;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.BeforeSuite;


/**
 * Базовый тест.
 */
public abstract class BaseTest {

    protected LoginPage loginPage;
    protected DashboardPage dashboardPage;

    @BeforeSuite
    public void setupAllure() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));
    }

    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(@Optional("chrome") String browser) {
        Configuration.browser = browser;
        Configuration.baseUrl = ConfigReader.getProperty("baseUrl");
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10_000;

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            options.addArguments("--lang=en-US");
            options.addArguments("--disable-notifications");
            options.addArguments("--headless=new");
            Configuration.browserCapabilities = options;
        }

        Configuration.headless = Boolean.parseBoolean(ConfigReader.getProperty("headless"));

        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        Selenide.closeWebDriver();
    }

}

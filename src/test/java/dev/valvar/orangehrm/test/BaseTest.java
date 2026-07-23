package dev.valvar.orangehrm.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import dev.valvar.orangehrm.page.DashboardPage;
import dev.valvar.orangehrm.page.LoginPage;
import dev.valvar.orangehrm.utils.ConfigReader;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

public abstract class BaseTest {

    protected String username;
    protected String password;

    protected LoginPage loginPage;
    protected DashboardPage dashboardPage;

    @BeforeSuite
    public void setupAllure() {
        SelenideLogger.addListener(
                "AllureSelenide",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(true)
        );
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
            Configuration.browserCapabilities = options;
        }

        Configuration.headless = Boolean.parseBoolean(ConfigReader.getProperty("headless"));

        username = ConfigReader.getProperty("validUsername");
        password = ConfigReader.getProperty("validPassword");

        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        Selenide.closeWebDriver();
    }

}

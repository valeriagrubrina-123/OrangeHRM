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


/**
 * Базовый тест.
 */
public abstract class BaseTest {

    protected LoginPage loginPage;
    protected DashboardPage dashboardPage;

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

        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        Selenide.closeWebDriver();
    }

}

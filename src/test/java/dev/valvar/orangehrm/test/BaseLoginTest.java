package dev.valvar.orangehrm.test;

import dev.valvar.orangehrm.ConfigReader;
import org.testng.annotations.BeforeMethod;

public abstract class BaseLoginTest extends BaseTest {

    @BeforeMethod
    public void loginBeforeTest() {
        String username = ConfigReader.getProperty("validUsername");
        String password = ConfigReader.getProperty("validPassword");

        loginPage
                .open()
                .login(username, password);
    }

}

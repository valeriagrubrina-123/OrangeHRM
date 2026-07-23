package dev.valvar.orangehrm.test;

import org.testng.annotations.BeforeMethod;

public abstract class BaseLoginTest extends BaseTest {

    @BeforeMethod
    public void loginBeforeTest() {
        loginPage
                .open()
                .login(username, password);
    }

}

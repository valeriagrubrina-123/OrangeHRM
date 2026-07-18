package dev.valvar.orangehrm.test;

import org.testng.annotations.Test;

@Test(description = "Моя информация (My info)")
public class MyInfoTest extends BaseLoginTest {

    @Test(description = "Переход к просмотру информации о себе")
    public void goToViewingInformationAboutYourselfTest() {
        dashboardPage
                .openMyInfoPage()
                .verifyIsOpen();
    }

}

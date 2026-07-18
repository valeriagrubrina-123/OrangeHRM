package dev.valvar.orangehrm.test;

import dev.valvar.orangehrm.ConfigReader;
import org.testng.ITestContext;
import org.testng.annotations.Test;

@Test(description = "Администрирование (Admin)")
public class AdminTest extends BaseLoginTest {

    @Test(description = "Успешное создание пользователя")
    public void successfulUserCreationTest(ITestContext context) {
        /*
        Для создания пользователя требуется указать сотрудника. С одной стороны, можно создать нового сотрудника -
        чтоб не сталкиваться с ситуацией, когда тест падает из-за того, что сотрудник не найден. С другой стороны -
        мы все же проверяем создание пользователя, а не создание сотрудника, и если не создавать нового сотрудника,
        а использовать уже существующего - то тест проверит именно создание пользователя, даже если по какой-то причине
        не работает создание сотрудника - что, как по мне, более корректно.
         */
        String employeeFullName = ConfigReader.getProperty("employeeFullName");

        String systemUsername = "TestUser" + System.currentTimeMillis();

        context.setAttribute("systemUsername", systemUsername);

        dashboardPage
                .openAdminPage()
                .clickAddUserButton()
                .fillForm("Admin", employeeFullName, "Enabled", systemUsername, "StrongPass123!")
                .save()
                .verifySavedSuccessfully();
    }

    @Test(
            description = "Отображение созданного пользователя в списке",
            dependsOnMethods = "successfulUserCreationTest"
    )
    public void displayingCreatedUserInListTest(ITestContext context) {
        String systemUsername = (String) context.getAttribute("systemUsername");

        dashboardPage
                .openAdminPage()
                .searchUserByUsername(systemUsername)
                .verifyUserFound(systemUsername);
    }

    @Test(
            description = "Удаление пользователя",
            dependsOnMethods = "displayingCreatedUserInListTest"
    )
    public void deletingUserTest(ITestContext context) {
        String systemUsername = (String) context.getAttribute("systemUsername");

        dashboardPage
                .openAdminPage()
                .searchUserByUsername(systemUsername)
                .deleteUser()
                .verifyUserNotFound(systemUsername);
    }

}

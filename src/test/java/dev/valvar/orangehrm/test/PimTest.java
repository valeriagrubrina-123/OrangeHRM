package dev.valvar.orangehrm.test;

import org.testng.ITestContext;
import org.testng.annotations.Test;

@Test(description = "Управление сотрудниками (PIM)")
public class PimTest extends BaseLoginTest {

    @Test(description = "Добавление сотрудника")
    public void addingEmployeeTest(ITestContext context) {
        String firstName = "TestEmployee";
        String lastName = String.valueOf(System.currentTimeMillis());
        String fullName = firstName + " " + lastName;

        context.setAttribute("employeeFirstName", firstName);
        context.setAttribute("employeeLastName", lastName);
        context.setAttribute("employeeFullName", fullName);

        dashboardPage
                .openPimPage()
                .clickAddEmployeeButton()
                .fillForm(firstName, lastName)
                .save()
                .verifySavedSuccessfully();
    }

    @Test(
            description = "Поиск сотрудника по имени/фамилии через фильтр",
            dependsOnMethods = "addingEmployeeTest"
    )
    public void searchForEmployeeByFirstLastNameUsingFilterTest(ITestContext context) {
        String firstName = (String) context.getAttribute("employeeFirstName");
        String lastName = (String) context.getAttribute("employeeLastName");
        String fullName = (String) context.getAttribute("employeeFullName");

        dashboardPage
                .openPimPage()
                .searchEmployeeByFullName(fullName)
                .verifyEmployeeFound(firstName, lastName);
    }

    @Test(
            description = "Редактирование личной информации сотрудника (Иконка карандашика)",
            dependsOnMethods = "searchForEmployeeByFirstLastNameUsingFilterTest"
    )
    public void editingEmployeePersonalInformationTest(ITestContext context) {
        String fullName = (String) context.getAttribute("employeeFullName");

        dashboardPage
                .openPimPage()
                .searchEmployeeByFullName(fullName)
                .clickEditEmployeeIcon()
                .editMiddleName("EditedMiddleName")
                .save()
                .verifySavedSuccessfully();
    }

    @Test(
            description = "Удаление сотрудника из списка (иконка урны)",
            dependsOnMethods = "editingEmployeePersonalInformationTest"
    )
    public void removingEmployeeFromListTest(ITestContext context) {
        String firstName = (String) context.getAttribute("employeeFirstName");
        String lastName = (String) context.getAttribute("employeeLastName");
        String fullName = (String) context.getAttribute("employeeFullName");

        dashboardPage
                .openPimPage()
                .searchEmployeeByFullName(fullName)
                .deleteEmployee()
                .searchEmployeeByFullName(fullName)
                .verifyEmployeeNotFound(firstName, lastName);
    }

}

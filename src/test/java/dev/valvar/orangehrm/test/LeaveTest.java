package dev.valvar.orangehrm.test;

import dev.valvar.orangehrm.OrangeHrmUtils;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.Month;

@Test(description = "Заявки на отпуск (Leave)")
public class LeaveTest extends BaseLoginTest {

    @Test(description = "Успешное создание заявки на отпуск")
    public void successfullyCreatingVacationRequestTest(ITestContext context) {
        // Нужно убедиться, что на выбранный диапазон дат есть неизрасходованные дни отпуска.
        LocalDate fromDate = OrangeHrmUtils.generateRandomDate(
                LocalDate.of(2026, Month.JANUARY, 1),
                LocalDate.of(2026, Month.DECEMBER, 31)
        );

        context.setAttribute("expectedLeaveDate", fromDate);

        dashboardPage
                .openLeavePage()
                .openApplyLeavePage()
                .apply("CAN - Personal", fromDate)
                .verifyAppliedSuccessfully();
    }

    @Test(
            description = "Отображение созданной заявки в разделе \"Мой отпуск\"",
            dependsOnMethods = "successfullyCreatingVacationRequestTest"
    )
    public void displayingCreatedApplicationInMyVacationSectionTest(ITestContext context) {
        LocalDate expectedDate = (LocalDate) context.getAttribute("expectedLeaveDate");

        dashboardPage
                .openLeavePage()
                .openMyLeavePage()
                .verifyLeaveExists(expectedDate);
    }

}
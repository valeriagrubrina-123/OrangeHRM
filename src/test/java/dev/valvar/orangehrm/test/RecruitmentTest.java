package dev.valvar.orangehrm.test;

import dev.valvar.orangehrm.utils.ConfigReader;
import org.testng.annotations.Test;

@Test(description = "Подбор персонала (Recruitment)")
public class RecruitmentTest extends BaseLoginTest {

    @Test(description = "Поиск нужного сотрудника")
    public void findingRightEmployeeTest() {
        String candidateName = ConfigReader.getProperty("candidateName");

        dashboardPage
                .openRecruitmentPage()
                .searchCandidateByName(candidateName)
                .verifyCandidateFound(candidateName);
    }

    @Test(description = "Просмотр информации по сотруднику (Иконка глазик)")
    public void viewingEmployeeInformationTest() {
        String candidateName = ConfigReader.getProperty("candidateName");

        dashboardPage
                .openRecruitmentPage()
                .searchCandidateByName(candidateName)
                .clickViewCandidateIcon()
                .verifyIsOpen();
    }

}

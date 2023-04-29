package UI;

import BaseObject.BaseTest;
import PageObject.AuthorizationPage;
import PageObject.PlansCheckPage;
import io.qameta.allure.*;
import org.testng.annotations.*;

public class Plans_flow extends BaseTest {

    @BeforeMethod()
    public void openUrl() {
        driver.get(context.getSuite().getParameter("url"));
    }

    @Test(priority = 1,dataProvider ="avkUsers")
    public void login(String login, String password) throws InterruptedException {
        get(AuthorizationPage.class).validatePage().
                authorization(login, password);
    }



    /* @Test(priority = 2, dependsOnMethods = "login",enabled = false)
    @Description("Some detailed test description")
    @Step("Searching for '{keyword}' in Google")
    @Link("https://instagram.com/dmitryrak11")
    @Issue("COVID-19")
    @TmsLink("COVID-19")
    @Attachment(value = "screenshot", type = "image/png")
    public void createPlan() {
        get(PlansCheckPage.class).validatePage().
                addPlan("Четвертый квартал", "2024").
                checkCorrectDataAfterAddPlan();


    }

    @Test(priority = 7, enabled = false)
    public void deletePlan() throws InterruptedException {
        get(PlansCheckPage.class).deletePlan(2);
    }

    @Test(priority = 8,enabled = false)
    public void renamePlan() {
        get(PlansCheckPage.class).checkCorrectDataAfterAddEvent();


    }


    @Test(priority = 3,enabled = false)
    public void addEvent() {
        get(PlansCheckPage.class).addEvent(1, "Проверочное мероприятие_автотесты", "10001", "soglas12").
                checkCorrectDataAfterAddEvent();


    }

    @Test(priority = 4,enabled = false)
    public void routePlanOnAgreeAndApprove() {
        get(PlansCheckPage.class).formDocument().
                routePlan("soglas12", "utverd12");

    }

     */

    @DataProvider(name = "avkUsers")
    private Object[][] getData() {
        return new Object[][]{
                {"admin", "12345Qaz"},
                {"d12f", "12345Qaz"},
                {"os6s", "12345Qaz"},
                {"os2e", "12345Qaz"},
                {"os3s", "12345Qaz"},
                {"os8i", "12345Qaz"},
                {"sa4c", "12345Qaz"},
                {"sa2e", "12345Qaz"},
                {"sa1c", "12345Qaz"},
                {"sa4d", "12345Qaz"},
                {"sa2c", "12345Qaz"},
                {"io2k", "12345Qaz"},
                {"io9s", "12345Qaz"},
                {"lg1c", "12345Qaz"},
                {"lg4c", "12345Qaz"},
                {"vf3b", "12345Qaz"},
                {"vf2b", "12345Qaz"},
                {"vf4s", "12345Qaz"},
                {"vf7c", "12345Qaz"},
                {"ku7l", "12345Qaz"},
                {"ku3s", "12345Qaz"},
                {"d12e", "12345Qaz"},
                {"nv9s", "12345Qaz"},
                {"by1e", "12345Qaz"},
                {"by7e", "12345Qaz"},
                {"ig4s", "12345Qaz"},
                {"ia1c", "12345Qaz"},
                {"d16k", "12345Qaz"},
                {"cz4c", "12345Qaz"},
                {"cz5c", "12345Qaz"},
                {"wb1b", "12345Qaz"},
                {"wb2d", "12345Qaz"},
                {"dx", "12345Qaz"},
                {"ко1с", "12345Qaz"},
                {"d320", "12345Qaz"},
                {"d32d", "12345Qaz"},
                {"gj1s", "12345Qaz"},
                {"gj2s", "12345Qaz"},
                {"gj1c", "12345Qaz"},
                {"gk1b", "12345Qaz"},
                {"gk4s", "12345Qaz"},
                {"gk2s", "12345Qaz"},
                {"gb1b", "12345Qaz"},
                {"of2c", "12345Qaz"},
                {"jf1b", "12345Qaz"},
                {"of3c", "12345Qaz"},
                {"jf4s", "12345Qaz"},
                {"gf1s", "12345Qaz"},
                {"gf4s", "12345Qaz"},
                {"gf7s", "12345Qaz"},
                {"mk2f", "12345Qaz"},
                {"mk3d", "12345Qaz"},
                {"mk6e", "12345Qaz"},
                {"os2b", "12345Qaz"},
        };
    }
}














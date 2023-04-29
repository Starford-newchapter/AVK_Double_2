package UI;

import BaseObject.BaseTest;
import PageObject.*;

import static PageObject.Tabs.*;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class Soglas_User extends BaseTest {

    @BeforeTest()
    public void openUrl() {
        driver.get(context.getSuite().getParameter("url"));
    }

    @Parameters({"login","password"})
    @Test(priority = 1)
    public void login(String login,String password) throws InterruptedException {
        get(AuthorizationPage.class).validatePage().
                authorization(login, password);

        Thread.sleep(2000);


    }

    @Test(priority = 7,enabled = false)
    public  void agreePlan(){
        get(PlansCheckPage.class).validatePage().
                agreeWithPlan();

    }

    @Test(enabled = false)
    public  void  rejectPlan(){
        get(PlansCheckPage.class).rejectPlan();
    }

    @Test(priority = 2)
    public  void createReference(){
        get(HomePage.class).goToTab(CПРАВКИ_ПРОВЕРОК);

        get(ReferenceCheckPage.class).createReference("Автотесты_211").
                checkTableAfterCreateReference();
        ;

    }

    @Test(priority = 3)
    public  void routeReference(){
        get(ReferenceCheckPage.class).formDocument().
                routeReference();

    }

    @Test(priority = 4,enabled = true)
    public  void authorization_2(){
        driver.get(context.getSuite().getParameter("url"));
        get(AuthorizationPage.class).validatePage().
                authorization("soglas12", "1");

    }

    @Test(priority = 5,enabled = true)
    public  void agreeReference(){
        get(HomePage.class).goToTab(CПРАВКИ_ПРОВЕРОК);
        get(ReferenceCheckPage.class).agreeReference();

    }

}

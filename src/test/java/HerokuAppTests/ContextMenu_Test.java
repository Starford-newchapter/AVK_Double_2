package HerokuAppTests;

import BaseObject.BaseTest;
import PageObject.Herokuapp.ContextMenuPage;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ContextMenu_Test extends BaseTest {

    @Test
    public  void  rightClickAndAcceptAlert(){
        driver.get("http://the-internet.herokuapp.com/context_menu");
        get(ContextMenuPage.class).validatePage().
                rightClick().verifyAlertText();
    }
}

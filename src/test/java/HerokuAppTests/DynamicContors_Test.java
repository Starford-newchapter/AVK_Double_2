package HerokuAppTests;

import BaseObject.BaseTest;
import PageObject.Herokuapp.ContextMenuPage;
import PageObject.Herokuapp.DynamicControlsPage;
import org.testng.annotations.Test;

public class DynamicContors_Test extends BaseTest {
    @Test(priority = 1)
    public  void  removeCheckBox(){
        driver.get("http://the-internet.herokuapp.com/dynamic_controls");
        get(DynamicControlsPage.class).validatePage().
                clickOnRemoveButton().verifyCheckBox();
    }
    @Test(priority = 2)
    public  void  enableInput(){
        get(DynamicControlsPage.class).isInputDisabled().
                clickOnEnableButton();
    }


}

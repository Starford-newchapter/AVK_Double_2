package PageObject.Herokuapp;

import PageObject.BasePage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class ContextMenuPage extends BasePage {
    private By contextMenuTitle = By.tagName("h3");
    private  By areaForRightClIck=By.id("hot-spot");

    public ContextMenuPage(WebDriver driver) {
        super.driver = driver;
    }

    public ContextMenuPage validatePage(){
        Assert.assertTrue(driver.findElement(contextMenuTitle).isDisplayed());
        Assert.assertEquals(driver.findElement(contextMenuTitle).getText(),"Context Menu");
        return  this;
    }

    public  ContextMenuPage rightClick(){
        actions=new Actions(driver);
        actions.contextClick(driver.findElement(areaForRightClIck)).perform();
        return  this;

    }
    public  ContextMenuPage verifyAlertText(){
        alert=driver.switchTo().alert();
        Assert.assertEquals(alert.getText(),"You selected a context menu");
        alert.accept();
        return  this;

    }


}
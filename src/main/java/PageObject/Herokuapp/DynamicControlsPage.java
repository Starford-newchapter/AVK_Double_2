package PageObject.Herokuapp;

import PageObject.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class DynamicControlsPage extends BasePage {
    private By dynamicControlsPageTitle=By.tagName("h4");
    private  By removeButton=By.xpath("//*[@onclick='swapCheckbox()']");
    private  By checkbox=By.xpath("//*[@type='checkbox']");
    private  By inputField=By.xpath("//*[@type='text']");
    private By disable_enable_button=By.xpath("//*[@onclick='swapInput()']");
    private  By successMessage=By.xpath("//*[@id='message']");



    public DynamicControlsPage(WebDriver driver) {
        super.driver = driver;
    }

    public  DynamicControlsPage validatePage(){
        Assert.assertTrue(driver.findElement(dynamicControlsPageTitle).isDisplayed());
        Assert.assertEquals(driver.findElement(dynamicControlsPageTitle).getText(),"Dynamic Controls");
        return  this;
    }

    public  DynamicControlsPage clickOnRemoveButton(){
        webDriverWait=new WebDriverWait(driver, Duration.ofSeconds(5));
        clickButton(removeButton);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        Assert.assertEquals(driver.findElement(successMessage).getText(),"It's gone!");
        return  this;
    }
    public  DynamicControlsPage verifyCheckBox(){
        Assert.assertEquals(driver.findElements(checkbox).size(),0);
        return  this;
    }

    public DynamicControlsPage isInputDisabled(){
        Assert.assertTrue(driver.findElement(inputField).isDisplayed());
        Assert.assertFalse(driver.findElement(inputField).isEnabled());
        return  this;

    }

    public  DynamicControlsPage clickOnEnableButton(){
        webDriverWait=new WebDriverWait(driver, Duration.ofSeconds(5));
        clickButton(disable_enable_button);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        Assert.assertTrue(driver.findElement(inputField).isEnabled());
        return  this;
    }



}

package PageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;
    protected JavascriptExecutor js;
    protected Actions actions;
    protected Alert alert;





    protected void clickButton(By button) {
        driver.findElement(button).click();
        sleep(2);
    }

    protected void sendText(By inputField, String text) {
        driver.findElement(inputField).sendKeys(text);
    }

    protected  String getText(By element){
        return  driver.findElement(element).getText();
    }

    private String getErrorMessage(By errorElements) {
        return driver.findElement(errorElements).getText();
    }


    protected void checkError(By errorElements) {
        Assert.assertTrue(driver.findElement(errorElements).isDisplayed());
        Assert.assertEquals(getErrorMessage(errorElements), "Неправильный логин или пароль");
    }

    protected Select selectValue(By selectValue) {
        return new Select(driver.findElement(selectValue));

    }

    protected List<String>  convertToStringList(List<WebElement> webElementList){
        List<String> stringList=new ArrayList<String>();
        webElementList.forEach(element -> stringList.add(element.getText()));

        return stringList;
    }

    protected  int getTableSize(By table) {
        List<String> plans = convertToStringList(driver.findElements(table));
        plans.removeIf(s -> s.equals("       ") || s.equals("     "));
        return plans.size();
    }

    protected  void  clickOnPlanInTable(By plan){
        driver.findElement(plan).click();
        sleep(1);
    }

    protected  void  sleep(long second){
        try {
            Thread.sleep(second*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    protected  void  scrollDown(){
        js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    protected  void  clickOnItem(By item){
        driver.findElement(item).click();
        sleep(1);
    }
}

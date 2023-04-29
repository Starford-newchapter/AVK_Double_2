package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AuthorizationPage extends BasePage {
    private By titleAuthorization = By.xpath("//h1[contains(text(),'Авторизация')]");
    private By loginInput = By.name("login");
    private By passwordInput = By.name("password");
    private By loginButton = By.xpath("//button[@type='submit']");
    private By errorMessage = By.xpath("//div[@role='alert']");

    public AuthorizationPage(WebDriver driver) {
        super.driver = driver;
    }


    private void sendLogin(String login) {
        driver.findElement(loginInput).clear();
        sendText(loginInput,login);
    }

    private void sendPassword(String password) {
        driver.findElement(passwordInput).clear();
        sendText(passwordInput,password);
    }



    public AuthorizationPage validatePage() {
        Assert.assertTrue(driver.findElement(titleAuthorization).isDisplayed());
        Assert.assertEquals(getText(titleAuthorization), "Авторизация");
        return this;
    }

    public AuthorizationPage authorization(String login, String password) {
        sendLogin(login);
        sendPassword(password);
        clickButton(loginButton);
        return this;
    }

    public AuthorizationPage failAuthorization(String login, String password) {
        sendLogin(login);
        sendPassword(password);
        clickButton(loginButton);
        checkError(errorMessage);
        return this;

    }




}



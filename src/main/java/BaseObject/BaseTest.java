package BaseObject;

import TestNgUtills.Listener;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import java.lang.reflect.InvocationTargetException;

import static BaseObject.SingletonDriverCreation.getDriver;
@Listeners(Listener.class)
public class BaseTest {
    protected WebDriver driver;
    protected ITestContext context;

    @BeforeTest
    public void precondition(ITestContext context) {
        this.context = context;
        this.driver = getDriver();

    }

    protected <T> T get(Class<T> page) {
        return get(page, this.driver);
    }

    protected <T> T get(Class<T> page, WebDriver driver) {
        T instance = null;
        try {
            instance = page.getDeclaredConstructor(WebDriver.class).newInstance(driver);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return instance;
    }
}

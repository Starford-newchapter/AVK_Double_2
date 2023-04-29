package BaseObject;

import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static io.github.bonigarcia.wdm.WebDriverManager.getInstance;

public class SingletonDriverCreation {
    private  static WebDriver driver = null;


    public static WebDriver getDriver() {
        if (driver == null) {
            //ChromeOptions options=new ChromeOptions();
            //options.addArguments("--remote-allow-origins=*");
            driver=new ChromeDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }


    public static void closeDriver() {
        getInstance(DriverManagerType.CHROME).quit();
    }
}


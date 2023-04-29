package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import  static  PageObject.Tabs.*;

//Класс для взаимодействия с вкладками
public class HomePage extends  BasePage {





    public HomePage(WebDriver driver) {
        super.driver = driver;
    }

    public void goToTab(Tabs link){
        String text=link.getLink();
        driver.findElement(By.xpath("//a[contains(text(),'"+text + "')]")).click();
        sleep(1);


        /*String [] words=tab.toString().toLowerCase().split("_");
        String text=words[0].substring(0,1).toUpperCase()+ words[0].substring(1);
        driver.findElement(By.xpath("//a[contains(text(),'"+text + "')]")).click();

         */

    }
}

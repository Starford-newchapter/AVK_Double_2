package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.security.Key;
import java.util.List;

public class ReferenceCheckPage extends BasePage {
    private By createReferenceButton = By.xpath("//button[contains(text(),'Создать справку')]");
    private By referenceTable = By.xpath("//div[@class='rt-tbody']/div[contains(@class,'group')]");
    private By firstReferenceInTable = By.xpath("//div[@class='rt-tr -odd']/div[@class='rt-td']");
    private By selectKindOfEvent = By.name("inspectionArrangementTypeId");
    private By nameOfEventInput = By.name("inspectionName");
    private By nameOfDepartmentCheckInputField = By.xpath("(//div[@class='css-1hwfws3'])[1]");
    private By submitButton = By.xpath("//button[@type='submit']");
    private By executorsOfEventInputField = By.xpath("(//div[@class='css-1hwfws3'])[2]");
    private By valueExecutors = By.xpath("(//div[@class='css-12jo7m5'])[2]");
    private  By formDocumentButton=By.xpath("//button[contains(text(),'Сформировать документ')]");
    private By deleteReferenceButton = By.xpath("//button[contains(text(),'Удалить справку')]");
    private By modalWindow = By.className("modal-content");
    private By approveDeleteReferenceButton = By.xpath("//button[contains(text(),'Да')]");
    private By correctReferenceButton=By.xpath("//button[contains(text(),'Корректировать справку')]");
    private By routeApproveButton=By.xpath("//button[contains(text(),'Маршрут утверждения справки')]");
    private  By selectApproveUser=By.name("approverId");
    private By selectAgreesUser=By.xpath("(//div[@class='css-1hwfws3'])[3]");
    private  By agreeReferenceButton=By.xpath("//button[contains(text(),'Согласовать справку')]");
    private  By rejectReferenceButton=By.xpath("//button[contains(text(),'Отклонить справку')]");


    private String nameOfEvent;
    private String nameOfExecutors;


    public ReferenceCheckPage(WebDriver driver) {
        super.driver = driver;
    }


    public ReferenceCheckPage createReference(String nameOfEvent) {
        this.nameOfEvent = nameOfEvent;
        int sizeOfReferenceTable = getTableSize(referenceTable);

        clickButton(createReferenceButton);

        scrollDown();

        Select kindOfEvent = selectValue(selectKindOfEvent);
        kindOfEvent.selectByVisibleText("Внеплановая проверка");

        scrollDown();

        sendText(nameOfEventInput, nameOfEvent);


        selectOption(nameOfDepartmentCheckInputField,"01");
        sleep(1);
        selectOption(executorsOfEventInputField,"Петров");
        sleep(1);
        this.nameOfExecutors = getText(valueExecutors);
        scrollDown();
        clickButton(submitButton);
        sleep(2);
        Assert.assertEquals(getTableSize(referenceTable), sizeOfReferenceTable + 1);


        return this;

    }


    private void selectOption(By field,String option){
        clickOnItem(field);
        String optionXpath=String.format("//div[contains(text(),'%s')]",option);   //Подумать как сделать локаторы гибкими
        driver.findElement(By.xpath(optionXpath)).click();
    }

    private  void  clickOnFirstReference(){
        clickOnItem(firstReferenceInTable);
    }

    public ReferenceCheckPage checkTableAfterCreateReference() {
        List<String> dataFirstReference = convertToStringList(driver.findElements(firstReferenceInTable));
        Assert.assertEquals(dataFirstReference.get(1), nameOfEvent);
        Assert.assertEquals(dataFirstReference.get(2), nameOfExecutors);
        Assert.assertEquals(dataFirstReference.get(3), "ввод данных");
        return this;
    }

    public ReferenceCheckPage formDocument(){
        clickOnFirstReference();
        clickButton(formDocumentButton);
        sleep(1);
        return  this;
    }

    public  ReferenceCheckPage deleteReference(){
        int sizeOfTable=getTableSize(referenceTable);
        clickOnFirstReference();
        clickButton(deleteReferenceButton);
        Assert.assertTrue(driver.findElement(modalWindow).isDisplayed());
        clickButton(approveDeleteReferenceButton);
        Assert.assertEquals(getTableSize(referenceTable),sizeOfTable-1);
        return  this;



    }


    public  ReferenceCheckPage correctReference(){
        clickOnFirstReference();
        clickButton(correctReferenceButton);
        js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
        driver.findElement(nameOfEventInput).clear();
        sendText(nameOfEventInput,"Исправление_3");
        sleep(1);


        this.nameOfEvent=driver.findElement(nameOfEventInput).getAttribute("value");
        scrollDown();
        clickButton(submitButton);

        Assert.assertEquals(convertToStringList(driver.findElements(firstReferenceInTable)).get(1), nameOfEvent);

        return  this;


    }


    public  ReferenceCheckPage routeReference(){
        clickButton(routeApproveButton);
        sleep(3);
        Assert.assertTrue(driver.findElement(modalWindow).isDisplayed());
        selectOption(selectAgreesUser,"v.kornilchik");

        Select approveUser=selectValue(selectApproveUser);
        approveUser.selectByValue("utverd11");
        clickButton(submitButton);
        sleep(2);

        Assert.assertEquals(convertToStringList(driver.findElements(firstReferenceInTable)).get(3), "на согласовании");

        return  this;

    }
    public  ReferenceCheckPage agreeReference(){
        clickOnFirstReference();
        clickButton(agreeReferenceButton);
        Assert.assertTrue(driver.findElement(modalWindow).isDisplayed());
        clickButton(submitButton);
        Assert.assertEquals(convertToStringList(driver.findElements(firstReferenceInTable)).get(3), "на ознакомлении");
        return  this;

    }

    public  ReferenceCheckPage rejectReference(){
        clickOnFirstReference();
        clickButton(rejectReferenceButton);
        Assert.assertEquals(convertToStringList(driver.findElements(firstReferenceInTable)).get(3), "корректировка");

        return  this;
    }





}

package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

public class PlansCheckPage extends BasePage {
    //План Проверок
    private By plansCheckTab = By.xpath("//a[contains(text(),'Планы проверок')]");//Вкладка Планы проверок
    private By tablePlans = By.xpath("(//div[@class='rt-tbody'])[1]/div[@class='rt-tr-group']");
    private By addPlanButton = By.xpath("//button[contains(text(),'Добавить план проверок')]");
    private By renamePlanButton = By.xpath("//button[contains(text(),'Переименовать план')]");
    private By approveRenamePlanButton = By.xpath("(//button[contains(text(),'Переименовать план')])[2]");//Кнопка подтверждение в модальном окне
    private By deletePlanButton = By.xpath("//button[contains(text(),'Удалить план проверок')]");
    private By approveDeletePlanButton = By.xpath("//button[contains(text(),'Да')]");
    private By modalWindow = By.className("modal-content");//Модальное окно(один  общий локатор для всех модалок)
    private By firstPlanInTable = By.xpath("(//div[@class='rt-tr -odd'])[1]/div[@class='rt-td']");//Первая запись в таблицы планов проверок
    private By titlePlanModalWindow = By.xpath("//div[contains(@class,'modal-title')]");
    private By anyPlanInTable; //Определяем в функции getAnyPlanInTable
    private By formDocumentButton = By.xpath("//button[contains(text(),'Сформировать документ')]");
    private By approvalRouteButton = By.xpath("//button[contains(text(),'Маршрут утверждения плана')]");
    private By agreePlanButton = By.xpath("//button[contains(text(),'Согласовать план проверок')]");
    private  By rejectPlanButton=By.xpath("//button[contains(text(),'Отклонить план проверок')]");


    //Выбор периода плана и год проведения  модалке Плана проверок
    private By selectPlanYear = By.name("year");//Локатор для селекта "Год проведения" плана проверок в модальном окне
    private By selectPlanPeriod = By.name("planningPeriodId");// Локатор для селекта "Плановый период" в модальном окне

    // Окно Маршрут Согласования
    private By selectAgreeUser = By.name("agreerId");
    private By selectApproveUser = By.name("approverId");
    private By onApproveButton = By.xpath("//button[contains(text(),'Направить на утверждение')]");

    //Проверочное мероприятие
    private By addEventButton = By.xpath("//button[contains(text(),'Добавить проверочное мероприятие')]");
    private By createButtonGeneral = By.xpath("//button[@type='submit']");
    private By firstEventInTable = By.xpath("(//div[@class='rt-tbody'])[2]/div[@class='rt-tr-group'][1]//div[@class='rt-td']");//Первая запись в таблице проверочных мероприятий
    private By eventTable = By.xpath("(//div[@class='rt-tbody'])[2]/div[@class='rt-tr-group']");

    //Поля в модалке проверочного мероприятия
    private By summaryEventField = By.xpath("(//textarea[@name='name'])");//Поле Темы проверки
    private By selectDepartmentId = By.name("responsibleDepartmentId");//Локатор для селекта "Проверяемого подразделения"
    private By selectResponsibleUser = By.name("responsibleUserId");//Локатор для селекта "Ответственный за проведение мероприятия"
    private By approveAddEventButton = By.xpath("//div[@class='modal-content']//button[contains(text(),'Добавить')]");

    private String year;
    private String period;
    private String nameOfEvent;
    private int numberOfPlan;
    private String agreeUser;
    private  String approveUser;



    public PlansCheckPage(WebDriver driver) {
        super.driver = driver;
    }


    public PlansCheckPage validatePage() {
        Assert.assertEquals(driver.findElement(plansCheckTab).getText(), "Планы проверок");
        return this;

    }

    //После создания плана проверок будем проверять размер таблицы и корректные данные в полях таблицы
    public PlansCheckPage addPlan(String period, String year) {
        this.period = period;
        this.year = year;

        int sizeTablePlans = getTableSize(tablePlans);
        List<String> plans = convertToStringList(driver.findElements(tablePlans));

        clickButton(addPlanButton);

        Assert.assertTrue(driver.findElement(modalWindow).isDisplayed());
        Assert.assertEquals(getText(titlePlanModalWindow), "Создание плана");

        //Определение селектов из функции в модальном окне для выбора года проведения и периода проверки
        selectPlanPeriodAndYear(year, period);


        clickButton(createButtonGeneral);


        Assert.assertEquals(getTableSize(tablePlans), sizeTablePlans + 1);


        return this;

    }

    //Формирование документа
    public PlansCheckPage formDocument() {
        clickOnPlanInTable(firstPlanInTable);
        clickButton(formDocumentButton);

        return this;
    }

    //Маршрут утверждения плана проверок
    public PlansCheckPage routePlan(String agreeUser, String approveUser) {
        this.agreeUser=agreeUser;
        this.approveUser=approveUser;

        clickButton(approvalRouteButton);
        sleep(2);
        Assert.assertTrue(driver.findElement(modalWindow).isDisplayed());
        selectAgreeAndApproveUser(agreeUser, approveUser);
        clickButton(onApproveButton);

        List<String> dataFirstPlan=convertToStringList(driver.findElements(firstPlanInTable));
        Assert.assertEquals(dataFirstPlan.get(4),"на согласовании");

        return  this;


    }

    // Проверка заполнения полей нужными данными
    public PlansCheckPage checkCorrectDataAfterAddPlan() {
        List<String> dataFirstPlan = convertToStringList(driver.findElements(firstPlanInTable));

        Assert.assertEquals(dataFirstPlan.get(2), this.year);
        Assert.assertEquals(dataFirstPlan.get(3), this.period);
        Assert.assertEquals(dataFirstPlan.get(4), "ввод данных");
        Assert.assertEquals(dataFirstPlan.get(5), "");

        return this;

    }

    public PlansCheckPage checkCorrectDataAfterAddEvent() {
        List<String> dataFirstEvent = convertToStringList(driver.findElements(firstEventInTable));
        Assert.assertEquals(dataFirstEvent.get(1), this.nameOfEvent);
        return this;
    }

    private void selectPlanPeriodAndYear(String year, String period) {
        Select selectYear = selectValue(selectPlanYear);
        Select selectPeriod = selectValue(selectPlanPeriod);

        selectYear.selectByVisibleText(year);
        selectPeriod.selectByVisibleText(period);

        sleep(1);


    }

    private void selectDepartmentAndResponsibleUser(String department, String responsibleUser) {
        Select selectDepartment = selectValue(selectDepartmentId);
        Select selectUser = selectValue(selectResponsibleUser);

        selectDepartment.selectByValue(department);
        selectUser.selectByValue(responsibleUser);

        sleep(1);


    }

    private void selectAgreeAndApproveUser(String agreeUser, String approveUser) {

        Select selectAgree = selectValue(selectAgreeUser);
        Select selectApprove = selectValue(selectApproveUser);

        selectAgree.selectByValue(agreeUser);
        selectApprove.selectByValue(approveUser);

        sleep(1);


    }

    /* Для того чтобы можно было выбирать любой план из таблицы
       создадим функцию возвращающая  локатор элемента с подставленнымм значением для выбора плана
     */
    private By getAnyPlanInTable() {
        return this.anyPlanInTable = By.xpath("(//div[@class='rt-tbody'])[1]/div[@class='rt-tr-group'][" + numberOfPlan + "]");
    }

    public PlansCheckPage deletePlan(int numberOfPlan) {
        this.numberOfPlan = numberOfPlan;
        int sizeTablePlan = getTableSize(tablePlans);

        clickOnPlanInTable(getAnyPlanInTable());

        clickButton(deletePlanButton);

        Assert.assertTrue(driver.findElement(modalWindow).isDisplayed());
        Assert.assertEquals(getText(titlePlanModalWindow), "План проверок будет удалён. Продолжить?");

        clickButton(approveDeletePlanButton);

        Assert.assertFalse(driver.findElement(modalWindow).isDisplayed());

        Assert.assertEquals(getTableSize(tablePlans), sizeTablePlan - 1);

        return this;
    }


    public PlansCheckPage renamePlan(int numberOfPlan, String year, String period) {
        this.year = year;
        this.period = period;
        this.numberOfPlan = numberOfPlan;

        clickOnPlanInTable(getAnyPlanInTable());
        clickButton(renamePlanButton);

        selectPlanPeriodAndYear(year, period);

        clickButton(renamePlanButton);


        return this;
    }

    public PlansCheckPage addEvent(int numberOfPlan, String nameOfEvent, String department, String responsibleUser) {
        this.numberOfPlan = numberOfPlan;
        this.nameOfEvent = nameOfEvent;


        clickOnPlanInTable(getAnyPlanInTable());

        int sizeEventTable = getTableSize(eventTable);

        scrollDown();


        clickButton(addEventButton);

        Assert.assertTrue(driver.findElement(modalWindow).isDisplayed());

        sendText(summaryEventField, nameOfEvent);
        selectDepartmentAndResponsibleUser(department, responsibleUser);

        clickButton(approveAddEventButton);


        Assert.assertEquals(getTableSize(eventTable), sizeEventTable + 1);


        return this;

    }


    public  PlansCheckPage agreeWithPlan(){
        clickOnPlanInTable(firstPlanInTable);
        clickButton(agreePlanButton);
        Assert.assertTrue(driver.findElement(modalWindow).isDisplayed());

        clickButton(approveDeletePlanButton);

        Assert.assertEquals(convertToStringList(driver.findElements(firstPlanInTable)).get(4),"на утверждении");

        return  this;
    }
    public PlansCheckPage rejectPlan(){
        clickOnPlanInTable(firstPlanInTable);
        clickButton(rejectPlanButton);
        Assert.assertTrue(driver.findElement(modalWindow).isDisplayed());

        clickButton(approveDeletePlanButton);

        Assert.assertEquals(convertToStringList(driver.findElements(firstPlanInTable)).get(4),"корректировка");
        return  this;

    }

    /*public PlansCheckPage workWithTable() {
        List<String> headers = driver.findElements(By.xpath("(//div[@class='rt-table'])[1]//div[contains(@class,'SelectableRowTable')]")).stream().map(WebElement::getText).collect(Collectors.toList());


        Map<String, List<String>> tableData = new HashMap<>();

        for (int i = 0; i < headers.size(); i++) {
            List<String> column = convertToStringList(driver.findElements(By.xpath("(//div[@class='rt-tbody'])[1]//div[@class='rt-td'][" + (i + 1) + "]")));
            tableData.put(headers.get(i), column);
        }

        out.println(tableData);
        return this;
    }

     */


}

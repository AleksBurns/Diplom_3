package pageObjects;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {
    WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By mainPageName  = By.xpath(".//*[text()='Соберите бургер']");
    private final By mainPersonalAccountButton = By.xpath(".//*[text()='Личный Кабинет']");
    private final By mainLoginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By mainCreateOrderButton = By.xpath(".//*[text() = 'Оформить заказ']");
    //Раздел конструктора
    private final By mainBuns = By.xpath(".//span[text()='Булки']//parent::div");
    private final By mainSauces = By.xpath(".//span[text()='Соусы']//parent::div");
    private final By mainFillings = By.xpath(".//span[text()='Начинки']//parent::div");

    @Step("Ожидание загрузки главной страницы")
    public void waitToLoadMainPageName() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(driver -> driver.findElement(mainPageName).isDisplayed());
    }
    @Step("Клик по кнопке 'Личный кабинет'")
    public void mainPersonalAccountButtonClick(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(mainPersonalAccountButton));
        driver.findElement(mainPersonalAccountButton).click();
    }
    @Step("Клик по кнопке 'Войти в аккаунт'")
    public void mainLoginButtonClick(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(mainLoginButton));
        driver.findElement(mainLoginButton).click();
    }
    @Step("Отображение кнопки 'Оформить заказ'")
    public void isDisplayedMainCreateOrderButton(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(driver -> driver.findElement(mainCreateOrderButton).isDisplayed());
    }
    @Step("Клик по кнопке 'Булки'")
    public void mainBunsButtonClick(){
        driver.findElement(mainBuns).click();
    }
    @Step("Клик по кнопке 'Соусы'")
    public void mainSaucesButtonClick(){
        driver.findElement(mainSauces).click();
    }
    @Step("Клик по кнопке 'Начинки'")
    public void mainFillingsButtonClick(){
        driver.findElement(mainFillings).click();
    }
    @Step("Отображение раздела 'Булки'")
    public boolean isDisplayedMainBunsSection(){
        String bunsClass = driver.findElement(mainBuns).getAttribute("class");
        return bunsClass != null && bunsClass.contains("current");
    }
    @Step("Отображение раздела 'Соусы'")
    public boolean isDisplayedMainSaucesSection(){
        String saucesClass = driver.findElement(mainSauces).getAttribute("class");
        return saucesClass != null && saucesClass.contains("current");
    }
    @Step("Отображение раздела 'Начинки'")
    public boolean isDisplayedMainFillingsSection(){
        String fillingsClass = driver.findElement(mainFillings).getAttribute("class");
        return fillingsClass != null && fillingsClass.contains("current");
    }
}
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
    private final By mainBunsButton = By.xpath(".//span[text()='Булки']");
    private final By mainSaucesButton = By.xpath(".//span[text()='Соусы']");
    private final By mainFillingsButton = By.xpath(".//span[text() ='Начинки']");
    private final By mainBunsSection = By.xpath(".//*[@class = 'text text_type_main-medium mb-6 mt-10' and text()='Булки']");
    private final By mainSaucesSection = By.xpath(".//*[@class = 'text text_type_main-medium mb-6 mt-10' and text()='Соусы']");
    private final By mainFillingsSection = By.xpath(".//*[@class = 'text text_type_main-medium mb-6 mt-10' and text()='Начинки']");

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
        driver.findElement(mainBunsButton).click();
    }
    @Step("Клик по кнопке 'Соусы'")
    public void mainSaucesButtonClick(){
        driver.findElement(mainSaucesButton).click();
    }
    @Step("Клик по кнопке 'Начинки'")
    public void mainFillingsButtonClick(){
        driver.findElement(mainFillingsButton).click();
    }
    @Step("Отображение раздела 'Булки'")
    public void isDisplayedMainBunsSection(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(driver -> driver.findElement(mainBunsSection).isDisplayed());
    }
    @Step("Отображение раздела 'Соусы'")
    public void isDisplayedMainSaucesSection(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(driver -> driver.findElement(mainSaucesSection).isDisplayed());
    }
    @Step("Отображение раздела 'Начинки'")
    public void isDisplayedMainFillingsSection(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(driver -> driver.findElement(mainFillingsSection).isDisplayed());
    }
}
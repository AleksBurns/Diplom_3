package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
     WebDriver driver;

    public RegisterPage(WebDriver driver){
        this.driver = driver;
    }

    private final By registerPageName = By.xpath(".//*[text()='Регистрация']");
    private final By registerNameField = By.xpath(".//*[text()='Имя']/../*[@type='text']");
    private final By registerEmailField = By.xpath(".//*[text()='Email']/../*[@type='text']");
    private final By registerPasswordField = By.xpath(".//*[text()='Пароль']/../*[@type='password']");
    private final By registerPasswordError = By.xpath(".//*[@class = 'input__error text_type_main-default' and text() = 'Некорректный пароль']");
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By registerLoginLink = By.xpath(".//*[text()='Войти']");

    public void isDisplayedRegisterPageName(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(driver -> driver.findElement(registerPageName).isDisplayed());
    }

    @Step("Регистрация нового пользователя")
    public void registration(String name, String email, String password){
        driver.findElement(registerNameField).sendKeys(name);
        driver.findElement(registerEmailField).sendKeys(email);
        driver.findElement(registerPasswordField).sendKeys(password);
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(registerButton));
        driver.findElement(registerButton).click();
    }

    @Step("Отображение сообщения 'Некорректный пароль'")
    public boolean isDisplayedRegisterPasswordError(){
        return driver.findElement(registerPasswordError).isDisplayed();
    }

    @Step("Клик по линку 'Войти' из страницы регистрации")
    public void registerLoginLinkClick(){
        driver.findElement(registerLoginLink).click();
    }
}
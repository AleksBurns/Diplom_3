package pageObjects;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pojoModels.User;

import java.time.Duration;
import java.util.Objects;

import static constants.Routes.*;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    private final By loginPageName = By.xpath(".//h2[text()='Вход']");
    private final By loginEmailField = By.xpath("//label[contains(text(),\"Email\")]/following-sibling::input");
    private final By loginPasswordField = By.xpath("//label[contains(text(),\"Пароль\")]/following-sibling::input");
    private final By loginEnterButton = By.xpath(".//*[text()='Войти']");

    public void waitToLoadLoginPageName() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(driver -> driver.findElement(loginPageName).isDisplayed());
    }
    public boolean isLoginPage(){
        return Objects.equals(driver.getCurrentUrl(), LOGIN_PAGE_URI);
    }

    @Step("Логин созданного пользователя")
    public void login(User createdUser){
        driver.findElement(loginEmailField).sendKeys(createdUser.getEmail());
        driver.findElement(loginPasswordField).sendKeys(createdUser.getPassword());
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(loginEnterButton));
        driver.findElement(loginEnterButton).click();
    }
}
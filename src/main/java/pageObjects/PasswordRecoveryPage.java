package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PasswordRecoveryPage {
    WebDriver driver;

    public PasswordRecoveryPage(WebDriver driver){
        this.driver = driver;
    }

    private final By recPassPageName = By.xpath(".//*[text()='Восстановление пароля']");
    private final By recPassLoginLink = By.xpath(".//*[text()='Войти']");

    public boolean isDisplayedRecPassPageName(){
       return driver.findElement(recPassPageName).isDisplayed();
    }

    @Step("Клик по линку Войти из формы восстановления пароля")
    public void recPassLoginLinkClick(){
        driver.findElement(recPassLoginLink).click();
    }
}

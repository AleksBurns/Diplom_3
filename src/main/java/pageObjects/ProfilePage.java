package pageObjects;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    WebDriver driver;

    public ProfilePage(WebDriver driver){
        this.driver = driver;
    }


    private final By profilePageName = By.xpath(".//*[text() = 'Профиль']");
    private final By profileConstructorLink = By.xpath(".//*[text()='Конструктор']");
    private final By profileMainPageLink = By.className("AppHeader_header__logo__2D0X2");
    private final By profileLogoutButton = By.xpath(".//button[text()='Выход']");

    public void isDisplayedProfilePageName(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(driver -> driver.findElement(profilePageName).isDisplayed());
    }

    @Step("Клик по линку 'Конструктор' из страницы профиля пользователя")
    public void profileConstructorLinkClick(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(profileConstructorLink));
        driver.findElement(profileConstructorLink).click();
    }

    @Step("Клик по кнопке 'Выход' из страницы профиля пользователя")
    public void profileLogoutButtonClick(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(profileLogoutButton));
        driver.findElement(profileLogoutButton).click();
    }

    @Step("Клик по логотипу 'stellar burgers' в хедере страницы профиля пользователя")
    public void profileMainPageLinkClick(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(profileMainPageLink));
        driver.findElement(profileMainPageLink).click();
    }
}
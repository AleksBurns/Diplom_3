import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageObjects.*;
import pojoModels.User;


import static creators.WebDriverCreator.createWebDriver;
import static constants.Routes.*;
import static creators.RandomGenerator.*;

/**
 * Проверить переход по клику на «Личный кабинет».
 * Проверить переход по клику на «Конструктор» и на логотип Stellar Burgers.
 * Проверить выход по кнопке «Выйти» в личном кабинете.
 */

public class ProfilePageTests extends User{
    WebDriver driver;
    MainPage mainPage;
    LoginPage loginPage;
    ProfilePage profilePage;

    User newUser = randomUser();

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URI;
        createUser(newUser);
        driver = createWebDriver();
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
    }

    @Test
    @DisplayName("Переход на страницу профиля по клику кнопки 'Личный кабинет' на главной странице")
    public void transitionToProfilePageFromMainPage(){
        driver.get(LOGIN_PAGE_URI);
        loginPage.login(newUser);
        mainPage.waitToLoadMainPageName();
        mainPage.mainPersonalAccountButtonClick();
        profilePage.isDisplayedProfilePageName();
    }

    @Test
    @DisplayName("Переход из личного кабинета на главную страницу при клике по логотипу в хэдере")
    public void transitionToMainPageFromProfilePage(){
        driver.get(LOGIN_PAGE_URI);
        loginPage.login(newUser);
        mainPage.waitToLoadMainPageName();
        mainPage.mainPersonalAccountButtonClick();
        profilePage.isDisplayedProfilePageName();
        profilePage.profileMainPageLinkClick();
        mainPage.waitToLoadMainPageName();
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор при клике по кнопке 'Конструктор'")
    public void transitionToConstructorFromProfilePage(){
        driver.get(LOGIN_PAGE_URI);
        loginPage.login(newUser);
        mainPage.waitToLoadMainPageName();
        mainPage.mainPersonalAccountButtonClick();
        profilePage.isDisplayedProfilePageName();
        profilePage.profileConstructorLinkClick();
        mainPage.waitToLoadMainPageName();
    }

    @Test
    @DisplayName("Выход из профиля при клике на кнопку 'Выход' в личном кабинете")
    public void logOutFromProfilePage(){
        driver.get(LOGIN_PAGE_URI);
        loginPage.login(newUser);
        mainPage.waitToLoadMainPageName();
        mainPage.mainPersonalAccountButtonClick();
        profilePage.isDisplayedProfilePageName();
        profilePage.profileLogoutButtonClick();
        loginPage.waitToLoadLoginPageName();
    }
    @After
    public void tearDown() {
        driver.close();
        deleteUser(newUser);
    }
}
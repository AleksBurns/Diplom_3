import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageObjects.MainPage;
import pageObjects.LoginPage;
import pageObjects.PasswordRecoveryPage;
import pageObjects.RegisterPage;
import pojoModels.User;


import static creators.WebDriverCreator.createWebDriver;
import static constants.Routes.*;
import static creators.RandomGenerator.*;

/**
 * Проверить:
 * вход по кнопке «Войти в аккаунт» на главной,
 * вход через кнопку «Личный кабинет»,
 * вход через кнопку в форме регистрации,
 * вход через кнопку в форме восстановления пароля.
 */

public class LoginPageTests extends User{
    WebDriver driver;
    MainPage mainPage;
    LoginPage loginPage;
    RegisterPage registerPage;
    PasswordRecoveryPage passwordRecoveryPage;

    User newUser = randomUser();

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URI;
        createUser(newUser);
        driver = createWebDriver();
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
    }

    @Test
    @DisplayName("Вход по кнопке 'Войти в аккаунт' на главной странице")
    public void loginFromMainPageLoginButton(){
        driver.get(BASE_URI);
        mainPage.waitToLoadMainPageName();
        mainPage.mainLoginButtonClick();
        loginPage.waitToLoadLoginPageName();
        loginPage.login(newUser);
        mainPage.waitToLoadMainPageName();
        mainPage.isDisplayedMainCreateOrderButton();
    }

    @Test
    @DisplayName("Вход по кнопке 'Личный кабинет' на главной странице")
    public void loginFromMainPagePersonalAccountButton(){
        driver.get(BASE_URI);
        mainPage.waitToLoadMainPageName();
        mainPage.mainPersonalAccountButtonClick();
        loginPage.waitToLoadLoginPageName();
        loginPage.login(newUser);
        mainPage.waitToLoadMainPageName();
        mainPage.isDisplayedMainCreateOrderButton();
    }

    @Test
    @DisplayName("Логин через кнопку в форме регистрации,")
    public void loginFromRegistrationPage(){
        registerPage = new RegisterPage(driver);
        driver.get(REGISTER_PAGE_URI);
        registerPage.isDisplayedRegisterPageName();
        registerPage.registerLoginLinkClick();
        loginPage.waitToLoadLoginPageName();
        loginPage.login(newUser);
        mainPage.waitToLoadMainPageName();
        mainPage.isDisplayedMainCreateOrderButton();
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void loginFromForgotPasswordPage(){
        passwordRecoveryPage = new PasswordRecoveryPage(driver);
        driver.get(PASSWORD_RECOVERY_PAGE);
        passwordRecoveryPage.isDisplayedRecPassPageName();
        passwordRecoveryPage.recPassLoginLinkClick();
        loginPage.waitToLoadLoginPageName();
        loginPage.login(newUser);
        mainPage.waitToLoadMainPageName();
        mainPage.isDisplayedMainCreateOrderButton();

    }

    @After
    public void tearDown() {
        driver.close();
        deleteUser(newUser);
    }
}

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pageObjects.LoginPage;
import pageObjects.RegisterPage;
import pojoModels.User;

import static constants.Routes.BASE_URI;
import static constants.Routes.REGISTER_PAGE_URI;
import static creators.WebDriverCreator.createWebDriver;
import static creators.RandomGenerator.*;

/**
 * Проверить:
 * Успешную регистрацию.
 * Ошибку для некорректного пароля. Минимальный пароль — шесть символов.
 */

@RunWith(Parameterized.class)
public class RegisterPageTests extends User{
    WebDriver driver;
    RegisterPage registerPage;
    LoginPage loginPage;
    User loginedUser;

    private final String name;
    private final String email;
    private final String password;
    private final boolean isSuccessfulRegistration;

    public RegisterPageTests(String name, String email, String password, boolean isSuccessfulRegistration) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.isSuccessfulRegistration = isSuccessfulRegistration;
    }

    @Parameterized.Parameters(name = "Регистрация с данными: Имя: {0} Email: {1} Пароль: {2} ")
    public static Object[][] userAttributes(){
        return new Object[][]{
                {randomName(),randomEmail(),randomPassword(6),true},
                {randomName(),randomEmail(),randomPassword(7),true},
                {randomName(),randomEmail(),randomPassword(5),false},
                {randomName(),randomEmail(),randomPassword(1),false},
        };
    }

    @Before
    public void setUp(){
        RestAssured.baseURI = BASE_URI;
        driver = createWebDriver();
        driver.manage().window().maximize();
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
    }

    @Test
    @Description("Проверка успешной регистрации; При пароле менее 6 символов выводится ошибка")
    public void registrationTest(){
        driver.get(REGISTER_PAGE_URI);
        registerPage.isDisplayedRegisterPageName();
        registerPage.registration(name, email, password);
        loginedUser = loginUser(name, email, password);
        Assert.assertEquals(isSuccessfulRegistration, loginPage.isLoginPage());
        if(password.length() < 6) {
            Assert.assertTrue(registerPage.isDisplayedRegisterPasswordError());
        }
    }

    @After
    public void tearDown() {
        driver.close();
        deleteUser(loginedUser);
    }
}
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageObjects.MainPage;

import static creators.WebDriverCreator.createWebDriver;
import static constants.Routes.*;

/**
 * Проверить, что работают переходы к разделам:
 * «Булки»,
 * «Соусы»,
 * «Начинки».
 */

public class MainPageTests {
    WebDriver driver;
    MainPage mainPage;

    @Before
    public void setUp() {
        driver = createWebDriver();
        driver.get(BASE_URI);
        mainPage = new MainPage(driver);
        mainPage.waitToLoadMainPageName();
    }

    @Test
    @DisplayName("При клике на кнопку 'Булки' отображается раздел с булками")
    public void checkBunsSection() {
        mainPage.mainSaucesButtonClick();
        mainPage.mainBunsButtonClick();
        mainPage.isDisplayedMainBunsSection();
    }

    @Test
    @DisplayName("При клике на кнопку 'Соусы' отображается раздел с соусами")
    public void checkSaucesSection() {
        mainPage.mainSaucesButtonClick();
        mainPage.isDisplayedMainSaucesSection();
    }

    @Test
    @DisplayName("При клике на кнопку 'Начинки' отображается раздел с начинками")
    public void checkFillingSection() {
        mainPage.mainFillingsButtonClick();
        mainPage.isDisplayedMainFillingsSection();
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
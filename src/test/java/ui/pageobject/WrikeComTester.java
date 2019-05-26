package ui.pageobject;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageobject.pages.HomePage;
import pageobject.pages.ResendPage;

public class WrikeComTester {

    private WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.firefoxdriver().setup();
    }

    @Before
    public void setupTest() {
        driver = new FirefoxDriver();
        driver.get("https://wrike.com");
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void createAccount() {
        WebDriverWait wait = new WebDriverWait(driver, 5);

        HomePage homePage = new HomePage(driver);
        homePage.createAccountWithRandomEmail();
        wait.until(ExpectedConditions.titleContains("Thank you for choosing Wrike!"));
        Assert.assertEquals("don't redirect on expected page after fill email at \"create new account block\"", "https://www.wrike.com/resend/", driver.getCurrentUrl());

        ResendPage resendPage = new ResendPage(driver);
        resendPage.setRandomAnswerToQASection();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertFalse("error with send answer for help us form", resendPage.isRequestAnswerAvailable());

        resendPage.clickResendEmail();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertFalse("Error with click resend email button", resendPage.isResendEmailButtonAvailable());

        Assert.assertEquals("wrong twitter href", "https://twitter.com/wrike", resendPage.getLinkByTwitterButton());

        Assert.assertEquals("wrong twitter icon",
                "/content/themes/wrike/dist/img/sprite/vector/footer-icons.symbol.svg?v2#twitter",
                resendPage.getTwitterIconLink());
    }
}
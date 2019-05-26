package pageobject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.RandomString;

import java.util.Random;

public class HomePage {

    private WebDriver driver;

    @FindBy(xpath = "/html/body/div[1]/header/div[3]/div[2]/div/div/div[2]/div/form/button")
    private WebElement getStartedButton;

    private WebElement enterEmailForm;

    private WebElement createAccountButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //getStarted must be clicked
    public void setEnterEmailForm() {
        enterEmailForm = driver.findElement(By.xpath("/html/body/div[4]/div/form/label[1]/input"));
    }

    //getStarted must be clicked
    public void setCreateAccountButton() {
        createAccountButton = driver.findElement(By.xpath("/html/body/div[4]/div/form/label[2]/button"));
    }

    public void sendEmail(String email){
        enterEmailForm.sendKeys(email);
    }

    public void createAccountWithRandomEmail() {
        getStartedButton.click();
        setEnterEmailForm();
        sendEmail(generateEmail());
        setCreateAccountButton();
        createAccountButton.click();
    }

    private String generateEmail(){
        Random random = new Random();
        return RandomString.generateRandomString(random.nextInt(10) + 1)  + "+wpt@wriketask.qaa";
    }

}


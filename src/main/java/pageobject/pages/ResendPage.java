package pageobject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.RandomString;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ResendPage {
    private WebDriver driver;

    @FindBy(xpath = "/html/body/div[1]/main/div/div/div[2]/div/div[1]/p[3]/button")
    private WebElement resendEmailButton;

    @FindBy(xpath = "/html/body/div[1]/div/div[3]/div/div[1]/div/ul/li[1]/a")
    private WebElement twitterClass;

    @FindBy(xpath = "/html/body/div[1]/div/div[3]/div/div[1]/div/ul/li[1]/a/svg/use")
    private WebElement twitterIcon;

    @FindBy(xpath = "/html/body/div[1]/main/div/div/div[2]/div/div[2]/div/form/button")
    private WebElement submitAnswer;

    @FindBy(xpath = "/html/body/div[1]/main/div/div/div[2]/div/div[2]/div/form")
    private WebElement answerRequest;

    private List<WebElement> interestButtons;

    private List<WebElement> teamMemberButtons;

    private List<WebElement> processFlowButtons;

    private WebElement otherForm;



    public ResendPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void setInterestButtons(){
        interestButtons = new ArrayList<>();
        addButtonToList(interestButtons, "/html/body/div[1]/main/div/div/div[2]/div/div[2]/div/form/div[1]/label", 2);

    }

    public void setTeamMemberButtons(){
        teamMemberButtons = new ArrayList<>();
        addButtonToList(teamMemberButtons, "/html/body/div[1]/main/div/div/div[2]/div/div[2]/div/form/div[2]/label", 5);
    }

    public void setProcessFlowButtons() {
        processFlowButtons = new ArrayList<>();
        addButtonToList(processFlowButtons, "/html/body/div[1]/main/div/div/div[2]/div/div[2]/div/form/div[3]/label", 3);
    }

    public void clickResendEmail(){
        resendEmailButton.click();
    }

    public void setOtherForm(){
        otherForm = driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div/div[2]/div/form/div[3]/label[3]/button/span/input"));
    }

    public void setRandomAnswerToQASection() {
        setInterestButtons();
        setTeamMemberButtons();
        setProcessFlowButtons();
        Random random = new Random();
        interestButtons.get(random.nextInt(interestButtons.size())).click();
        teamMemberButtons.get(random.nextInt(teamMemberButtons.size())).click();
        int processButton = random.nextInt(processFlowButtons.size());
        processFlowButtons.get(processButton).click();
        if (processButton == processFlowButtons.size() - 1) {
            setOtherForm();
            otherForm.sendKeys(RandomString.generateRandomString(random.nextInt(50)));
        }
        submitAnswer.click();
    }

    public String getLinkByTwitterButton() {
        return twitterClass.getAttribute("href");
    }

    public String getTwitterIconLink(){
        return twitterClass.findElement(By.xpath("./svg/use")).getAttribute("xlink:href");
    }

    public boolean isResendEmailButtonAvailable(){
        return resendEmailButton.isDisplayed();
    }

    public boolean isRequestAnswerAvailable(){
        return answerRequest.isDisplayed();
    }

    private void addButtonToList(List<WebElement> buttonsList, String prefixXPath, int countButtons){
        for (int i = 1; i <= countButtons; i++) {

            buttonsList.add(driver.findElement(By.xpath(prefixXPath + String.format("[%d]/button", i))));
        }
    }

}

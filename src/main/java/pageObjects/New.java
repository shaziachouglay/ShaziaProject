package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilityClasses.CommonMethods;

public class New extends CommonMethods {

    @FindBy(xpath = "//input[@type='email']")
    WebElement emailId;

    @FindBy(xpath = "//input[@type='password']")
    WebElement password;

    @FindBy(xpath = "//input[@type='submit']")
    WebElement submitBtn;

    @FindBy(css = "#idSIButton9")
    WebElement yesBtn;

    @FindBy(css = "#ShellDynamics365_link > span > ohp-icon-font > span")
    WebElement dynamicsLink;

    @FindBy(xpath = "//div/../div[@aria-label='Customer Service']/a")
    WebElement customerServiceLink;

    @FindBy(xpath = "//div[@id='siteMapPanelBodyDiv']/ul[@id]/li[1]/ul/li[2]")
    WebElement activities;

    public void enterEmailAndPassword(String id ,String passwrd){
       emailId.sendKeys(id);
       explicitWait(500);
       submitBtn.click();
       password.sendKeys(passwrd);
       explicitWait(500);
       submitBtn.click();
       yesBtn.click();
       waitForPageLoadToComplete();


    }


    public void pageLoad() throws InterruptedException {
        waitForAjax();
        explicitWait(4000);
        System.out.println(getDriver().getTitle());
    }

    public void clickOnActivities(){
        activities.click();
        explicitWait(5000);
        System.out.println(getDriver().getTitle());
    }
}

package stepDefinitions.firstSteps;

import baseTest.BaseTest;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MyStepdefs extends BaseTest {
    @Given("^I am on microsoft poortal portal$")
    public void iAmOnMicrosoftPoortalPortal() {

    }



    @Then("^I must see I am successfully logged in$")
    public void iMustSeeIAmSuccessfullyLoggedIn() {
    }

    @When("^I enter valid  \"([^\"]*)\" and valid \"([^\"]*)\"$")
    public void iEnterValidAndValid(String arg0, String arg1) {

        aNew.enterEmailAndPassword(arg0,arg1);
    }



    @Then("^I wait for page load to complete$")
    public void iWaitForPageLoadToComplete() throws InterruptedException {
        aNew.pageLoad();
    }

    @Then("^I click on Activities$")
    public void iClickOnActivities() {
        aNew.clickOnActivities();
    }
}

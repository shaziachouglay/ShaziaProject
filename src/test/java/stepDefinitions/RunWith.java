package stepDefinitions;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@CucumberOptions(   dryRun = false ,
        strict = false ,
        features = ".",
        tags = "@something",
        plugin = {"com.cucumber.listener.ExtentCucumberFormatter:"},
        format = {"html:target/site/cucumber-pretty","json:target/cucumber.json"})
@org.junit.runner.RunWith(Cucumber.class)

public class RunWith {


    /**
     * This is the Runner Class from where the Framework can be triggered.
     * To run an individual Scenario right click on the Scenario and hit run
     * To run as an individual Feature right click on the on the word 'Feature' in the feature file and hit run.
     * To run as a suite of test, tag the desired Scenarios or Features with a tag and place the tag in Cucumber Option in the RunWith.class (as above),
     * then right click on the class name and hit run.
     * To run as a maven project, use 'mvn clean verify' in the project directory from command line.
     * When running from the command line as a maven project you can also define parameters like tags and browser choice.
     */
}

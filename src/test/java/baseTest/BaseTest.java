package baseTest;

import org.apache.log4j.Logger;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;
import pageObjects.New;
import utilityClasses.FrameworkPropertyConfigurator;
import utilityClasses.LoggingFactory;

public class BaseTest {
    public New aNew = new New();

    /**
     * The testNG Assertion Class provides us with Hard Assertions.
     *    -----Use hardAsser to terminate the programme when the assertion fails.
     */
    protected Assertion hardAssertion = new Assertion();

    /**
     *The testNG SoftAssertions Class provides us with Soft Assertions.
     *    -----Use softAssert to display a WARNING on failure and continue the test case.
     */
    protected SoftAssert softAssert = new SoftAssert();

    private static Logger log = LoggingFactory.getLogger();

    protected void info(Object message){
        if(FrameworkPropertyConfigurator.getPropertiesByFileName(FrameworkPropertyConfigurator.propertyFileName.config, FrameworkPropertyConfigurator.keys.LOG_SWITCH).equalsIgnoreCase("ON")){
            log.info(message);}
    }

    public void getMeCurrentMethodInfo(){
        Thread.currentThread().getContextClassLoader();
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        String ClassFromWhereMethodCallIsMade = Thread.currentThread().getStackTrace()[2].getFileName();
        info(   "\n-------------------------------------------------------------------------"+"" +
                "\n|Information for the current running Method                      "+"" +
                "\n|Method Name is             |"+methodName+"                             "+"" +
                "\n|Class Calling this method  |"+ClassFromWhereMethodCallIsMade+"         "+"" +
                "\n-------------------------------------------------------------------------");
    }
}

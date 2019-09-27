
package utilityClasses;

import com.google.common.base.Function;
import customExceptions.GetFrameworkPropertyException;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 * This abstract class holds the Generic Functionality Only and no application specific functionality should be placed in here.
 * For Generic Functionality specific to Application should be placed in CommomMethods.java.
 */

public abstract class Page extends BrowserFactory {


    protected Logger logger = LoggingFactory.getLogger();

    public  static void info(Object message){
        if(FrameworkPropertyConfigurator.getPropertiesByFileName(FrameworkPropertyConfigurator.propertyFileName.config,"log_switch").equalsIgnoreCase("ON")){
            LoggingFactory.logger.info(message);}
    }

    protected static void warn(Object message){
        BrowserFactory.logger.warn(message);
    }
    //------------------------------------------------ Select Methods Below--------------------------------------------------
    /**
     *
     * @param element is the element for the select or dropdown field
     * @param visibleText is the option to be selected in the dropdown
     */
    public void selectElementByVisibleText(WebElement element , String visibleText){
        Select select = new Select(element);
        select.selectByVisibleText(visibleText);
        info("Selected text is :"+ visibleText);
    }

    /**
     *
     * @param element takes an element of the dropdown.
     * @param visibleValue takes the option value in the dropdown.
     */
    public void selectElementByVisibleDropDownValue(WebElement element , String visibleValue){
        try{
            waitForElementToBeVisibleloop(element);
            cleanAndRebuildElement(element);
            Select select = new Select(element);
            select.selectByValue(visibleValue);
            info("Selected value from drop down is :"+ visibleValue);}
        catch (StaleElementReferenceException e){
            e.printStackTrace();
            warn("StaleElementReferenceException occurred");
        }catch (NoSuchElementException e){
            e.printStackTrace();
            warn("NoSuchElementException occurred");
        }
    }

    /**
     *
     * @param parentElement is the parent element for the page this method will be used in
     * @param visibleText is the dropdown visible text that need to be clicked
     * @param fieldsetName is the name of the dropdown field
     */
    public void selectElementByVisibleTextAndFielsetName(WebElement parentElement  , String visibleText , String fieldsetName){
        try {
            WebElement element = parentElement.findElement(By.xpath(".//div/label[contains(.,'"+fieldsetName+"')]/../../div/div/select"));
            Select select = new Select(element);
            select.selectByVisibleText(visibleText);
            info("Selected text is :" + visibleText);
        }
        catch (Exception e ){
            e.printStackTrace();
            takeScreenshot();
            info("Screen shot has been taken !");
        }
    }

    //------------------------------------------------ getter / getValue / getText Value Methods Below--------------------------------------------------

    /**
     * use these ENUM constants to configure the waits for the getWebDriver
     */
    public enum timeUnitSeconds{S_SECONDS,M_SECONDS,L_SECONDS,Xl_SECONDS,XXl_SECONDS,XXXl_SECONDS,VARIBALE_SECONDS,
        S_MILLISECONDS,M_MILLISECONDS,L_MILLISECONDS,XL_MILLISECONDS,XXL_MILLISECONDS,XXXL_MILLISECONDS,VARIBALE_MILLISECONDS}

    /**
     *  this method reads from the Config file and sets the local variables
     */
    public int getSyncTimeUnit(timeUnitSeconds timeUnitSeconds){
        int seconds = 0;

        switch(timeUnitSeconds){
            case S_SECONDS:seconds=Integer.parseInt(FrameworkPropertyConfigurator.getPropertiesByFileName(FrameworkPropertyConfigurator.propertyFileName.config,"S_sec"));
                info(" Synchronised wait set for "+seconds+"seconds. ");
                break;
            case M_SECONDS:seconds=Integer.parseInt(FrameworkPropertyConfigurator.getPropertiesByFileName(FrameworkPropertyConfigurator.propertyFileName.config,"M_sec"));
                info(" Synchronised wait set for "+seconds+"seconds. ");
                break;
            case L_SECONDS:seconds=Integer.parseInt(FrameworkPropertyConfigurator.getPropertiesByFileName(FrameworkPropertyConfigurator.propertyFileName.config,"L_sec"));
                info(" Synchronised wait set for "+seconds+"seconds. ");
                break;
            case Xl_SECONDS:seconds=Integer.parseInt(FrameworkPropertyConfigurator.getPropertiesByFileName(FrameworkPropertyConfigurator.propertyFileName.config,"XL_sec"));
                info(" Synchronised wait set for "+seconds+"seconds. ");
                break;
            case XXl_SECONDS:seconds=Integer.parseInt(FrameworkPropertyConfigurator.getPropertiesByFileName(FrameworkPropertyConfigurator.propertyFileName.config,"XXL_sec")                );
                info(" Synchronised wait set for "+seconds+"seconds. ");
                break;
            case XXXl_SECONDS:seconds=Integer.parseInt(FrameworkPropertyConfigurator.getPropertiesByFileName(FrameworkPropertyConfigurator.propertyFileName.config,                             "XXXL_sec"));
                info(" Synchronised wait set for "+seconds+"seconds. ");
                break;
            case VARIBALE_SECONDS:seconds=Integer.parseInt(FrameworkPropertyConfigurator.getPropertiesByFileName(FrameworkPropertyConfigurator.propertyFileName.config,                             "variable_sec"));
                info(" Synchronised wait set for "+seconds+"seconds. ");
                break;
            case S_MILLISECONDS:seconds=Integer.parseInt(FrameworkPropertyConfigurator.getPropertiesByFileName(FrameworkPropertyConfigurator.propertyFileName.config,                             "S_milli"));
                info(" Synchronised wait set for "+seconds+" milliseconds. ");
                break;
            case M_MILLISECONDS:seconds=Integer.parseInt(FrameworkPropertyConfigurator.getPropertiesByFileName(FrameworkPropertyConfigurator.propertyFileName.config,                             "M_milli"));
                info(" Synchronised wait set for "+seconds+" milliseconds. ");
                break;
            case L_MILLISECONDS:seconds=Integer.parseInt(FrameworkPropertyConfigurator.getPropertiesByFileName(FrameworkPropertyConfigurator.propertyFileName.config,                             "L_milli"));
                info(" Synchronised wait set for "+seconds+"milliseconds. ");
                break;
            case XL_MILLISECONDS:seconds=Integer.parseInt(FrameworkPropertyConfigurator.getPropertiesByFileName(FrameworkPropertyConfigurator.propertyFileName.config,                             "XL_milli"));
                info(" Synchronised wait set for "+seconds+"milliseconds. ");
                break;
            case XXL_MILLISECONDS:seconds=Integer.parseInt(FrameworkPropertyConfigurator.getPropertiesByFileName(FrameworkPropertyConfigurator.propertyFileName.config,                             "XXL_milli"));
                info(" Synchronised wait set for "+seconds+"milliseconds. ");
                break;
            case XXXL_MILLISECONDS:seconds=Integer.parseInt(FrameworkPropertyConfigurator.getPropertiesByFileName(FrameworkPropertyConfigurator.propertyFileName.config,                             "XXXL_milli"));
                info(" Synchronised wait set for "+seconds+"milliseconds. ");
                break;
            case VARIBALE_MILLISECONDS:seconds=Integer.parseInt(FrameworkPropertyConfigurator.getPropertiesByFileName(FrameworkPropertyConfigurator.propertyFileName.config,                             "varibale_milli"));
                info(" Synchronised wait set for "+seconds+"milliseconds. ");
                break;
            default: info("The time unit was improperly configured : Please check the enum values to the property file.");
                warn(" Synchronised wait set for "+seconds+"seconds. WARNING! ");
                try {
                    throw new GetFrameworkPropertyException();
                } catch (GetFrameworkPropertyException fpce) {
                    fpce.printStackTrace();
                }
                break;
        }
        return seconds;
    }

    /**
     *
     * @param element is the element from which value needs to be extracted with fluent wait and ignoring StaleElementReferenceException
     * @return the value extracted from the webElement
     */
    public static String getElementTextWithRetry(final WebElement element) {

        FluentWait<WebElement> customWait = new FluentWait<WebElement>(element).withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS).ignoring(StaleElementReferenceException.class);
        return customWait.until(new Function<WebElement, String>() {
            public String apply(WebElement element) {
                return element.getText();
            }
        });
    }
    /**
     *
     * @param element is the WebElement whose attribute value needs to be obtained.
     * @param attributeName is the name of the attribute for the WebElement.
     * @return the String value of the attributes value.
     */
    public String getElementAttributeValueWithRetry(final WebElement element, final String attributeName) {
        cleanAndRebuildElement(element);
        FluentWait<WebElement> customWait = new FluentWait<WebElement>(element).withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS).ignoring(StaleElementReferenceException.class);

        return customWait.until(new Function<WebElement, String>() {
            public String apply(WebElement element) {
                return element.getAttribute(attributeName);
            }
        });
    }

    public String getElementAttributeValueWithRetry1(final WebElement element, final String attributeName){
        int counter = 0;
        String value ="";
        do {
            try{
                explicitWait(150);
                waitForJStoLoad();waitForAjax();
                value= element.getAttribute(attributeName);
            }catch (StaleElementReferenceException e){
                counter++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while(counter<20);
        return value;
    }

    /**
     * @param elementList is the  list of elements
     * @param elementName name of the element
     * @param pageName used for logging message.
     * @return the count of WebElements present.
     */

    public int getElementCount(List<WebElement> elementList, String elementName , String pageName){
        try {
            waitForAjax();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int count = elementList.size();
        info("The number of elements on the "+pageName+" are : "+count);
        return count;
    }

    public int getElementCount(List<WebElement> elementList, String elementName ){
        try {
            waitForAjax();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int count = elementList.size();
        info("The number of elements on the are : "+count);
        return count;
    }

    /**
     * @param elementList is the list of WebElements.
     * @param expectedSize is the size of the list expected.
     * @return the List of web elements.
     */
    public List<WebElement> refreshListOfWebElement(List<WebElement> elementList, String expectedSize){

        if(Integer.parseInt(expectedSize ) != (elementList.size())){
            for (WebElement iteratorElement : elementList){
                iteratorElement.isDisplayed();
                explicitWait(100);
            }
        }
        return elementList;
    }

    /**
     * @param elementInput this is the input element in which the value will be set
     * @param inputValue is the String value to be inputted in the element
     */
    public void setValueInElementInputJS(WebElement elementInput , String inputValue){
        try {
            JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("arguments[0].value = '';", elementInput);
            elementInput.sendKeys(inputValue);

        }
        catch (Exception e){
            e.printStackTrace();
            takeScreenshot();
            info("Exception Occurred !");
        }
    }

    /**
     * this is trial method
     * @param elementInput is the element in which  the value needs to be input.
     * @param inputValue this standard Selenium method does not work as it is working on a hit text element.
     */
    public void setValueInElementInput1(WebElement elementInput , String inputValue){
        try {
            elementInput.click();
            elementInput.clear();
            elementInput.sendKeys(inputValue);
        }
        catch (Exception e){
            e.printStackTrace();
            takeScreenshot();
            info("Exception Occurred !");
        }
    }


    /**
     * @param element is the input WebElement in which the value is to be set.
     * @param inputValue is the value to ob inserted in the input element.
     */
    public void setValueInInputFieldByWebElement(WebElement element , String inputValue){
        try {
            refreshWebElement(element);
            setValueInElementInputJS(element,inputValue);
            explicitWait(200);
            element.sendKeys(Keys.TAB);
            element.sendKeys(Keys.SHIFT , Keys.TAB);
            info("\nThe Value set in the input box is ' "+ inputValue+" '.");
        }
        catch (NoSuchElementException nsee){
            nsee.printStackTrace();
            info(nsee.toString());
        }
        info("\nThe Value set in the input box is "+ inputValue);
    }

    /**
     * @param element check that the element is present and displayed
     * @return in boolean the present and displayed state of the element
     * this also handles the stale element reference exception.
     * this also waits for the element to be visible in the DOM
     */
    protected  boolean isElementPresentAndDisplayed(WebElement element){
        boolean isPresentAndDisplayed = false;
        try {
            cleanAndRebuildElement(element);
            waitForElementToBeVisible(element);
            isPresentAndDisplayed = element.isDisplayed();
            info("element displayed status is : " + isPresentAndDisplayed);
        }
        catch (NoSuchElementException nsee){
            warn("The current displayed status of the WebElement is "+isPresentAndDisplayed);
            takeScreenshot();
        }
        return isPresentAndDisplayed;
    }
    /**
     * @param elementToBeLoaded : checks the loaded status of the element.
     * @return the loaded webelement. Use other methods like waitForElementToBeVisible.
     */
    public WebElement isElementLoaded(WebElement elementToBeLoaded){
        WebDriverWait wait = new WebDriverWait(driver, 60);
        WebElement element = wait.until(ExpectedConditions.visibilityOf(elementToBeLoaded));
        return elementToBeLoaded;
    }

    /**
     * @param element is the element which will be clicked.
     *                This method wait for the element to be clickable using fluent wait and ignoring StaleElement exception
     *                This method is also configurable for polling time
     */
    public void clickElementWithRetry(final WebElement element) {
        cleanAndRebuildElement(element);
        // Click Element With Retry : this will wait for the element to be clickable until timeout
        FluentWait<WebElement> customWait = new FluentWait<WebElement>(element)
                .withTimeout(20, TimeUnit.SECONDS)
                .pollingEvery(200, TimeUnit.MILLISECONDS).ignoring(StaleElementReferenceException.class);

        customWait.until(new Function<WebElement, Boolean>() {
            public Boolean apply(WebElement element) {
                try {
                    element.click();
                    return true;
                } catch (Exception e) {
                    //takeScreenshot();
                    return false;
                }
            }
        });
    }

    /**
     * @param element is the Webelement which needs to be clicked with JavaScript Executor.
     */
    public void clickElementWithJS1(WebElement element) {
        long startTime = System.currentTimeMillis();
        do {
            try {
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click();", element);
            } catch (StaleElementReferenceException e) {
                explicitWait(100);
                info("refreshing web element in progress !!!");
            }
        }while (startTime-System.currentTimeMillis()>10000);

    }

    /**
     * @param element is the WebElement which needs to be clicked with JavaScript.
     */
    public void clickElementWithJS2(WebElement element) {
        try {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element);
        } catch (StaleElementReferenceException e) {
            explicitWait(200);
            info("Stale element exception with 'clickElementWithJS' methods in class : "+ this.getClass());
        }
    }

    /**
     * @param element is the WebElement which needs to be clicked with JavaScript.
     */
    public void clickElementWithJS(WebElement element){
        try{JavascriptExecutor _driver = (JavascriptExecutor)getDriver();
            waitForElementToBeVisible(element);
            waitForPageLoadToComplete();
            element.click();}
        catch (StaleElementReferenceException sere){
            info("Stale element reference Exception !");
        }
    }

    /**
     * @param element waits or the element to be visible using fluent wait and polling time
     *                This returns nothing , it only provides wait mechanism for element visibility
     */
    public void waitForElementToBeVisible(final WebElement element){
        FluentWait<WebElement> customWait = new FluentWait<WebElement>(element)
                .withTimeout(20, TimeUnit.SECONDS)
                .pollingEvery(200, TimeUnit.MILLISECONDS).ignoring(StaleElementReferenceException.class);
        customWait.until(new Function<WebElement, Boolean>() {
            public Boolean apply(WebElement element) {
                try {
                    element.isDisplayed();
                    return true;
                }
                catch (Exception e) {
                    return false;
                }
            }
        });
    }

    public void waitForElementToBeVisibleloop(WebElement element){
        explicitWait(50);
        int counter=0;
        do{
            counter++;
            explicitWait(50);
            try{
                element.isDisplayed();
            }
            catch (StaleElementReferenceException e){
            }
        }while (counter<20);
    }
    /**
     * @param milliseconds provides waiting mechanism when extra wait is required
     *                 can be used explicitly at desired points
     */
    public void explicitWait(int milliseconds){
        try {
            Thread.sleep(milliseconds);
        }
        catch(InterruptedException ie){
            ie.printStackTrace();
            info("Interrupted Exception Occurred !");
        }
    }

    /**
     *this is a overloaded method.
     * @param milliseconds as a long inout type.
     */
    public void explicitWait(long milliseconds){
        try {
            Thread.sleep(milliseconds);
        }
        catch(InterruptedException ie){
            ie.printStackTrace();
            info("Interrupted Exception Occurred !");
        }
    }

    /**
     *
     * @param milliseconds : this is when we read from the configuration file and parses into Integers.
     */
    public void explicitWait(String milliseconds){
        try {
            Thread.sleep(Integer.parseInt(milliseconds));
        }
        catch(InterruptedException ie){
            ie.printStackTrace();
            info("Interrupted Exception Occurred !");
        }
    }
    /**
     * This method wais for the page to be laoded ; waits for the document reference from the DOM
     * waits until the refrenced time inside the method. use this after the Save and Next button click to ensure the next page is successfully loaded .
     */
    public void waitForPageLoadToComplete() {
        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait.until(new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver driver) {
                System.out.println("Current Window State       : "
                        + String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")));
                return String
                        .valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
                        .equals("complete");
            }
        });
        explicitWait(50);
    }


    /**
     * @param element the element on which explicit wait needs to be performed.
     * @param timeInSeconds the time in seconds to be waited upon.
     */
    public static void explicitWaitForElement(WebElement element, int timeInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * @throws InterruptedException
     * This method is to wait for the Ajax call to complete avoiding stale values to be populated.
     */
    public void waitForAjax() throws InterruptedException
    {
        while (true)
        {
            Boolean ajaxIsComplete = (Boolean) ((JavascriptExecutor)driver).executeScript("return jQuery.active == 0");
            if (ajaxIsComplete){
                info("The Ajax Call is now complete. ");
                break;
            }
            Thread.sleep(150);
        }
    }

    /**
     * use this to che check if the Javascript is complete and is loaded.
     */
    public void waitForJStoLoad() {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        if (js.executeScript("return document.readyState").toString().equals("complete")){
            info("Page Is loaded. JS is complete ");
            return;
        }
        for (int i=0; i<25; i++){
            explicitWait(150);
            if (js.executeScript("return document.readyState").toString().equals("complete")){
                break;
            }
        }
    }

    /**
     * @param element Takes the WebElement in which the wait for visible is to be performed.
     */
    public void waitForElementToBeVisible2(WebElement element){
        int counter = 0 ;
        do {
            try {
                explicitWait(200);
                counter++;
                element.isDisplayed();
                break;
            } catch (StaleElementReferenceException sere) {
                sere.toString();
                explicitWait(200);
                warn(" Stale Element Exception Occured and Caught, Refreshing the Element the  !\n"+ counter+" th Time !");
                warn(sere);
            }
        }while(counter<20);
    }

    /**
     * @param element is the element where the driver needs to be moved. Moving the focus of the driver
     */
    protected void moveToElement(WebElement element){
        Actions action = new Actions(this.getDriver());
        try{
            action.moveToElement(element);
            waitForElementToBeVisibleloop(element);
        }
        catch (Exception e){
            e.printStackTrace();
            info("The driver could not move the element : Exception Occurred !  , Please check the locator.");
        }
    }

    /**
     *
     * @param element is the element where the driver will scroll into view.
     *                this method will scroll the page until the element is in view.
     */
    public void scrollToElement(WebElement element){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        explicitWait(500);
        info("Scrolling to element in view");
    }

    /**
     *
     * @return the instance of the Actions class.
     */
    public Actions getActionsInstance(){
        Actions actions = new Actions(driver);
        return actions;
    }
    /**
     * @param element . the element you want to refresh.
     * @return the refresh element . This will loop until element is refreshed
     */
    public WebElement refreshWebElement1(WebElement element){
        long time = 0 ;
        do {
            time = System.currentTimeMillis();
            {
                try{
                    explicitWait(300);
                    waitForJStoLoad();
                    time = time+300;
                    element.click();
                    break;
                }catch (StaleElementReferenceException sere){
                    warn("\nException Occured !!");
                    sere.printStackTrace();
                }
            }
        }while (time < 5000);
        return element;
    }

    /**
     * @param element The WebElement to be refreshed.
     * @return the refreshed WebElement.
     */
    public WebElement refreshWebElement(WebElement element){
        int counter = 0;
        do { { try{
            explicitWait(300);
            counter++;
            element.isDisplayed();
            break;
        }catch (StaleElementReferenceException sere ){
            warn("\nException Occurred while refreshing element  !!");
            explicitWait(100);
        }
        }
        }while (counter<20);
        return element;
    }

    /**
     * @param element : webelement to check status. COME BACK TO THIS @aniket : Needs some TLC (Tender Loving Care).
     */
    public void checkElementStatus(WebElement element){
        refreshWebElement(element);
        waitForJStoLoad();
        try {
            waitForAjax();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param element is the element that needs to be rebuilt.
     * @return the rebuilt fresh element.
     */
    public WebElement cleanAndRebuildElement(final WebElement element){
        WebElement e2 = null;
        int counter = 0 ;
        do{
            try{
                e2=element;
                e2.isDisplayed();
                info("refreshing element....");
                break;
            }catch (StaleElementReferenceException sere){
                e2=null;
                explicitWait(150);
                counter++;
            }catch(java.util.NoSuchElementException e){
                e2=null;
                explicitWait(150);
                counter++;
            }
        }while(counter<20);
        return e2;
    }

    /**
     *
     * @param element to be refreshed.
     * @param milliseconds is the time in milliseconds you want to try refreshing the element
     * @return the refreshed webElement.
     */
    public WebElement cleanAndRebuildElement(final WebElement element, long milliseconds){
        WebElement e2;
        long startTime = System.currentTimeMillis();
        do{
            try{
                e2=element;
                e2.isDisplayed();
                info("refreshing element....");
                break;
            }catch (StaleElementReferenceException sere){
                e2=null;
                explicitWait(100);

            }
        }while(System.currentTimeMillis()-startTime > milliseconds);
        return e2;
    }


    /**
     * @param element : the webelement to clean.
     * @return : a cleaned element by waiting for page to load, moving to the element location,
     * waiting for any Ajax call to complete and refreshWebElement invoked.
     * Use the refresh webelement before using this one.
     */
    public WebElement cleanElement (WebElement element){
        try{
            waitForPageLoadToComplete();
            moveToElement(element);
            waitForAjax();
            waitForElementToBeVisibleloop(element);
            refreshWebElement(element);
            info("refreshing element....");
        }catch (Exception e){
            e.printStackTrace();
            warn("Exception Occurred !!");
        }
        return element;
    }

    /**
     *
     * @param elementToTabOn the WebElement on which the keyboard key needs to be activated.
     * @param keys is the enum Keys to be used.
     */
    public void hitKeyboardButton(WebElement elementToTabOn, Keys keys){
        elementToTabOn.sendKeys(keys);
        info("clicked Tab button on Keyboard"+keys.name().toString());
    }

    /**
     *
     * @param element the element that is not to be visible.
     * @return the boolean status of the element invisiblity
     */
    public boolean isElementNotPresent(WebElement element){
        Boolean result=false;
        WebDriver dri = getDriver();
        try {
            dri.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            element.click();
        }catch (NoSuchElementException e){
            result=true;
        }finally {
            dri.manage().timeouts().implicitlyWait(Integer.parseInt(FrameworkPropertyConfigurator.getPropertiesByFileName(FrameworkPropertyConfigurator.propertyFileName.config,"implicitWait")),TimeUnit.SECONDS);
        }
        return result;
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

    public WebElement waitForElementVisibility(WebElement element){
        element = new WebDriverWait(getDriver(),10).until(ExpectedConditions.visibilityOf(element));
        info("Waiting on element visibility.");
        return element;
    }
}
package utilityClasses;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SeleniumUtils extends FileAppender {

    static WebDriver driver;

    SeleniumUtils() {
        PageFactory.initElements(driver, this);
    }

    public static String timestamp() {
        return new SimpleDateFormat("dd-MMMM-YYYY : HH-mm-ss :").format(new Date());
    }

    public void takeScreenshot() {

        if (FrameworkPropertyConfigurator.getPropertiesByFileName(FrameworkPropertyConfigurator.propertyFileName
                .config, FrameworkPropertyConfigurator.keys.SCREENSHOT_SWITCH).equalsIgnoreCase("ON")) {

            Logger logger = LoggingFactory.getLogger();
            File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                String applicationName = FrameworkPropertyConfigurator.getPropertiesByFileName(FrameworkPropertyConfigurator.propertyFileName.config, "ApplicationName");
                FileUtils.copyFile(sourceFile, new File("src/testScreenshots/" + applicationName + timestamp() + ":" + driver.getTitle().trim().replaceAll(" ", "") + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("screenshot at" + timestamp() + "for" + driver.getTitle());


        }
    }
}








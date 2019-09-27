package utilityClasses;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class handles the logging mechanism in the framework
 * It is configured thru the log 4j.properties file in the configuration directory.
 */


public class LoggingFactory extends SeleniumUtils{
    /**
     * Sets the defined user for the logger.
     */
    final static Logger logger = Logger.getLogger(FrameworkPropertyConfigurator.getPropertiesByFileName(FrameworkPropertyConfigurator.propertyFileName.config,"logUser"));

    public static Logger getLogger(){
        System.setProperty("org.apache.logging.log4j.simplelog.StatusLogger.level","INFO");
        PropertyConfigurator.configure("log4j2.properties");
        return logger ;
    }

    /**
     * This static block will be executed when the class is loaded
     * The dateFormat will be set to be used for logging purposes.
     * The
     */
    static{
        SimpleDateFormat dateFormat = new SimpleDateFormat("EE dd-MM-yyyy HH-mm-ss");
        System.setProperty("current.date", dateFormat.format(new Date()));
        System.setProperty("application.name",FrameworkPropertyConfigurator.getPropertiesByFileName(FrameworkPropertyConfigurator.propertyFileName.config,"ApplicationName"));
        System.setProperty("user.name",FrameworkPropertyConfigurator.getPropertiesByFileName(FrameworkPropertyConfigurator.propertyFileName.config,"logUser"));
    }
}















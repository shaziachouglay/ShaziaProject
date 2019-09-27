package utilityClasses;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Use this class for its method to get the properties from .properties file.
 *
 */

public class FrameworkPropertyConfigurator {
    /**
     * This Enum is constant used to fetch the properties file path
     */

    public enum propertyFileName {

        config("src/configuration/config.properties"),

        log4j("src/configuration/log4j2.properties");

        private final String stringValue;

        propertyFileName(String s) {
            this.stringValue = s;
        }


        public String toString() {
            return stringValue;
        }
    }

        public enum keys{
            LOG_SWITCH("log_switch"), SCREENSHOT_SWITCH("screenshot_switch"),
            REPORT_SWITCH("report_switch");

            private final String stringValue;
            keys(String s){this.stringValue = s;}
            public String toString(){ return stringValue;}
        }

        public static String getPropertiesByFileName (propertyFileName fileName,String key){
            Properties properties = new Properties();
            String value = null;
            try {
                FileInputStream fileInputStream = new FileInputStream(fileName.toString());
                properties.load(fileInputStream);
                value = properties.getProperty(key);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return value;

        }

        public static String getPropertiesByFileName(propertyFileName fileName,keys key){
            Properties properties = new Properties();
            String  value = null;
            try {
                FileInputStream fileInputStream = new FileInputStream(fileName.toString());
                properties.load(fileInputStream);
                value = properties.getProperty(key.toString());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return value;
        }
    }




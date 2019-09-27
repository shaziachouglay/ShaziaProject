package customExceptions;

import org.apache.log4j.Logger;
import utilityClasses.LoggingFactory;

public class GetFrameworkPropertyException  extends Exception {

    Logger logger = LoggingFactory.getLogger();

    public GetFrameworkPropertyException() {
        logger.info("GetFrameworkPropertyException Occurred !");
        printStackTrace();
    }


}

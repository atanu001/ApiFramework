package apiAutomation.utility;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;



public class Log {
    private static final Logger logger = (Logger) LogManager.getLogger(Log.class);

    public static void info(String message) {
        logger.info(message);
    }

    public static void warning(String message) {
        logger.warn(message);
    }

    public static void error(String message, Throwable throwable) {
        logger.error(message, throwable);
    }
}

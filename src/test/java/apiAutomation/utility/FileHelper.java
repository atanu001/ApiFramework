package apiAutomation.utility;



import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileHelper {

    public static Properties prop;

    /*public Properties init_prop(){
        prop = new Properties();

        try{
            FileInputStream fis = new FileInputStream(Constants.propertyFilePath);
            prop.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("File not found " + e);
        }
        return prop;
    }*/

    public static void loadProperties() {
        prop = new Properties();
        try (InputStream input = FileHelper.class.getClassLoader().getResourceAsStream(Constants.propertyFilePath)) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        if (prop == null) {
            loadProperties();
        }
        return prop.getProperty(key);
    }
}

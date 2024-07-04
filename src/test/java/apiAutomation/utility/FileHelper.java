package apiAutomation.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileHelper {

    public Properties prop;

    public Properties init_prop(){
        prop = new Properties();

        try{
            FileInputStream fis = new FileInputStream(Constants.propertyFilePath);
            prop.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("File not found " + e);
        }
        return prop;
    }
}

package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {
    // read the config properties file
    Properties properties ;

    String path = System.getProperty("user.dir") + "\\Configuration\\config.properties";

    // constructor
    public ReadConfig(){
        properties = new Properties();
        // read the file
        try {
            FileInputStream fis = new FileInputStream(path);
            try {
                properties.load(fis);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // read the values creates in config properties file
    public String getBaseUrl(){
       String value = properties.getProperty("baseUrl");
       if(value!=null)
       return value;
       else
           throw new RuntimeException("url not specified in config file");
    }

    public String getBrowser(){
        String value = properties.getProperty("browser");
        if(value!=null)
            return value;
        else
            throw new RuntimeException("browser not specified in config file");
    }

    public String getOS() {
        return properties.getProperty("operatingSystem");
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}

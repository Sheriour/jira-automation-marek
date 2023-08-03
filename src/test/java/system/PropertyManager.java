package system;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {

    static  PropertyManager instance;

    public static PropertyManager GetInstance(){
        if (instance == null) instance = new  PropertyManager();
        return instance;
    }

    Properties properties;

    private PropertyManager(){
        String path = System.getProperty("user.dir") + "/src/test/resources/jiratest.properties";
        try {
            InputStream input = new FileInputStream(path);
            properties = new Properties();
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String propertyName){
        return properties.getProperty(propertyName);
    }
}

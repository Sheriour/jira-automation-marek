package system;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class PropertyManager {

    private static PropertyManager instance;

    public static PropertyManager GetInstance(){
        if (instance == null) instance = new  PropertyManager();
        return instance;
    }

    private Properties properties;

    private PropertyManager(){
        properties = new Properties();
        loadPropertyFiles();
        loadSystemProperties();
    }

    private void loadPropertyFiles(){
        String path = System.getProperty("user.dir") + "/src/test/resources/jiratest.properties";
        try {
            InputStream input = new FileInputStream(path);
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadSystemProperties(){
        List<String> systemProperties = Arrays.asList("jira_api_username", "jira_user_password", "jira_api_secret");
        for (String propertyName : systemProperties) {
            properties.setProperty(propertyName, System.getenv(propertyName));
        }
    }

    public String getProperty(String propertyName){
        return properties.getProperty(propertyName);
    }
}

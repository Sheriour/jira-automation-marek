package models;

import utils.RandomisationUtils;

public class JiraProject {
    public String Name;
    public String Id;

    public static String getRandomName(){
        return "Project_" + RandomisationUtils.getRandomString();
    }
}

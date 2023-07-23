package Utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomisationUtils {

    public static String getRandomString(){
        int length = 5;
        boolean useLetters = true;
        boolean useNumbers = false;
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }
}

package utils;

public class GeneralUtils {

    public static void waitForSeconds(int seconds){
        try {
            Thread.sleep(1000L * seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

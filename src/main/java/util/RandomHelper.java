package util;

public class RandomHelper {

    public static String getFourDigitCode() {
        return String.valueOf((Math.random() * 9999 + 1000));
    }

}

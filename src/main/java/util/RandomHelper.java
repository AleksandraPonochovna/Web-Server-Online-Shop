package util;

import java.util.Random;

public class RandomHelper {

    public static String getFourDigitCode() {
        return String.valueOf(new Random().nextInt(9000) + 1000);
    }

}

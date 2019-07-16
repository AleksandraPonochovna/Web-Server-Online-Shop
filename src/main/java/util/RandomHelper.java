package util;

import java.util.UUID;

public class RandomHelper {

    public static String get4DigitCode() {
        return UUID.randomUUID().toString().substring(0,4);
    }

}

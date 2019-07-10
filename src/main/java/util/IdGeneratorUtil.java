package util;

public class IdGeneratorUtil {

    private static Long productId = 1L;
    private static Long userId = 1L;

    public static Long getProductId() {
        return productId++;
    }

    public static Long getUserId() {
        return userId++;
    }

}

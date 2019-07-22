package util;

public class IdGeneratorUtil {

    private static Long productId = 1L;
    private static Long userId = 1L;
    private static Long orderId = 100000L;
    private static Long basketId = 1l;

    public static Long getProductId() {
        return productId++;
    }

    public static Long getBasketId() {
        return basketId++;
    }

    public static Long getUserId() {
        return userId++;
    }

    public static Long getOrderId() {
        return orderId++;
    }
}

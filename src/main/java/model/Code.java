package model;

public class Code {

    private String code;
    private Order order;

    public Code(String code, Order order) {
        this.code = code;
        this.order = order;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

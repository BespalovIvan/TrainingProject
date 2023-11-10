package entity;

public class Order {
    Integer id;
    String orderStatus;

    public Order(Integer id, String orderStatus) {
        this.id = id;
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }
}

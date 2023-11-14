package entity;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private final Integer id;
    private final OrderStatus status;
    private final User user;
    private final List<Product> products;
    private final LocalDateTime dateOrder;

    public Order(Integer id, OrderStatus status, User user, List<Product> products, LocalDateTime dateOrder) {
        this.id = id;
        this.status = status;
        this.user = user;
        this.products = products;
        this.dateOrder = dateOrder;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status=" + status +
                ", user=" + user +
                ", products=" + products +
                ", dateOrder=" + dateOrder +
                '}';
    }
}

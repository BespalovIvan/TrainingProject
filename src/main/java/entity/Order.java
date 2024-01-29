package entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Order {
    private final Integer id;
    private final OrderStatus status;
    private final User user;
    private final List<Product> products;
    private final LocalDateTime createOrder;

    public Order(Integer id, OrderStatus status, User user, List<Product> products, LocalDateTime createOrder) {
        this.id = id;
        this.status = status;
        this.user = user;
        this.products = products;
        this.createOrder = createOrder;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status=" + status +
                ", user=" + user +
                ", products=" + products +
                ", dateOrder=" + createOrder +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && status == order.status && Objects.equals(user, order.user) && Objects.equals(products, order.products) && Objects.equals(createOrder, order.createOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, user, products, createOrder);
    }
}

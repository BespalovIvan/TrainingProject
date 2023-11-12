package entity;

import java.util.Date;
import java.util.List;

public class Order {
    Integer id;
    OrderStatus status;
    User user;
    List<Product> products;
    Date dateOrder;

    public Order(Integer id, OrderStatus status, User user, List<Product> products, Date dateOrder) {
        this.id = id;
        this.status = status;
        this.user = user;
        this.products = products;
        this.dateOrder = dateOrder;
    }
}

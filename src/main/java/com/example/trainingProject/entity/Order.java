package com.example.trainingProject.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Order {
    private final Long id;
    private final User user;
    private final List<Product> products;
    private final LocalDateTime createOrder;

    public Order(Long id, User user, List<Product> products, LocalDateTime createOrder) {
        this.id = id;

        this.user = user;
        this.products = products;
        this.createOrder = createOrder;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id= " + id +
                ", " + user +
                ", Products : " + products +
                ", createOrder = " + createOrder +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(user, order.user) && Objects.equals(products, order.products) && Objects.equals(createOrder, order.createOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, products, createOrder);
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public LocalDateTime getCreateOrder() {
        return createOrder;
    }
}

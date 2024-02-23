package com.example.trainingProject.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JdbcTypeCode(SqlTypes.JSON)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany
    private List<Product> products;
    private LocalDateTime createOrder;

    public Order(Long id, User user, List<Product> products, LocalDateTime createOrder) {
        this.id = id;

        this.user = user;
        this.products = products;
        this.createOrder = createOrder;
    }

    public Order() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setCreateOrder(LocalDateTime createOrder) {
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

package com.example.trainingProject.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Objects;


@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private Integer totalCost;
    private LocalDateTime orderCreationDate;
    private LocalDateTime orderUpdateDate;
    private OrderStatus status;

    public Order(Long id, Long userId, Integer totalCost, LocalDateTime orderCreationDate,
                 LocalDateTime orderUpdateDate, OrderStatus status) {
        this.id = id;
        this.userId = userId;
        this.totalCost = totalCost;
        this.orderCreationDate = orderCreationDate;
        this.orderUpdateDate = orderUpdateDate;
        this.status = status;
    }

    public Order(Long userId, Integer totalCost, LocalDateTime orderCreationDate,
                 LocalDateTime orderUpdateDate, OrderStatus status) {
        this.userId = userId;
        this.totalCost = totalCost;
        this.orderCreationDate = orderCreationDate;
        this.orderUpdateDate = orderUpdateDate;
        this.status = status;
    }

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Integer totalCost) {
        this.totalCost = totalCost;
    }

    public LocalDateTime getOrderCreationDate() {
        return orderCreationDate;
    }

    public void setOrderCreationDate(LocalDateTime orderCreationDate) {
        this.orderCreationDate = orderCreationDate;
    }

    public LocalDateTime getOrderUpdateDate() {
        return orderUpdateDate;
    }

    public void setOrderUpdateDate(LocalDateTime orderUpdateDate) {
        this.orderUpdateDate = orderUpdateDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(userId, order.userId) && Objects.equals(totalCost, order.totalCost) && Objects.equals(orderCreationDate, order.orderCreationDate) && Objects.equals(orderUpdateDate, order.orderUpdateDate) && status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, totalCost, orderCreationDate, orderUpdateDate, status);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", totalCost=" + totalCost +
                ", orderCreationDate=" + orderCreationDate +
                ", orderUpdateDate=" + orderUpdateDate +
                ", status=" + status +
                '}';
    }
}

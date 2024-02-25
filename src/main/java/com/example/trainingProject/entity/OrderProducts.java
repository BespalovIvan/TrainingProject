package com.example.trainingProject.entity;

import java.util.Objects;

public class OrderProducts {
    private Order order;
    private Product product;

    public OrderProducts(Order order, Product product) {
        this.order = order;
        this.product = product;
    }

    public OrderProducts() {
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderProducts that = (OrderProducts) o;
        return Objects.equals(order, that.order) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, product);
    }

    @Override
    public String toString() {
        return "OrderProducts{" +
                "order=" + order +
                ", product=" + product +
                '}';
    }
}

package com.example.trainingProject.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class OrderProduct {
    private Long orderId;
    private Long productId;
    private String titleProduct;
    private BigDecimal sum;
    private Integer count;
    private OrderStatus orderStatus;

    public OrderProduct(Long orderId, Long productId, String titleProduct,
                        BigDecimal sum, Integer count, OrderStatus orderStatus) {
        this.orderId = orderId;
        this.productId = productId;
        this.titleProduct = titleProduct;
        this.sum = sum;
        this.count = count;
        this.orderStatus = orderStatus;
    }

    public OrderProduct() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getTitleProduct() {
        return titleProduct;
    }

    public void setTitleProduct(String titleProduct) {
        this.titleProduct = titleProduct;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderProduct that = (OrderProduct) o;
        return Objects.equals(orderId, that.orderId) && Objects.equals(productId, that.productId) && Objects.equals(titleProduct, that.titleProduct) && Objects.equals(sum, that.sum) && Objects.equals(count, that.count) && orderStatus == that.orderStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, productId, titleProduct, sum, count, orderStatus);
    }

    @Override
    public String toString() {
        return "OrderProduct{" +
                "orderId=" + orderId +
                ", productId=" + productId +
                ", titleProduct='" + titleProduct + '\'' +
                ", sum=" + sum +
                ", count=" + count +
                ", orderStatus=" + orderStatus +
                '}';
    }
}

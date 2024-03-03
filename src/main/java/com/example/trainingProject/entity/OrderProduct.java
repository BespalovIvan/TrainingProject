package com.example.trainingProject.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class OrderProduct {
   private Long order_id;
   private Long product_id;
   private String titleProduct;
   private BigDecimal sum;
   private Integer count;

    public OrderProduct(Long order_id, Long product_id, String titleProduct, BigDecimal sum, Integer count) {
        this.order_id = order_id;
        this.product_id = product_id;
        this.titleProduct = titleProduct;
        this.sum = sum;
        this.count = count;
    }

    public OrderProduct() {
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderProduct that = (OrderProduct) o;
        return Objects.equals(order_id, that.order_id) && Objects.equals(product_id, that.product_id) && Objects.equals(titleProduct, that.titleProduct) && Objects.equals(sum, that.sum) && Objects.equals(count, that.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order_id, product_id, titleProduct, sum, count);
    }

    @Override
    public String toString() {
        return "OrderProduct{" +
                "order_id=" + order_id +
                ", product_id=" + product_id +
                ", titleProduct='" + titleProduct + '\'' +
                ", sum=" + sum +
                ", count=" + count +
                '}';
    }
}

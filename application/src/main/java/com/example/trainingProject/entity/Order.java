package com.example.trainingProject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Order {

    @Id
    private Long id;
    private Long userId;
    private BigDecimal totalCost;
    private LocalDateTime orderCreationDate;
    private LocalDateTime orderUpdateDate;
    private OrderStatus status;

    public Order(Long userId, BigDecimal totalCost, LocalDateTime orderCreationDate,
                 LocalDateTime orderUpdateDate, OrderStatus status) {
        this.userId = userId;
        this.totalCost = totalCost;
        this.orderCreationDate = orderCreationDate;
        this.orderUpdateDate = orderUpdateDate;
        this.status = status;
    }
}

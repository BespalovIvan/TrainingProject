package com.example.trainingProject.dto;

import com.example.trainingProject.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDto {
    private Long orderId;
    private BigDecimal totalCost;
    private LocalDateTime orderCreationDate;
    private LocalDateTime orderUpdateDate;
    private OrderStatus status;
}

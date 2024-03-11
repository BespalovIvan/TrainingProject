package com.example.trainingProject.dto;

import com.example.trainingProject.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderProductDto {
    private Long orderId;
    private Long productId;
    private BigDecimal price;
    private String titleProduct;
    private BigDecimal sum;
    private Integer count;
    private OrderStatus orderStatus;

}

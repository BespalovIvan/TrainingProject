package com.example.trainingProject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    private Long id;
    private String title;
    private BigDecimal price;
    private LocalDateTime createDate;

}

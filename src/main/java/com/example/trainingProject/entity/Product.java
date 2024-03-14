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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private BigDecimal price;
    private LocalDateTime createDate;
    @Lob
    private byte[] image;

    public Product(Long id, String title, BigDecimal price, LocalDateTime createDate) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.createDate = createDate;
    }
}

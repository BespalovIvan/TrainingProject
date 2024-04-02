package com.example.trainingProject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User {
    @Id
    private Long id;
    private String name;
    private String email;
    private LocalDateTime createDate;
    private String password;
    private String role;
    private String activateCode;

    public User(Long id, String name, String email, LocalDateTime createDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createDate = createDate;
    }

    public User(Long id, String name, String email, LocalDateTime createDate, String password, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createDate = createDate;
        this.password = password;
        this.role = role;
    }
}

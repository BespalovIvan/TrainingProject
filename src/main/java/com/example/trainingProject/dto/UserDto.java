package com.example.trainingProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime createDate;
    private String password;
    private String role;
    private String activateCode;

    public UserDto(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public UserDto(String name, String email, LocalDateTime createDate, String password, String role, String activateCode) {
        this.name = name;
        this.email = email;
        this.createDate = createDate;
        this.password = password;
        this.role = role;
        this.activateCode = activateCode;
    }
}

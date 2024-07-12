package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto implements Serializable,GeneralData {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime createDate;
    private String password;
    private String role;
    private String activateCode;
    private boolean confirmed;

    public UserDto(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public UserDto(Long id, String name, String email, LocalDateTime createDate, String password, String role, boolean confirmed) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createDate = createDate;
        this.password = password;
        this.role = role;
        this.confirmed = confirmed;
    }
}

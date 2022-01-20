package com.challenge.abdulbasit.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Users {
    private String id;
    private String name;
    private String username;
    private String phone;
    private String email;
}

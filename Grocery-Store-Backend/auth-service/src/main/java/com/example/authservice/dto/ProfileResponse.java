package com.example.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProfileResponse {
    private String name;
    private String email;
    private String phone;
}

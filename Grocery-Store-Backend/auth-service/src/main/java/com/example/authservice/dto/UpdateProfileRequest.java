package com.example.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateProfileRequest {
    private String name;
    private String phone;
    private String email;
}

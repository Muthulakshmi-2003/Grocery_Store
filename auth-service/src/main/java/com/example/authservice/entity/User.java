package com.example.authservice.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Pattern(regexp = "^[1-9][0-9]{9}$",
            message = "Number must be exactly 10 digits and cannot start with 0")
    @Column(unique = true, nullable = false)
    private String number;

    @Column(nullable = false)
    private String password;


}

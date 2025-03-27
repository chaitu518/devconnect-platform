package com.devconnect.auth.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
}

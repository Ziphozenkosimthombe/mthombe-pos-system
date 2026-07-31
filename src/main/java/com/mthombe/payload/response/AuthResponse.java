package com.mthombe.payload.response;

import com.mthombe.payload.dto.UserDto;
import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private String message;
    private UserDto User;
}

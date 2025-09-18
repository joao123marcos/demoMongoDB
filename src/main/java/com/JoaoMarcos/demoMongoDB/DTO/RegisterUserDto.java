package com.JoaoMarcos.demoMongoDB.DTO;

import com.JoaoMarcos.demoMongoDB.Enums.UserRole;

public record RegisterUserDto(String login, String password, UserRole role) {

}

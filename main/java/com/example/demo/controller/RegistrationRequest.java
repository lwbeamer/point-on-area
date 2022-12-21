package com.example.demo.controller;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class RegistrationRequest {

    @NotEmpty(message = "Логин должен быть непустым длиной до 15 символов")
    @NotBlank(message = "Логин должен быть непустым длиной до 15 символов")
    @Size(min = 1, max = 15, message = "Логин должен быть непустым длиной до 15 символов")
    private String username;

    @NotEmpty(message = "Пароль должен быть непустым длиной до 15 символов")
    @NotBlank(message = "Пароль должен быть непустым длиной до 15 символов")
    @Size(min = 1, max = 15, message = "Пароль должен быть непустым длиной до 15 символов")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

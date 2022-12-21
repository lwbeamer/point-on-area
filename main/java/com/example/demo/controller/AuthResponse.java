package com.example.demo.controller;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AuthResponse {

    @NotEmpty(message = "Логин должен быть непустым длиной до 15 символов")
    @NotBlank(message = "Логин должен быть непустым длиной до 15 символов")
    @Size(min = 1, max = 15, message = "Логин должен быть непустым длиной до 15 символов")
    private String token;

    @NotEmpty(message = "Пароль должен быть непустым длиной до 15 символов")
    @NotBlank(message = "Пароль должен быть непустым длиной до 15 символов")
    @Size(min = 1, max = 15, message = "Пароль должен быть непустым длиной до 15 символов")
    private String errorMessage;

    public AuthResponse(String token, String errorMessage) {
        this.token = token;
        this.errorMessage = errorMessage;
    }

    public AuthResponse(String errorMessage){
        this.errorMessage = errorMessage;
    }
    public AuthResponse() {
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

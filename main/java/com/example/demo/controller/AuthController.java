package com.example.demo.controller;

import com.example.demo.annotations.Loggable;
import com.example.demo.config.jwt.JwtProvider;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.UserAlreadyExistException;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid RegistrationRequest registrationRequest, BindingResult bindingResult) {

        if (bindingResult.hasErrors()){
            List<FieldError> errors = bindingResult.getFieldErrors();
            String errorMessage = "";
            for (FieldError error : errors ) {
                errorMessage = errorMessage + error.getDefaultMessage() + '\n';
            }
            return ResponseEntity.ok().body(errorMessage);
        }

        UserEntity userToAdd = new UserEntity();
        userToAdd.setPassword(registrationRequest.getPassword());
        userToAdd.setUsername(registrationRequest.getUsername());
        try {
            userService.saveUser(userToAdd);
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.ok().body(e.getMessage());
        }
        return ResponseEntity.ok("Пользователь успешно зарегистрирован");
    }

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody @Valid AuthRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            List<FieldError> errors = bindingResult.getFieldErrors();
            String errorMessage = "";
            for (FieldError error : errors ) {
                errorMessage = errorMessage + error.getDefaultMessage() + '\n';
            }
            return new AuthResponse(errorMessage);
        }
        UserEntity userEntity = userService.findByUsernameAndPassword(request.getUsername(),request.getPassword());
        if (userEntity == null) return new AuthResponse("Пользователь с таким именем не зарегистрирован или пароль неверный!");
        String token = jwtProvider.generateToken(userEntity.getUsername(), userEntity.getRoleEntity().getName());
        return new AuthResponse(token,null);
    }


}

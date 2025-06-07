package com.school.controllers;

import com.school.models.SystemUser;
import com.school.payload.SchoolRegistrationDto;
import com.school.services.auth.AuthService;
import com.school.utils.OperationReturnObject;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;


    @PostMapping("/register")
    public OperationReturnObject register(@Valid @RequestBody SchoolRegistrationDto user, BindingResult bindingResult) {
        OperationReturnObject operationReturnObject = new OperationReturnObject();
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new LinkedHashMap<>();

            bindingResult.getFieldErrors().forEach(fieldError -> {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            });
            operationReturnObject.setCodeAndMessageAndReturnObject(400, "Registration failed", errors);
            return operationReturnObject;
        }

        try{
            return authService.register(user);
        }catch (IllegalArgumentException e){
            Map<String, String> errors = Map.of("general", e.getMessage());
            operationReturnObject.setCodeAndMessageAndReturnObject(400, "Registration failed", errors);
            return operationReturnObject;
        }
    }

    @PostMapping("/login")
    public OperationReturnObject login( @RequestBody SystemUser user) {
        return authService.login(user);
    }
}
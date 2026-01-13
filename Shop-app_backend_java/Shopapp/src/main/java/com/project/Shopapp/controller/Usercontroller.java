package com.project.Shopapp.controller;

import com.project.Shopapp.DTO.UserDTO;
import com.project.Shopapp.DTO.UserLoginDTO;
import jakarta.validation.Valid;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("api/v1/users")
@RestController
public class Usercontroller {

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO user, BindingResult result){
        try {
            if(result.hasErrors()){
                List<String> errorMessage = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessage);
            }
            if(! user.getPassword().equals(user.getRetypePassword())){
                return  ResponseEntity.badRequest().body("Password does not match");
            }

        }
        catch (Exception e){
            ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("Register suscessfully");

    }
    @PostMapping("/login")
    public ResponseEntity<String> userLogin(@Valid @RequestBody UserLoginDTO userLogin){
        return ResponseEntity.ok("Labla");


    }
}

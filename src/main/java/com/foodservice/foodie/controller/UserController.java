package com.foodservice.foodie.controller;

import com.foodservice.foodie.dto.request.UserRequest;
import com.foodservice.foodie.dto.response.UserResponse;
import com.foodservice.foodie.entity.User;
import com.foodservice.foodie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<List<User>> save(@RequestBody List<UserRequest> users) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.save(users));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }
}

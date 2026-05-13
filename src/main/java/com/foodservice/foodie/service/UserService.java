package com.foodservice.foodie.service;

import com.foodservice.foodie.dto.request.UserRequest;
import com.foodservice.foodie.dto.response.UserResponse;
import com.foodservice.foodie.entity.User;
import com.foodservice.foodie.repository.UserRepository;
import com.foodservice.foodie.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> save(List<UserRequest> users) {

        List<User> userList = users.stream().map(user -> {
            User u = new User();
            u.setUsername(user.getUsername());
            u.setPassword(user.getPassword());
            u.setEmail(user.getEmail());
            u.setAddress(user.getAddress());
            u.setName(user.getName());
            return u;
        }).toList();
        return userRepository.saveAll(userList);
    }

    public List<UserResponse> getAllUsers() {
        List<User> userList = userRepository.findAll();
        List<UserResponse> userResponse = userList
                .stream().map(response ->{
                    UserResponse u = new UserResponse();
                    u.setUsername(response.getUsername());
                    u.setEmail(response.getEmail());
                    u.setAddress(response.getAddress());
                    u.setName(response.getName());
                    return u;
                }).toList();

        return  userResponse;
    }

    public UserResponse getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException(User.class.getSimpleName(), "id", userId));
        UserResponse userResponse = new UserResponse();
        userResponse.setUsername(user.getUsername());
        userResponse.setEmail(user.getEmail());
        userResponse.setAddress(user.getAddress());
        userResponse.setName(user.getName());

        return userResponse;
    }

    public User getUserEntityById(Long userId) {
        return userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException(User.class.getSimpleName(), "id", userId));
    }

}

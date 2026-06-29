package com.foodservice.foodie.service;

import com.foodservice.foodie.entity.User;
import com.foodservice.foodie.repository.UserRepository;
import com.foodservice.foodie.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> save(List<User> users) {
        return userRepository.saveAll(users);
    }

    public List<User> getAllUsers() {
        return  userRepository.findAll();
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException(User.class.getSimpleName(), "id", userId));
    }

}

package com.foodservice.foodie.dto.request;

import jakarta.validation.constraints.NotNull;

public class UserRequest {

    @NotNull(message = "Username cannot be null")
    private String username;

    @NotNull(message = "Password cannot be null")
    private String password;

    @NotNull(message = "Email cannot be null")
    private String email;

    @NotNull(message = "Name cannot be null")
    private String name;

    private String address;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

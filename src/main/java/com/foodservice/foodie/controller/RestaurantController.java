package com.foodservice.foodie.controller;

import com.foodservice.foodie.dto.request.RestaurantRequest;
import com.foodservice.foodie.entity.Restaurant;
import com.foodservice.foodie.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<List<Restaurant>> addRestaurant(@RequestBody List<RestaurantRequest> restaurants) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(restaurantService.save(restaurants)) ;
    }

    @GetMapping
    public ResponseEntity<?> getAllRestaurant() {
        return ResponseEntity.ok(restaurantService.findAll());
    }

    @GetMapping("/id/{id}")
    public Restaurant getRestaurantById(@PathVariable Long id) {
        return restaurantService.findById(id);
    }

    @GetMapping("/search")
    public List<Restaurant> searchRestaurantByName(@RequestParam String name) {
        return restaurantService.searchRestaurantByName(name);
    }
}

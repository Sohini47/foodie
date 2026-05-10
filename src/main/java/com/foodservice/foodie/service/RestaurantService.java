package com.foodservice.foodie.service;

import com.foodservice.foodie.entity.Restaurant;
import com.foodservice.foodie.exception.ResourceNotFoundException;
import com.foodservice.foodie.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Restaurant> save(List<Restaurant> restaurants) {
        return restaurantRepository.saveAll(restaurants);
    }

    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    public Restaurant findById(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Restaurant.class.getSimpleName(), "id", id));
    }

    public List<Restaurant> searchRestaurantByName(String name) {
        return restaurantRepository.findByNameContainingIgnoreCase(name);
    }
}

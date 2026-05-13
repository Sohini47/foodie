package com.foodservice.foodie.service;

import com.foodservice.foodie.dto.request.RestaurantRequest;
import com.foodservice.foodie.entity.Restaurant;
import com.foodservice.foodie.exception.ResourceNotFoundException;
import com.foodservice.foodie.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Restaurant> save(List<RestaurantRequest> restaurants) {

        List<Restaurant> restaurantList = restaurants
                .stream().map(restaurant -> {
                    Restaurant r = new Restaurant();
                    r.setName(restaurant.getName());
                    r.setRating(restaurant.getRating());
                    r.setLocation(restaurant.getLocation());
                    return r;
                }).toList();

        return restaurantRepository.saveAll(restaurantList);
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

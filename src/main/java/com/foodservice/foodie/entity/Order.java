package com.foodservice.foodie.entity;

import com.foodservice.foodie.enums.Status;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double totalAmount;
    private Status status;

    @ManyToOne
    @JoinColumn(name ="userId", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name ="restaurantId", nullable = false)
    private Restaurant restaurant;
}

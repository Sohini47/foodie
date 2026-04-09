package com.foodservice.foodie.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int orderId;
    private int quantity;

    @ManyToOne
    @JoinColumn(name ="menuItemId", nullable = false)
    private MenuItem menuItem;
}

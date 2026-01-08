package com.poc.apigee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private String id;
    private String name;
    private String email;
    private String type; // e.g., "PREMIUM", "REGULAR" - useful for Apigee routing logic later
}
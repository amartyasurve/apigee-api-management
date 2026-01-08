package com.poc.apigee.controller;

import com.poc.apigee.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    // Simulating a database
    private final Map<String, Customer> customerDb = new ConcurrentHashMap<>();

    // GET: Retrieve a customer
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable String id) {
        if (customerDb.containsKey(id)) {
            System.out.println("Received GET request for ID: " + id); // Log for PoC visibility
            return ResponseEntity.ok(customerDb.get(id));
        }
        return ResponseEntity.notFound().build();
    }

    // POST: Create a new customer
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        String id = UUID.randomUUID().toString();
        customer.setId(id);
        customerDb.put(id, customer);

        System.out.println("Received POST request. Created Customer: " + customer.getName());
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    // PUT: Update an existing customer
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String id, @RequestBody Customer customer) {
        if (!customerDb.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        customer.setId(id); // Ensure ID remains consistent
        customerDb.put(id, customer);

        System.out.println("Received PUT request for ID: " + id);
        return ResponseEntity.ok(customer);
    }
}
package com.example.rewards.controller;

import com.example.rewards.pojo.dto.CustomerResponseDTO;
import com.example.rewards.pojo.entity.Customer;
import com.example.rewards.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<CustomerResponseDTO> getAllCustomer() {
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/{id}/rewards")
    public ResponseEntity<?> getCustomerPointsById(@PathVariable Long id) {
        return new ResponseEntity<>(customerService.getTotalRewards(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/rewards")
    public ResponseEntity<?> getCustomerPointByIdAndMonth(@PathVariable Long id, @RequestParam int month) {
        return new ResponseEntity<>(customerService.getMonthRewards(id, month), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> insertCustomer(@RequestBody Customer c) {
        return new ResponseEntity<>(customerService.insertCustomer(c), HttpStatus.CREATED);
    }
}

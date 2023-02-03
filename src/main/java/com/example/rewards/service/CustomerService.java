package com.example.rewards.service;

import com.example.rewards.pojo.dto.CustomerResponseDTO;
import com.example.rewards.pojo.dto.CustomerResponseDTO.*;
import com.example.rewards.pojo.entity.Customer;
import com.example.rewards.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    CustomerResponseDTO getAllCustomers();
    CustomerDTO getCustomerById(Long id);
    Long insertCustomer(Customer c);
    Long updateCustomer(Long id, Customer c);
    Long deleteCustomer(Long id);

    double getTotalRewards(Long id);
    double getMonthRewards(Long id, int month);
}

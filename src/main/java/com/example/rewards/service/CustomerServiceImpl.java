package com.example.rewards.service;

import com.example.rewards.exception.ResourceNotFoundException;
import com.example.rewards.pojo.dto.CustomerResponseDTO;
import com.example.rewards.pojo.dto.CustomerResponseDTO.*;
import com.example.rewards.pojo.entity.Customer;
import com.example.rewards.pojo.entity.Transaction;
import com.example.rewards.repository.CustomerRepository;
import com.example.rewards.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, TransactionRepository transactionRepository) {
        this.customerRepository = customerRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public CustomerResponseDTO getAllCustomers() {
        Collection<Customer> customers = customerRepository.findAll();
        List<CustomerDTO> customerDTOList = customers.stream()
                .map(CustomerDTO::new)
                .collect(Collectors.toList());
        return new CustomerResponseDTO(customerDTOList);
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isEmpty()) {
            throw new ResourceNotFoundException("...");
        }
        return new CustomerDTO(customer.get());

    }
    @Override
    public double getTotalRewards(Long id) {
        Collection<Transaction> transactions = transactionRepository.findTransactionByCustomerId(id);
        return calculateReward(transactions);
    }

    public double calculateReward(Collection<Transaction> transactions) {
        double rewards = 0;
        for(Transaction t: transactions) {
            double price = t.getPrice();
            if(price > 50 && price < 100) {
                rewards += price-50;
            }
            else {
                rewards += price > 100 ? 2 * (price-100) + 50 : 0;
            }
        }
        return rewards;
    }
    @Override
    public double getMonthRewards(Long id, int month) {
        Collection<Transaction> transactions = transactionRepository.findTransactionByCustomerIdAndMonth(id, month);
        return calculateReward(transactions);
    }
    @Override
    @Transactional
    public Long insertCustomer(Customer c) {
        customerRepository.save(c);
        return c.getId();
    }

    @Override
    @Transactional
    public Long updateCustomer(Long id, Customer c) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if(optionalCustomer.isEmpty())
            throw new ResourceNotFoundException("...");
        Customer customer = optionalCustomer.get();
        customer.setEmail(c.getEmail());
        customer.setUserName(c.getUserName());
        customerRepository.save(customer);
        return customer.getId();
    }

    @Override
    @Transactional
    public Long deleteCustomer(Long id) {
        customerRepository.deleteById(id);
        return id;
    }


}

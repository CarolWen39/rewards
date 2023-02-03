package com.example.rewards.service;

import com.example.rewards.exception.ResourceNotFoundException;
import com.example.rewards.pojo.dto.TransactionResponseDTO;
import com.example.rewards.pojo.dto.TransactionResponseDTO.*;
import com.example.rewards.pojo.entity.Customer;
import com.example.rewards.pojo.entity.Transaction;
import com.example.rewards.repository.CustomerRepository;
import com.example.rewards.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.*;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, CustomerRepository customerRepository) {
        this.transactionRepository = transactionRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public TransactionResponseDTO getAll() {
        Collection<Transaction> transactions = transactionRepository.findAll();
//        System.out.println(transactions);
        List<TransactionDTO> transactionDTOList = transactions.stream()
                .map(TransactionDTO::new).collect(Collectors.toList());
        return new TransactionResponseDTO(transactionDTOList);
    }

    @Override
    @Transactional
    public TransactionDTO getTranById(Long id) {
        Optional<Transaction> optional = transactionRepository.findById(id);
        if(!optional.isPresent())
            throw new ResourceNotFoundException("...");
        return new TransactionDTO(optional.get());
    }
    @Override
    @Transactional
    public Long insertTransaction(Transaction t) {
        transactionRepository.save(t);
        // update customer database;
//        double newReward = calculateReward(t.getPrice());
//        Customer customer = customerRepository.findById(t.getCustomer().getId()).orElseGet(null);
//        if(customer == null) {
//            throw new ResourceNotFoundException("No such user");
//        }
//        else {
//            double oldReward = t.getCustomer().getRewards();
//            customer.setRewards(oldReward + newReward);
//            customerRepository.save(customer);
//        }
        return t.getId();
    }
    private double calculateReward(double price) {
        double reward = 0;
        if(price > 50 && price < 100) {
            reward = price-50;
        }
        else {
            reward = reward > 100 ? 2 * (price-100) + 50 : 0;
        }
        return reward;
    }
}

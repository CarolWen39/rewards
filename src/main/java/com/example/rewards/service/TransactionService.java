package com.example.rewards.service;

import com.example.rewards.pojo.dto.TransactionResponseDTO;
import com.example.rewards.pojo.dto.TransactionResponseDTO.*;
import com.example.rewards.pojo.entity.Transaction;
import org.springframework.stereotype.Service;

@Service
public interface TransactionService {
    public TransactionResponseDTO getAll();
    public TransactionDTO getTranById(Long id);
    public Long insertTransaction(Transaction t);
}

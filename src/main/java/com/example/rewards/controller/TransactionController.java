package com.example.rewards.controller;


import com.example.rewards.pojo.dto.TransactionResponseDTO;
import com.example.rewards.pojo.entity.Transaction;
import com.example.rewards.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<TransactionResponseDTO> getAllTran() {
        return new ResponseEntity<TransactionResponseDTO>(transactionService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getTranById(@PathVariable Long id) {
        return new ResponseEntity<>(transactionService.getTranById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> insertTransaction(@RequestBody Transaction t) {
        return new ResponseEntity<>(transactionService.insertTransaction(t), HttpStatus.CREATED);
    }
}

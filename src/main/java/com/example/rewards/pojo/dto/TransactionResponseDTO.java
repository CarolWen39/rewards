package com.example.rewards.pojo.dto;

import com.example.rewards.pojo.entity.Customer;
import com.example.rewards.pojo.entity.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class TransactionResponseDTO {
    private List<TransactionDTO> transactionDTOList;

    @Data
    @Builder
    @AllArgsConstructor
    public static class TransactionDTO {
        private Long id;
        private Customer customer;
        private int month;
        private double price;

        public TransactionDTO(Transaction t) {
            this.id = t.getId();
            this.customer = t.getCustomer();
            this.month = t.getMonth();
            this.price = t.getPrice();
        }
    }
}

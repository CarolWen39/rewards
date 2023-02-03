package com.example.rewards.repository;

import com.example.rewards.pojo.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query(value = "select t from Transaction t where t.customer.id = ?1")
    List<Transaction> findTransactionByCustomerId(Long customerId);
    @Query(value = "select t from Transaction t where t.customer.id = ?1 and t.month = ?2")
    List<Transaction> findTransactionByCustomerIdAndMonth(Long customerId, int month);
}

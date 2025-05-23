package com.rewards.repository;

import com.rewards.model.Customer_Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Customer_Transaction,Long> {
    List<Customer_Transaction> findByCustomerId(String customerId);
}

package com.rewards.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity
@Table(name= "customer_transaction")
public class Customer_Transaction {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name="customer_id")
    private String customerId;
    private Double amount;
    @Column(name="transaction_date")
    private LocalDate transactionDate;

    public Customer_Transaction() {
    }

    public Customer_Transaction(String customerId, Double amount, LocalDate transactionDate) {
        this.customerId = customerId;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }
}

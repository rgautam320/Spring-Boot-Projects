package com.rajangautam.wallet.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rajangautam.wallet.entities.Transaction;

public interface ITransactionDao extends JpaRepository<Transaction, String> {
    List<Transaction> findByUserUserId(String userId);

    Transaction findByTransactionId(String transactionId);
}

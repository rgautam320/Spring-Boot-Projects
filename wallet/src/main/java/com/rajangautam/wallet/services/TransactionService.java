package com.rajangautam.wallet.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rajangautam.wallet.dao.ITransactionDao;
import com.rajangautam.wallet.entities.Transaction;
import com.rajangautam.wallet.exceptions.EtException;

@Service
public class TransactionService implements ITransactionService {
    @Autowired
    private ITransactionDao transactionDao;

    @Override
    public Transaction CreateTransaction(Transaction transaction) throws EtException {
        try {
            transaction.setDate(new Date());
            var createdCategory = transactionDao.save(transaction);
            return createdCategory;
        } catch (Exception e) {
            throw new EtException("Error: " + e.getMessage());
        }
    }

    @Override
    public Transaction UpdateTransaction(Transaction transaction) throws EtException {
        try {
            var singleTransaction = transactionDao.findByTransactionId(transaction.getTransactionId());

            singleTransaction.setAmount(transaction.getAmount());
            singleTransaction.setNote(transaction.getNote());
            singleTransaction.setDate(new Date());

            transactionDao.save(singleTransaction);

            return singleTransaction;
        } catch (Exception e) {
            throw new EtException("Error: " + e.getMessage());
        }
    }

    @Override
    public Transaction Getransaction(String transactionID) throws EtException {
        try {
            var singleTransaction = transactionDao.findById(transactionID).orElse(null);
            return singleTransaction;
        } catch (Exception e) {
            throw new EtException("Error: " + e.getMessage());
        }
    }

    @Override
    public List<Transaction> Getransactions(String userId) throws EtException {
        try {
            var userTransactions = transactionDao.findByUserUserId(userId);
            return userTransactions;
        } catch (Exception e) {
            throw new EtException("Error: " + e.getMessage());
        }
    }

    @Override
    public boolean DeleteTransaction(String transactionId) throws EtException {
        try {
            transactionDao.deleteById(transactionId);
            return true;
        } catch (Exception e) {
            throw new EtException("Error: " + e.getMessage());
        }
    }

}

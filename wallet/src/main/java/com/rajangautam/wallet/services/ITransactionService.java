package com.rajangautam.wallet.services;

import java.util.List;

import com.rajangautam.wallet.entities.Transaction;
import com.rajangautam.wallet.exceptions.EtException;

public interface ITransactionService {
    Transaction CreateTransaction(Transaction transaction) throws EtException;

    Transaction UpdateTransaction(Transaction transaction) throws EtException;

    Transaction Getransaction(String transactionID) throws EtException;

    List<Transaction> Getransactions(String userId) throws EtException;

    boolean DeleteTransaction(String transactionId) throws EtException;
}

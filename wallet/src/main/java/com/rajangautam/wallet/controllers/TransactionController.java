package com.rajangautam.wallet.controllers;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.rajangautam.wallet.entities.Category;
import com.rajangautam.wallet.entities.Transaction;
import com.rajangautam.wallet.entities.User;
import com.rajangautam.wallet.responses.Response;
import com.rajangautam.wallet.services.ITransactionService;

@RestController
@RequestMapping("/api/transactions/")
public class TransactionController {
    @Autowired
    private ITransactionService service;

    @PostMapping("create/{categoryId}")
    public Response<Transaction> CreateCategory(@RequestBody Transaction transaction, @PathVariable String categoryId,
            HttpServletRequest request) {
        try {
            transaction.setTransactionId(UUID.randomUUID().toString());
            transaction.setCategory(new Category(categoryId));
            transaction.setUser(new User((String) request.getAttribute("id")));

            var createdTransaction = service.CreateTransaction(transaction);
            var success = new Response<Transaction>(HttpStatus.CREATED, true, "Transaction Created",
                    createdTransaction);
            return success;

        } catch (Exception e) {
            var error = new Response<Transaction>(HttpStatus.INTERNAL_SERVER_ERROR, false, "Error: " + e.getMessage(),
                    null);
            return error;
        }
    }

    @PutMapping("update/{transactionId}")
    public Response<Transaction> UpdateCategory(@RequestBody Transaction transaction,
            @PathVariable String transactionId, HttpServletRequest request) {
        try {
            transaction.setTransactionId(transactionId);
            transaction.setUser(new User((String) request.getAttribute("id")));

            var updatedTransaction = service.UpdateTransaction(transaction);
            var success = new Response<Transaction>(HttpStatus.OK, true, "Transaction Updated",
                    updatedTransaction);
            return success;

        } catch (Exception e) {
            var error = new Response<Transaction>(HttpStatus.INTERNAL_SERVER_ERROR, false, "Error: " + e.getMessage(),
                    null);
            return error;
        }
    }

    @GetMapping("get-one/{transactionId}")
    public Response<Transaction> GetTransaction(@PathVariable String transactionId, HttpServletRequest request) {
        try {
            var singleTransaction = service.Getransaction(transactionId);
            var success = new Response<Transaction>(HttpStatus.OK, true, "Transaction Fetched",
                    singleTransaction);
            return success;

        } catch (Exception e) {
            var error = new Response<Transaction>(HttpStatus.INTERNAL_SERVER_ERROR, false, "Error: " + e.getMessage(),
                    null);
            return error;
        }
    }

    @GetMapping("get/{userId}")
    public Response<List<Transaction>> GetTransactions(@PathVariable String userId, HttpServletRequest request) {
        try {
            var userTransactions = service.Getransactions(userId);
            var success = new Response<List<Transaction>>(HttpStatus.OK, true, "Transactions Fetched",
                    userTransactions);
            return success;

        } catch (Exception e) {
            var error = new Response<List<Transaction>>(HttpStatus.INTERNAL_SERVER_ERROR, false,
                    "Error: " + e.getMessage(),
                    null);
            return error;
        }
    }

    @DeleteMapping("delete/{transactionId}")
    public Response<Boolean> DeleteTransaction(@PathVariable String transactionId) {
        try {
            var userTransactions = service.DeleteTransaction(transactionId);
            var success = new Response<Boolean>(HttpStatus.OK, true, "Transaction Deleted",
                    userTransactions);
            return success;

        } catch (Exception e) {
            var error = new Response<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR, false,
                    "Error: " + e.getMessage(),
                    null);
            return error;
        }
    }
}

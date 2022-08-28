package com.rajangautam.wallet.entities;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Transaction {
    @Id
    private String transactionId;
    private double amount;
    private String note;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Transaction() {
    }

    public Transaction(String transactionId, double amount, String note, Date date, User user, Category category) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.note = note;
        this.date = date;
        this.user = user;
        this.category = category;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Transaction [amount=" + amount + ", category=" + category + ", date=" + date + ", note=" + note
                + ", transactionId=" + transactionId + ", user=" + user + "]";
    }

}

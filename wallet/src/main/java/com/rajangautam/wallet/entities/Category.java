package com.rajangautam.wallet.entities;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Category {
    @Id
    @Column(name = "category_id", unique = true)
    private String categoryId;
    private String title;
    private String description;
    private double totalExpense;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Transaction> transactions;

    public Category() {
    }

    public Category(String categoryId) {
        this.categoryId = categoryId;
    }

    public Category(String categoryId, String title, String description, double totalExpense, User user,
            List<Transaction> transactions) {
        this.categoryId = categoryId;
        this.title = title;
        this.description = description;
        this.totalExpense = totalExpense;
        this.user = user;
        this.transactions = transactions;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(double totalExpense) {
        this.totalExpense = totalExpense;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

}

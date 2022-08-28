package com.rajangautam.wallet.entities;

import java.util.List;

import javax.persistence.*;

@Entity
public class User {
    @Id
    private String userId;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Category> category;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    public User() {
    }

    public User(String userId) {
        this.userId = userId;
    }

    public User(String userId, String firstName, String lastName, String email, String password) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User [email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + ", password=" + password
                + ", userId=" + userId + "]";
    }
}

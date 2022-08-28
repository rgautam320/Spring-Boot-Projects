package com.rajangautam.wallet.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rajangautam.wallet.entities.User;

public interface IUserDao extends JpaRepository<User, String> {
    public User findByEmail(String email);
}

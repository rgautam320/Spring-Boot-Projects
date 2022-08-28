package com.rajangautam.wallet.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rajangautam.wallet.entities.Category;

public interface ICategoryDao extends JpaRepository<Category, String> {
    public Category findByCategoryId(String categoryId);
}

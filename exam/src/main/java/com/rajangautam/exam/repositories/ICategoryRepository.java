package com.rajangautam.exam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rajangautam.exam.models.Category;

public interface ICategoryRepository extends JpaRepository<Category, String> {

}

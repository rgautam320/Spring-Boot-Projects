package com.rajangautam.exam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rajangautam.exam.models.Quiz;

public interface IQuizRepository extends JpaRepository<Quiz, String> {

}

package com.rajangautam.exam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rajangautam.exam.models.Question;

public interface IQuestionRepository extends JpaRepository<Question, String> {

}

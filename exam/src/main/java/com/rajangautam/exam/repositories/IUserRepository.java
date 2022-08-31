package com.rajangautam.exam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rajangautam.exam.models.User;

public interface IUserRepository extends JpaRepository<User, String> {

}

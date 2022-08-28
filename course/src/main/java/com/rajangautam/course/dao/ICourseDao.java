package com.rajangautam.course.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rajangautam.course.entities.Course;

public interface ICourseDao extends JpaRepository<Course, Integer> {

}

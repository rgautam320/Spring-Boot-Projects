package com.rajangautam.course.service;

import java.util.List;
import java.util.Optional;

import com.rajangautam.course.entities.Course;

public interface ICourseService {
	public List<Course> GetCourses();

	public Optional<Course> GetCourse(int courseId);

	public Course CreateCourse(Course course);

	public Course UpdateCourse(Course course);

	public Boolean DeleteCourse(int courseId);
}

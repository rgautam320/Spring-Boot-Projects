package com.rajangautam.course.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rajangautam.course.dao.ICourseDao;
import com.rajangautam.course.entities.Course;

@Service
public class CourseService implements ICourseService {
	@Autowired
	private ICourseDao courseDao;

	@Override
	public List<Course> GetCourses() {
		return this.courseDao.findAll();
	}

	@Override
	public Optional<Course> GetCourse(int courseId) {
		return this.courseDao.findById(courseId);
	}

	@Override
	public Course CreateCourse(Course course) {
		this.courseDao.save(course);
		return course;
	}

	@Override
	public Course UpdateCourse(Course course) {
		this.courseDao.save(course);
		return course;
	}

	@Override
	public Boolean DeleteCourse(int courseId) {
		this.courseDao.deleteById(courseId);
		return true;
	}
}

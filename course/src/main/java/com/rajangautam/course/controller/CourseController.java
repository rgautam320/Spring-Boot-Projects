package com.rajangautam.course.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rajangautam.course.service.ICourseService;

import com.rajangautam.course.entities.Course;
import com.rajangautam.responses.Response;

@RestController
public class CourseController {
	@Autowired
	private ICourseService service;

	@GetMapping("/courses")
	public Response<List<Course>> GetCourses() {
		try {
			List<Course> courses = service.GetCourses();
			var success = new Response<List<Course>>(
					HttpStatus.OK, true, "Courses Found", courses);
			return success;
		} catch (Exception e) {
			var failure = new Response<List<Course>>(
					HttpStatus.INTERNAL_SERVER_ERROR, false, "Error" + e.getMessage(), null);
			return failure;
		}
	}

	@GetMapping("/courses/{courseId}")
	public Response<Optional<Course>> GetCourse(@PathVariable String courseId) {
		try {
			Optional<Course> course = service.GetCourse(Integer.parseInt(courseId));
			var success = new Response<Optional<Course>>(
					HttpStatus.OK, true, "Course Found", course);
			return success;
		} catch (Exception e) {
			var failure = new Response<Optional<Course>>(
					HttpStatus.INTERNAL_SERVER_ERROR, false, "Error" + e.getMessage(), null);
			return failure;
		}

	}

	@PostMapping("/course")
	public Response<Course> CreateCourse(@RequestBody Course course) {
		try {
			Course createdCourse = service.CreateCourse(course);
			var success = new Response<Course>(
					HttpStatus.OK, true, "Course Created", createdCourse);
			return success;
		} catch (Exception e) {
			var failure = new Response<Course>(
					HttpStatus.INTERNAL_SERVER_ERROR, false, "Error" + e.getMessage(), null);
			return failure;
		}
	}

	@DeleteMapping("/courses/{courseId}")
	public Response<Boolean> DeleteCourse(@PathVariable int courseId) {
		try {
			var res = service.DeleteCourse(courseId);
			var success = new Response<Boolean>(
					HttpStatus.OK, true, "Course Deleted", res);
			return success;
		} catch (Exception e) {
			var failure = new Response<Boolean>(
					HttpStatus.INTERNAL_SERVER_ERROR, false, "Error" + e.getMessage(), null);
			return failure;
		}
	}

	@PutMapping("/courses/{courseId}")
	public Response<Course> UpdateCourse(@PathVariable int courseId, @RequestBody Course course) {
		course.setId(courseId);
		try {
			var res = service.UpdateCourse(course);
			var success = new Response<Course>(
					HttpStatus.OK, true, "Course Updated", res);
			return success;
		} catch (Exception e) {
			var failure = new Response<Course>(
					HttpStatus.INTERNAL_SERVER_ERROR, false, "Error" + e.getMessage(), null);
			return failure;
		}
	}
}

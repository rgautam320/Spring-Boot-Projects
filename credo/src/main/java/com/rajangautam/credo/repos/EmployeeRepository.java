package com.rajangautam.credo.repos;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rajangautam.credo.models.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
    Employee findByEmployeeId(String employeeId);
}

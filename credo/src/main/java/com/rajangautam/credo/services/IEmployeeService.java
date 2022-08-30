package com.rajangautam.credo.services;

import java.util.List;

import com.rajangautam.credo.models.Employee;

public interface IEmployeeService {
    Employee createEmployee(Employee employee) throws Exception;

    Employee updateEmployee(Employee employee) throws Exception;

    Employee getEmployee(String employeeId) throws Exception;

    List<Employee> getEmployees() throws Exception;

    Boolean deleteEmployee(String employeeId) throws Exception;
}

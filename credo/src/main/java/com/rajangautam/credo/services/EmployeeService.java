package com.rajangautam.credo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rajangautam.credo.models.Employee;
import com.rajangautam.credo.repos.EmployeeRepository;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(Employee employee) throws Exception {
        try {
            employeeRepository.insert(employee);
            return employee;
        } catch (Exception e) {
            throw new Exception("Exception: " + e.getMessage());
        }
    }

    @Override
    public Employee updateEmployee(Employee employee) throws Exception {
        try {
            employeeRepository.save(employee);
            return employee;
        } catch (Exception e) {
            throw new Exception("Exception: " + e.getMessage());
        }
    }

    @Override
    public Employee getEmployee(String employeeId) throws Exception {
        try {
            var emp = employeeRepository.findByEmployeeId(employeeId);
            return emp;
        } catch (Exception e) {
            throw new Exception("Exception: " + e.getMessage());
        }
    }

    @Override
    public List<Employee> getEmployees() throws Exception {
        try {
            var employees = employeeRepository.findAll();
            return employees;
        } catch (Exception e) {
            throw new Exception("Exception: " + e.getMessage());
        }
    }

    @Override
    public Boolean deleteEmployee(String employeeId) throws Exception {
        try {
            employeeRepository.deleteById(employeeId);
            return true;
        } catch (Exception e) {
            throw new Exception("Exception: " + e.getMessage());
        }
    }

}

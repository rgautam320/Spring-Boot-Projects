package com.rajangautam.credo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajangautam.credo.models.Employee;
import com.rajangautam.credo.responses.Response;
import com.rajangautam.credo.services.EmployeeService;

@RestController
@RequestMapping("/api/employee/")
public class EmployeeController {
    @Autowired
    private EmployeeService eService;

    @PostMapping("create")
    public Response<Employee> createEmployee(@RequestBody Employee employee) {
        try {
            var emp = eService.createEmployee(employee);
            var success = new Response<Employee>(HttpStatus.CREATED, true, "Employee Created", emp);
            return success;
        } catch (Exception e) {
            var error = new Response<Employee>(HttpStatus.INTERNAL_SERVER_ERROR, false, e.getMessage(),
                    null);
            return error;
        }
    }

    @GetMapping("get/{employeeId}")
    public Response<Employee> getEmployee(@PathVariable String employeeId) {
        try {
            var emp = eService.getEmployee(employeeId);
            var success = new Response<Employee>(HttpStatus.OK, true, "Employee fetched", emp);
            return success;
        } catch (Exception e) {
            var error = new Response<Employee>(HttpStatus.INTERNAL_SERVER_ERROR, false, e.getMessage(),
                    null);
            return error;
        }
    }

    @GetMapping("get-all")
    public Response<List<Employee>> getEmployees() {
        try {
            var emps = eService.getEmployees();
            var success = new Response<List<Employee>>(HttpStatus.OK, true, "Employees fetched", emps);
            return success;
        } catch (Exception e) {
            var error = new Response<List<Employee>>(HttpStatus.INTERNAL_SERVER_ERROR, false, e.getMessage(),
                    null);
            return error;
        }
    }

    @PutMapping("update/{employeeId}")
    public Response<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable String employeeId) {
        try {
            employee.setEmployeeId(employeeId);
            var emp = eService.updateEmployee(employee);
            var success = new Response<Employee>(HttpStatus.OK, true, "Employee updated", emp);
            return success;
        } catch (Exception e) {
            var error = new Response<Employee>(HttpStatus.INTERNAL_SERVER_ERROR, false, e.getMessage(),
                    null);
            return error;
        }
    }

    @DeleteMapping("delete/{employeeId}")
    public Response<Boolean> deleteEmployee(@PathVariable String employeeId) {
        try {
            var res = eService.deleteEmployee(employeeId);
            var success = new Response<Boolean>(HttpStatus.OK, true, "Employee deleted", res);
            return success;
        } catch (Exception e) {
            var error = new Response<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR, false, e.getMessage(),
                    null);
            return error;
        }
    }
}

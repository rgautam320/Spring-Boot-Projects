package com.rajangautam.wallet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajangautam.wallet.entities.User;
import com.rajangautam.wallet.responses.Response;
import com.rajangautam.wallet.responses.UserResponse;
import com.rajangautam.wallet.services.IUserService;

@RestController
@RequestMapping("/api/users/")
public class UserController {
    @Autowired
    private IUserService service;

    @PostMapping("create")
    public Response<UserResponse> CreateUser(@RequestBody User user) {
        try {
            var createUser = service.CreateUser(user);
            var success = new Response<UserResponse>(HttpStatus.CREATED, true, "User Created", createUser);
            return success;

        } catch (Exception e) {
            var error = new Response<UserResponse>(HttpStatus.INTERNAL_SERVER_ERROR, false, "Error: " + e.getMessage(),
                    null);
            return error;
        }
    }

    @PostMapping("validate")
    public Response<UserResponse> ValidateUser(@RequestBody User user) {
        try {
            var validateResult = service.ValidateUser(user.getEmail(), user.getPassword());
            var success = new Response<UserResponse>(HttpStatus.OK, true, "User Validated", validateResult);
            return success;

        } catch (Exception e) {
            var error = new Response<UserResponse>(HttpStatus.INTERNAL_SERVER_ERROR, false, "Error: " + e.getMessage(),
                    null);
            return error;
        }
    }
}

package com.rajangautam.wallet.responses;

import com.rajangautam.wallet.entities.User;

public class UserResponse {
    public User user;
    public String token;

    public UserResponse(User user, String token) {
        this.user = user;
        this.token = token;
    }
}
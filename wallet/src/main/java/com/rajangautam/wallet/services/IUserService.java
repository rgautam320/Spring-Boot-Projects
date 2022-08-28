package com.rajangautam.wallet.services;

import com.rajangautam.wallet.entities.User;
import com.rajangautam.wallet.exceptions.EtAuthException;
import com.rajangautam.wallet.responses.UserResponse;

public interface IUserService {
    UserResponse CreateUser(User user) throws EtAuthException;

    UserResponse ValidateUser(String email, String password) throws EtAuthException;
}

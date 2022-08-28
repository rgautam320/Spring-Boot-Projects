package com.rajangautam.wallet.services;

import java.util.Date;
import java.util.UUID;
import java.util.regex.Pattern;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.rajangautam.wallet.Constants;
import com.rajangautam.wallet.dao.IUserDao;
import com.rajangautam.wallet.entities.User;
import com.rajangautam.wallet.exceptions.EtAuthException;
import com.rajangautam.wallet.responses.UserResponse;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserDao userDao;

    @Override
    public UserResponse CreateUser(User user) throws EtAuthException {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if (user.getEmail() != null) {
            user.setEmail(user.getEmail().toLowerCase());
        }

        if (!pattern.matcher(user.getEmail()).matches()) {
            throw new EtAuthException("Invalid email format");
        }

        User userFound = userDao.findByEmail(user.getEmail());
        if (userFound != null) {
            throw new EtAuthException("Email already in use");
        }

        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
        user.setPassword(hashedPassword);
        user.setUserId(UUID.randomUUID().toString());
        User createdUser = userDao.save(user);

        var userResponse = new UserResponse(createdUser, generateJWTToken(createdUser));
        return userResponse;
    }

    @Override
    public UserResponse ValidateUser(String email, String password) throws EtAuthException {
        try {
            if (email != null) {
                email = email.toLowerCase();
            }
            User user = userDao.findByEmail(email);

            if (!BCrypt.checkpw(password, user.getPassword())) {
                throw new EtAuthException("Invalid email or password");
            }
            var userResponse = new UserResponse(user, generateJWTToken(user));
            return userResponse;
        } catch (EmptyResultDataAccessException e) {
            throw new EtAuthException("Invalid email or password");
        }
    }

    private String generateJWTToken(User user) {
        long timestamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
                .claim("id", user.getUserId())
                .claim("email", user.getEmail())
                .claim("firstName", user.getFirstName())
                .claim("lastName", user.getLastName())
                .compact();
        return token;
    }
}
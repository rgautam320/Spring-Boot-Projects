package com.rajangautam.wallet.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EtResourceNotFoundException extends RuntimeException {

    public EtResourceNotFoundException(String message) {
        super(message);
    }
}

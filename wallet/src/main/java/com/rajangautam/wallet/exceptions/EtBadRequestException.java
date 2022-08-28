package com.rajangautam.wallet.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EtBadRequestException extends RuntimeException {

    public EtBadRequestException(String message) {
        super(message);
    }
}
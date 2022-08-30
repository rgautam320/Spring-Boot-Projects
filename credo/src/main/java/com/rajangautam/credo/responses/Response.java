package com.rajangautam.credo.responses;

import org.springframework.http.HttpStatus;

public class Response<T> {
    public HttpStatus Status;
    public boolean Success;
    public String Message;
    public T Data;

    public Response(HttpStatus status, boolean success, String message, T data) {
        super();
        Status = status;
        Success = success;
        Message = message;
        Data = data;
    }
}

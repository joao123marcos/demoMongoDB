package com.JoaoMarcos.demoMongoDB.Services.Execptions;

public class NoResourceFoundException extends RuntimeException {

    public NoResourceFoundException(String message) {

        super(message);
    }
}

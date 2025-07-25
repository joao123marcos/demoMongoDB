package com.JoaoMarcos.demoMongoDB.Services.Execptions;

public class ObjectNotfound extends RuntimeException {

    public ObjectNotfound(String message) {

        super(message);
    }
}

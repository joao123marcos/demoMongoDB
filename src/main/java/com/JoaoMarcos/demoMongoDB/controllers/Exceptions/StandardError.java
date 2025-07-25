package com.JoaoMarcos.demoMongoDB.controllers.Exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
public class StandardError implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")

    private Instant timeStamp;
    private Integer statusError;
    private String error;
    private String messageExceptionError;
    private String pathError;

}

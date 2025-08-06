package com.JoaoMarcos.demoMongoDB.controllers.Exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.JoaoMarcos.demoMongoDB.Services.Execptions.DateTimeParseException;
import com.JoaoMarcos.demoMongoDB.Services.Execptions.NoResourceFoundException;
import com.JoaoMarcos.demoMongoDB.Services.Execptions.ObjectNotfound;
import com.JoaoMarcos.demoMongoDB.Services.Execptions.IllegalArgumentException;

import jakarta.servlet.http.HttpServletRequest;


@ControllerAdvice
public class ControllerExceptionHandler {

 @ExceptionHandler(ObjectNotfound.class)
 public ResponseEntity<StandardError> objectNotFound(ObjectNotfound e, HttpServletRequest request){

     String defaultError = "Object not found ";
     HttpStatus status = HttpStatus.NOT_FOUND;
     StandardError erro = new StandardError(Instant.now(), status.value(), defaultError, e.getMessage(),
             request.getRequestURI());

     return ResponseEntity.status(status).body(erro);
 }

   @ExceptionHandler(NoResourceFoundException.class)
   public ResponseEntity<StandardError> noResourceFound(NoResourceFoundException e, 
     HttpServletRequest request){

       String defaultError = "Resource not found ";
       HttpStatus status = HttpStatus.BAD_REQUEST;
       StandardError erro = new StandardError(Instant.now(), status.value(), defaultError, e.getMessage(),
               request.getRequestURI());

       return ResponseEntity.status(status).body(erro);
   }

   @ExceptionHandler(DateTimeParseException.class)
   public ResponseEntity<StandardError> dateTimeParseExceptions(DateTimeParseException e, 
     HttpServletRequest request){

       String defaultError = "Date Time Parse Error";
       HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
       StandardError erro = new StandardError(Instant.now(), status.value(), defaultError, e.getMessage(),
               request.getRequestURI());

       return ResponseEntity.status(status).body(erro);
   }

   @ExceptionHandler(IllegalArgumentException.class)
   public ResponseEntity<StandardError> illegalArgumentException(IllegalArgumentException e, 
     HttpServletRequest request){

       String defaultError = "Illegal Arguments";
       HttpStatus status = HttpStatus.BAD_REQUEST;
       StandardError erro = new StandardError(Instant.now(), status.value(), defaultError, e.getMessage(),
               request.getRequestURI());

       return ResponseEntity.status(status).body(erro);
   }
}
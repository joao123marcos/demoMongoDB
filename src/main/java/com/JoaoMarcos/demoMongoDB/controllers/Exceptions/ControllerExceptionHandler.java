package com.JoaoMarcos.demoMongoDB.controllers.Exceptions;

import com.JoaoMarcos.demoMongoDB.Services.Execptions.NoResourceFoundException;
import com.JoaoMarcos.demoMongoDB.Services.Execptions.ObjectNotfound;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;


@ControllerAdvice
public class ControllerExceptionHandler {

 @ExceptionHandler(ObjectNotfound.class)
 public ResponseEntity<StandardError> resourceNotFound(ObjectNotfound e, HttpServletRequest request){

     String defaultError = "Resource not found ";
     HttpStatus status = HttpStatus.NOT_FOUND;
     StandardError erro = new StandardError(Instant.now(), status.value(), defaultError, e.getMessage(),
             request.getRequestURI());

     return ResponseEntity.status(status).body(erro);
 }

   @ExceptionHandler(NoResourceFoundException.class)
   public ResponseEntity<StandardError> noResourceFoundFound(NoResourceFoundException e, 
     HttpServletRequest request){

       String defaultError = "Resource not found ";
       HttpStatus status = HttpStatus.BAD_REQUEST;
       StandardError erro = new StandardError(Instant.now(), status.value(), defaultError, e.getMessage(),
               request.getRequestURI());

       return ResponseEntity.status(status).body(erro);
   }

}

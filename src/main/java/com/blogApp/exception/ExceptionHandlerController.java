package com.blogApp.exception;

import com.blogApp.payload.ErrorHandlers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TheRecordDoesNotExit.class)
    public ResponseEntity<ErrorHandlers> ResourceNotFound(TheRecordDoesNotExit e, WebRequest webRequest){

        ErrorHandlers errorHandlers=new ErrorHandlers(new Date(),e.getMessage(), webRequest.getDescription(false),HttpStatus.NOT_FOUND.getReasonPhrase());

        return new ResponseEntity<>(errorHandlers, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> GlobalException(Exception e,WebRequest webRequest){
        ErrorHandlers errorHandlers=new ErrorHandlers(new Date(),e.getMessage(),
                webRequest.getDescription(false),HttpStatus.NOT_ACCEPTABLE.getReasonPhrase());
        return new ResponseEntity<>(errorHandlers,HttpStatus.NOT_ACCEPTABLE);
    }

}

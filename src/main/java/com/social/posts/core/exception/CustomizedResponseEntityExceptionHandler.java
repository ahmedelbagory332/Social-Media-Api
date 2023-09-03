package com.social.posts.core.exception;

import com.social.posts.core.errorModel.ErrorDetails;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;
import java.util.Objects;



@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{


    @ExceptionHandler(CustomNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest request) throws Exception {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
                ex.getMessage(), HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
                "Total Errors " + ex.getErrorCount()  +" : " + Objects.requireNonNull(ex.getFieldError()).getDefaultMessage(), status.value());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }



   // for missing params
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
                ex.getMessage(), HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);

    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {


        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
                ex.getMessage(), HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);

    }


    /**
     example :
     valid : http://localhost:8080/api/social/user/2102/post
     not valid : http://localhost:8080/api/social/user/2102f/post
     due to 2102f not correct path
     */
    @Override
    protected ResponseEntity<Object> handleTypeMismatch(
            TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
                ex.getMessage(), HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }


}
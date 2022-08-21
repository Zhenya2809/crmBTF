//package com.example.crmbtf.rest;
//
//import com.example.crmbtf.security.jwt.JwtAuthenticationException;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//@ControllerAdvice
//public class RestResponseEntityExceptionHandler
//        extends ResponseEntityExceptionHandler {
//
//    @ExceptionHandler(value
//            = { JwtAuthenticationException.class })
//    protected ResponseEntity<Object> handleConflict(
//            RuntimeException ex, WebRequest request) {
//        String bodyOfResponse = "JWT INVALID";
//        return handleExceptionInternal(ex, bodyOfResponse,
//                new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
//    }
//}
package com.bluespurs.starterkit.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bluespurs.starterkit.resource.CustomErrorType;

@ControllerAdvice(basePackageClasses = ProductController.class)
public class ProductControllerAdvice extends ResponseEntityExceptionHandler {

    public static final Logger log = LoggerFactory.getLogger(ProductController.class);
    public static final String MESSAGE_BAD_REQUEST = "Required field or parameter has not been provided or the value supplied is invalid.";
    public static final String MESSAGE_SERVER_ERROR = "An unexpected error occurred while processing the request.";

    /**
     * Handles IllegalArgumentException exceptions and returns 400(Bad Request)
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    ResponseEntity<?> handleControllerException(HttpServletRequest request, IllegalArgumentException ex) {
    	log.error("Error occured while executing " + request.getRequestURI(), ex);
    	return createErrorResponse(HttpStatus.BAD_REQUEST, MESSAGE_BAD_REQUEST);
    }
    
    /**
     * Handles HttpClientErrorException exceptions and returns 400(Bad Request) if it contains 
     * the same code and 500(Internal Server Error) otherwise. 
     */
    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseBody
    ResponseEntity<?> handleControllerException(HttpServletRequest request, HttpClientErrorException ex) {
    	log.error("Error occured while executing " + request.getRequestURI(), ex);
    	HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    	String message = MESSAGE_SERVER_ERROR;
    	if(ex.getStatusCode() == HttpStatus.BAD_REQUEST) {
    		status = HttpStatus.BAD_REQUEST;
    		message = MESSAGE_BAD_REQUEST;
    	}
    	return createErrorResponse(status, message);
    }

    /**
     * Handles all other exceptions and returns 500 - Internal Server Error
     */
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    ResponseEntity<?> handleControllerException(HttpServletRequest request, Throwable ex) {
    	log.error("Error occured while executing " + request.getRequestURI(), ex);
    	return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, MESSAGE_SERVER_ERROR);
    }

    /**
     * Returns a ResponseEntity object representing an error with provided HTTP status code and message.
     * @param status: An HTTP status code to return.
     * @param message: A String representing the message to return.
     */
    private ResponseEntity<?> createErrorResponse(HttpStatus status, String message) {
    	return new ResponseEntity<>(new CustomErrorType(status.value(), message), status);
    }
 }
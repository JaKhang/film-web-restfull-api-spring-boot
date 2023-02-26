package com.nlu.filmweb.controller;

import com.nlu.filmweb.exception.ResourceNotFoundException;
import com.nlu.filmweb.exception.UsernameNotFoundException;
import com.nlu.filmweb.payload.response.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    public ResponseEntity<APIResponse> resolveException(ResourceNotFoundException exception) {
        APIResponse apiResponse = exception.getAPIResponse();
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseBody
    public ResponseEntity<APIResponse> resolveException(UsernameNotFoundException exception) {
        APIResponse apiResponse = exception.getAPIResponse();
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

}

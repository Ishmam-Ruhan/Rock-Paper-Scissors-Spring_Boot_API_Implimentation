package com.ishmamruhan.RockPaperScissorsSpringBootRestAPI.ErrorManagement;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandle extends ResponseEntityExceptionHandler{
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        RestErrorTemplate restErrorTemplate = new RestErrorTemplate();
        restErrorTemplate.setErrorCode(status.value());
        restErrorTemplate.setErrorType("Request Method Not Supported!");
        restErrorTemplate.setErrorMessage(ex.getMessage());

        return ResponseEntity.ok(restErrorTemplate);
    }

    @ExceptionHandler(CustomizeException.class)
    public ResponseEntity<Object> customizeException(CustomizeException ex){
        RestErrorTemplate restErrorTemplate = new RestErrorTemplate();
        restErrorTemplate.setErrorCode(ex.getError_code());
        restErrorTemplate.setErrorType(ex.getError_type());
        restErrorTemplate.setErrorMessage(ex.getMessage());

        return ResponseEntity.ok(restErrorTemplate);
    }


}

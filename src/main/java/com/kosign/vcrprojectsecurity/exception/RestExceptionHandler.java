package com.kosign.vcrprojectsecurity.exception;

import com.kosign.vcrprojectsecurity.common.api.ApiResponse;
import com.kosign.vcrprojectsecurity.common.api.ApiStatus;
import com.kosign.vcrprojectsecurity.common.api.EmptyJsonResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@Component
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return buildResponseEntity(new ApiStatus(BAD_REQUEST.value(), ex.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Object handleIllegalArgumentException(IllegalArgumentException ex) {


        return buildResponseEntity(new ApiStatus(BAD_REQUEST.value(), ex.getMessage()));
    }
    @ExceptionHandler(IllegalStateException.class)
    public Object handleTimeoutException(IllegalStateException ex) {


        if(ex.getMessage().contains("Timeout on blocking")){
            return buildResponseEntity(new ApiStatus(HttpStatus.REQUEST_TIMEOUT.value(), "Request timeout"));
        }

        return buildResponseEntity(new ApiStatus(BAD_REQUEST.value(), "Unexpected error occurred"));
    }


    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {

        return buildResponseEntity(new ApiStatus(statusCode.value(), "Internal Server Error"));
    }

    public ResponseEntity<Object> buildResponseEntity(ApiStatus apiStatus) {
        ApiResponse<Object> apiResponse = new ApiResponse<>(apiStatus, new EmptyJsonResponse());

        return new ResponseEntity<>(apiResponse, HttpStatusCode.valueOf(apiStatus.getCode()));
    }

    public ResponseEntity<Object> buildResponseEntity(ApiStatus apiStatus, Object data) {
        ApiResponse<Object> apiResponse = new ApiResponse<>(apiStatus, data == null ? new EmptyJsonResponse() : data);

        return new ResponseEntity<>(apiResponse, HttpStatusCode.valueOf(apiStatus.getCode()));
    }

}

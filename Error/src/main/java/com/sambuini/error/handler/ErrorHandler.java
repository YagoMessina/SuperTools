package com.sambuini.error.handler;

import com.sambuini.error.dto.ErrorDTO;
import com.sambuini.error.entity.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handle(MethodArgumentNotValidException exception) {
        ErrorDTO errorDTO = new ErrorDTO(exception.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(errorDTO.getStatusCode()).body(errorDTO);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorDTO> handle(ApiException exception) {
        ErrorDTO errorDTO = new ErrorDTO(exception);

        return ResponseEntity.status(errorDTO.getStatusCode()).body(errorDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleUnknownException(Exception exception) {
        ErrorDTO errorDTO = new ErrorDTO(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        return ResponseEntity.status(errorDTO.getStatusCode()).body(errorDTO);
    }
}

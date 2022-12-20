package com.luxoft.controller.advice;

import com.luxoft.exception.ReportExportException;
import com.luxoft.exception.RiservaNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {RiservaNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessageDto> handleRiservaNotFoundException(RiservaNotFoundException exception) {
        ErrorMessageDto customErrorMessage = new ErrorMessageDto(
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage()
        );
        return new ResponseEntity<>(customErrorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ReportExportException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorMessageDto> handleReportExportException(ReportExportException exception) {
        ErrorMessageDto customErrorMessage = new ErrorMessageDto(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage()
        );
        return new ResponseEntity<>(customErrorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

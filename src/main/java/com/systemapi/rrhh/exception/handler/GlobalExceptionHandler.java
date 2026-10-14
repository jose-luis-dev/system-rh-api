package com.systemapi.rrhh.exception.handler;

import com.systemapi.rrhh.exception.EmployeeNotFoundException;
import com.systemapi.rrhh.exception.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //404 No encontrado
    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse employeeNotFound(EmployeeNotFoundException e){
        return new ErrorResponse("NOT_FOUND", e.getMessage());
    }

    // 500 Error General
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGeneral(Exception e){
        return new ErrorResponse("INTERNAL_ERROR", "Error interno del servidor");
    }

}

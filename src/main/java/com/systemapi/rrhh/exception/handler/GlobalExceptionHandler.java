package com.systemapi.rrhh.exception.handler;

import com.systemapi.rrhh.exception.EmployeeNotFoundException;
import com.systemapi.rrhh.exception.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 400 Argumento inválido
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse argumentosInvalidos(MethodArgumentNotValidException e) {

        List<ErrorResponse.DetalleError> detalles =
                e.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(error -> new ErrorResponse.DetalleError(
                                error.getField(),
                                error.getDefaultMessage()
                        ))
                        .toList();

        return new ErrorResponse(false,
                "BAD_REQUEST",
                "Uno o más campos contienen datos inválidos",
                detalles);
    }

    //404 No encontrado
    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse employeeNotFound(EmployeeNotFoundException e){
        return new ErrorResponse(
                false,
                "NOT_FOUND",
                e.getMessage(),
                List.of()
        );
    }

    // 500 Error General
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGeneral(Exception e){
        return new ErrorResponse(
                false,
                "INTERNAL_ERROR",
                "Error interno del servidor",
                List.of()
        );
    }

}

package com.systemapi.rrhh.exception.handler;

import com.systemapi.rrhh.exception.EmployeeNotFoundException;
import com.systemapi.rrhh.exception.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import tools.jackson.databind.exc.UnrecognizedPropertyException;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // 400 Argumento inválido
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationErrors (MethodArgumentNotValidException e) {

        List<ErrorResponse.DetalleError> detalles =
                e.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(error -> new ErrorResponse.DetalleError(
                                error.getField(),
                                error.getDefaultMessage()
                        ))
                        .toList();

        return ErrorResponse.of(
                "BAD_REQUEST",
                "Uno o más campos contienen datos inválidos",
                detalles);
    }

    // 400
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleUnreadableBody (HttpMessageNotReadableException e){

        log.warn("Cuerpo ilegible: {}",e.getMessage());

        if (e.getCause() instanceof UnrecognizedPropertyException ex){

            return ErrorResponse.of(
                    "BAD_REQUEST",
                    "El cuerpo de la petición contiene uno o más campos no permitidos.",
                    List.of(new ErrorResponse.DetalleError(ex.getPropertyName(), "Campo no permitido"))
            );
        }
        return ErrorResponse.of(
                "BAD_REQUEST",
                "El cuerpo de la petición es inválido o tiene un formato incorrecto.",
                List.of()
        );
    }

    // 400
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMissingParameter (MissingServletRequestParameterException e){
        String message = String.format("El parámetro '%s' es obligatorio", e.getParameterName());

        return ErrorResponse.of(
                "BAD_REQUEST",
                message,
                List.of()
        );
    }

    //404 No encontrado
    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleEmployeeNotFound(EmployeeNotFoundException e){
        return ErrorResponse.of(
                "NOT_FOUND",
                e.getMessage(),
                List.of()
        );
    }

    // 404 Ruta o recurso no encontrado
    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNoResourceFound(NoResourceFoundException e){
        return ErrorResponse.of(
                "NOT_FOUND",
                "El recurso solicitado no existe",
                List.of()
        );
    }

    // 405
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorResponse handleMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        String message = String.format("El método '%s' no está permitido para esta ruta", e.getMethod());

        return ErrorResponse.of(
                "METHOD_NOT_ALLOWED",
                message,
                List.of()
        );
    }

    // 415 Unsupported Media Type
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public ErrorResponse handleMediaTypeNotSupported(HttpMediaTypeNotSupportedException e) {
        return ErrorResponse.of(
                "UNSUPPORTED_MEDIA_TYPE",
                "El tipo de contenido no es soportado: Asegúrate de enviar 'application/json'.",
                List.of()
        );
    }

    // 500 Error General
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleUnexpectedError(Exception e) {
        log.error("Error interno inesperado en la aplicación: ", e);

        return ErrorResponse.of(
                "INTERNAL_ERROR",
                "Error interno del servidor",
                List.of()
        );
    }

}

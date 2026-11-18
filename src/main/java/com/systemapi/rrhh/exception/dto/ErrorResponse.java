package com.systemapi.rrhh.exception.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@JsonPropertyOrder({"success", "error","message", "details"})
public class ErrorResponse {

    private final boolean success;
    private final String error;
    private final String message;
    private final List<DetalleError> details;

    public static ErrorResponse of(String errorCodigo, String message, List<DetalleError> details) {
        return new ErrorResponse(false, errorCodigo, message, details);
    }

    public record DetalleError(
            String field,
            String message
    ){}
}

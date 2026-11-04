package com.systemapi.rrhh.exception.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
@JsonPropertyOrder({"success", "error","message", "detalles"})
public class ErrorResponse {

    private final boolean success;
    private final String error;
    private final String message;
    private final List<DetalleError> detalles;

    public record DetalleError(
            String campo,
            String mensaje
    ){}
}

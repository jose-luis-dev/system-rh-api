package com.systemapi.rrhh.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record EmployeeRequestDTO (
        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
        String name,
        @Size(max = 50, message = "El departamento no puede superar los 50 caracteres")
        String department,
        @NotNull(message = "El salario es obligatorio")
        @Positive(message = "El salario debe ser mayor a cero")
        @Digits(integer = 8, fraction = 2, message = "El formato del salario es inválido (máximo 8 enteros y 2 decimales)")
        BigDecimal salary) {
}

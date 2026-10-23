package com.systemapi.rrhh.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record EmployeeRequestDTO (
        @NotBlank
        @Size(max = 100)
        String name,
        @Size(max = 50)
        String department,
        @NotNull
        @Positive
        @Digits(integer = 8, fraction = 2)
        BigDecimal salary) {
}

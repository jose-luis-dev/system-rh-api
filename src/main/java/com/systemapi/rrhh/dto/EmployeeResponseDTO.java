package com.systemapi.rrhh.dto;

import java.math.BigDecimal;

public record EmployeeResponseDTO(
        String name,
        String department,
        BigDecimal salary,
        String employeeNumber
) {}

package com.systemapi.rrhh.service;

import com.systemapi.rrhh.dto.EmployeeRequestDTO;
import com.systemapi.rrhh.dto.EmployeeResponseDTO;

import java.util.List;

public interface IEmployeeService {
    List<EmployeeResponseDTO> listEmployee();
    EmployeeResponseDTO searchEmployeeByNumber(String employeeNumber);
    List<EmployeeResponseDTO> searchEmployeeByName(String name);
    EmployeeResponseDTO createEmployee(EmployeeRequestDTO dto);
    void deleteEmployee(String employeeNumber);
}

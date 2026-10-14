package com.systemapi.rrhh.service;

import com.systemapi.rrhh.dto.EmployeeResponseDTO;
import com.systemapi.rrhh.model.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {
    List<EmployeeResponseDTO> listEmployee();
    Optional <EmployeeResponseDTO> searchEmployeeId(Long id);
    EmployeeResponseDTO createEmployee(Employee employee);
    void deleteEmployee(Employee employee);
}

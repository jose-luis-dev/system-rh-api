package com.systemapi.rrhh.service;

import com.systemapi.rrhh.dto.EmployeeResponseDTO;
import com.systemapi.rrhh.exception.EmployeeNotFoundException;
import com.systemapi.rrhh.mapper.EmployeeMapper;
import com.systemapi.rrhh.model.Employee;
import com.systemapi.rrhh.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeService(
            EmployeeRepository employeeRepository,
            EmployeeMapper employeeMapper){
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }


    // Listar empleados
    @Override
    public List<EmployeeResponseDTO> listEmployee() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::toDTO)
                .toList();
    }

    // Buscar empleado por Id
    @Override
    public Optional <EmployeeResponseDTO> searchEmployeeId(Long id) {
        return employeeRepository.findById(id)
                .map(employeeMapper::toDTO)
                .map(Optional::of)
                .orElseThrow(() -> new EmployeeNotFoundException("Empleado no encontrado"));
    }

    // Registra empleado
    @Override
    public EmployeeResponseDTO createEmployee(Employee employee) {
        return null;
    }

    // Eliminar empleado
    @Override
    public void deleteEmployee(Employee employee) {
        employeeRepository.delete(employee);
    }
}

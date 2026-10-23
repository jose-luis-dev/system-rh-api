package com.systemapi.rrhh.service;

import com.systemapi.rrhh.dto.EmployeeRequestDTO;
import com.systemapi.rrhh.dto.EmployeeResponseDTO;
import com.systemapi.rrhh.exception.EmployeeNotFoundException;
import com.systemapi.rrhh.mapper.EmployeeMapper;
import com.systemapi.rrhh.model.Employee;
import com.systemapi.rrhh.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService implements IEmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    // Método privado auxiliar para reutilizar la búsqueda y lanzamiento de excepción.
    private Employee findEntityByNumber(String employeeNumber){
        return employeeRepository.findByEmployeeNumber(employeeNumber)
                .orElseThrow( () -> new EmployeeNotFoundException(
                        "Empleado no encontrado con el número: " + employeeNumber
                ));
    }

    // Listar empleados
    @Override
    @Transactional(readOnly = true)
    public List<EmployeeResponseDTO> listEmployee() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::toDTO)
                .toList();
    }

    // Buscar empleado por numero de empleado
    @Override
    @Transactional(readOnly = true)
    public EmployeeResponseDTO searchEmployeeByNumber(String employeeNumber) {
        Employee employee = findEntityByNumber(employeeNumber);
        return employeeMapper.toDTO(employee);
    }

    // Buscar empleado por nombre
    @Override
    @Transactional(readOnly = true)
    public List<EmployeeResponseDTO> searchEmployeeByName(String name) {
        return employeeRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(employeeMapper::toDTO)
                .toList();
    }

    // Registra empleado
    @Override
    @Transactional
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO dto) {
        // 1 Convertir el DTO a entidad
        Employee employee = employeeMapper.toEntity(dto);

        // 2 Guardar preliminarmente para obtener el ID de la BD
        employee = employeeRepository.save(employee);

        // 3 Generar el numero de empleado asignado por el sistema
        employee.generateEmployeeNumber();

        return employeeMapper.toDTO(employee);
    }

    // Eliminar empleado
    @Override
    @Transactional
    public void deleteEmployee(String employeeNumber) {
        Employee employee = findEntityByNumber(employeeNumber);
        employeeRepository.delete(employee);
    }
}

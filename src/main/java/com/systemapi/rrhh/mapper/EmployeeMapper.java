package com.systemapi.rrhh.mapper;

import com.systemapi.rrhh.dto.EmployeeRequestDTO;
import com.systemapi.rrhh.dto.EmployeeResponseDTO;
import com.systemapi.rrhh.model.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    // Entity a DTO -> Para enviar hacia afuera
    public EmployeeResponseDTO toDTO(Employee employee){
        return new EmployeeResponseDTO(
                employee.getName(),
                employee.getDepartment(),
                employee.getSalary(),
                employee.getEmployeeNumber()
        );
    }

    // DTO a Entity -> Para guardar DB
    public Employee toEntity(EmployeeRequestDTO dto){
        return Employee.builder()
                .name(dto.name())
                .department(dto.department())
                .salary(dto.salary())
                .build();
    }

    // Actualiza entity existente con los datos de DTO
    public void updateEntity(Employee employee, EmployeeRequestDTO dto){
        employee.setName(dto.name());
        employee.setDepartment(dto.department());
        employee.setSalary(dto.salary());
    }
}

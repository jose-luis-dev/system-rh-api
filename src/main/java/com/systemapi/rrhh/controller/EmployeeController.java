package com.systemapi.rrhh.controller;

import com.systemapi.rrhh.dto.EmployeeRequestDTO;
import com.systemapi.rrhh.dto.EmployeeResponseDTO;
import com.systemapi.rrhh.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController( EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Listar empleados
    @GetMapping
    public List<EmployeeResponseDTO> listEmployee(){ return employeeService.listEmployee();}

    // Buscar por número de empleado
    @GetMapping("/{employeeNumber}")
    public ResponseEntity<EmployeeResponseDTO> searchEmployeeNumber(@PathVariable String employeeNumber){
        return ResponseEntity.ok(employeeService.searchEmployeeByNumber(employeeNumber));
    }

    // Crear empleado
    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@RequestBody @Valid EmployeeRequestDTO request){

        EmployeeResponseDTO responseDTO = employeeService.createEmployee(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    // Eliminar empleado
    @DeleteMapping("/{employeeNumber}")
    public ResponseEntity<EmployeeResponseDTO> deleteEmployee(@PathVariable String employeeNumber){
        employeeService.deleteEmployee(employeeNumber);
        return ResponseEntity.noContent().build();
    }


}

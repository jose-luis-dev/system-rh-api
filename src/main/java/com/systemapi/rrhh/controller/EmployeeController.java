package com.systemapi.rrhh.controller;

import com.systemapi.rrhh.dto.EmployeeRequestDTO;
import com.systemapi.rrhh.dto.EmployeeResponseDTO;
import com.systemapi.rrhh.service.IEmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

    private final IEmployeeService employeeService;

    // Listar empleados
    @GetMapping
    public List<EmployeeResponseDTO> listEmployee(){ return employeeService.listEmployee();}

    // Buscar por número de empleado
    @GetMapping("/{employeeNumber}")
    public ResponseEntity<EmployeeResponseDTO> searchEmployeeNumber(@PathVariable String employeeNumber){
        return ResponseEntity.ok(employeeService.searchEmployeeByNumber(employeeNumber));
    }

    // Buscar empleado por nombre
    @GetMapping("/search")
    public ResponseEntity<List<EmployeeResponseDTO>> searchEmployeeByName(@RequestParam String name){
        return ResponseEntity.ok(employeeService.searchEmployeeByName(name));
    }

    // Crear empleado
    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@RequestBody @Valid EmployeeRequestDTO request) {

        EmployeeResponseDTO responseDTO = employeeService.createEmployee(request);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{employeeNumber}")
                .buildAndExpand(responseDTO.employeeNumber())
                .toUri();

        return ResponseEntity.created(uri).body(responseDTO);
    }

    // Eliminar empleado
    @DeleteMapping("/{employeeNumber}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String employeeNumber){
        employeeService.deleteEmployee(employeeNumber);
        return ResponseEntity.noContent().build();
    }

}

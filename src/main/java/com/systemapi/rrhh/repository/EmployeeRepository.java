package com.systemapi.rrhh.repository;

import com.systemapi.rrhh.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmployeeNumber(String employeeNumber);
    List<Employee> findByNameContainingIgnoreCase(String name);
}
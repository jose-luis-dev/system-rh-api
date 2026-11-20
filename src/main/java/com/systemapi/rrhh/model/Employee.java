package com.systemapi.rrhh.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "employee")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String department;
    @Column(precision = 10, scale = 2)
    private BigDecimal salary;
    @Column(unique = true, length = 20)
    private String employeeNumber;
    

    public void generateEmployeeNumber() {
        if (this.id == null){
            throw new IllegalStateException("No se puede generar el número de empleado sin un ID");
        }
        this.employeeNumber = String.format("EMP-%04d", this.id);
    }

}



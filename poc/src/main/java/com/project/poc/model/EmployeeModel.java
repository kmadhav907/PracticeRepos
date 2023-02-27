package com.project.poc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
public class EmployeeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @Column(name="employee_name", nullable = false)
    private String employeeName;

    @Column(name="created_at", nullable = false)
    private String createdAt;

    @Column(name="updated_at")
    private String updatedAt;

    @Column(name="deleted_at")
    private String deletedAt;

}

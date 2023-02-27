package com.project.poc.repositories;

import com.project.poc.model.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<EmployeeModel, Long> {
    List<EmployeeModel> findByDeletedAtIsNull();

    List<EmployeeModel> findByDeletedAtIsNotNull();
    Optional<EmployeeModel> findByEmployeeName(String employeeName);
}

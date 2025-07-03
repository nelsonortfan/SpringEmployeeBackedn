package com.example.backend.demoReact.repository;

import com.example.backend.demoReact.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

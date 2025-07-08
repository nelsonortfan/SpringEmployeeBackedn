package com.example.backend.demoReact.repository;

import com.example.backend.demoReact.entity.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class EmployeeRepositoryTest {

    // given - when -  then

    @Autowired
    private EmployeeRepository repository;
    Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee(1L, "Nelson", "Ortiz", "nmortiz@gmail.com" );
        repository.save(employee);
    }

    @AfterEach
    void tearDown() {
        employee = null;
        repository.deleteAll();;
    }
}

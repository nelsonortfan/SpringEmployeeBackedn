package com.example.backend.demoReact.repository;

import com.example.backend.demoReact.entity.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class EmployeeRepositoryTest {

    // given - when -  then

    @Autowired
    private EmployeeRepository repository;
    Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee(null, "Nelson", "Ortiz", "nmortiz@gmail.com" );
        repository.save(employee);
    }

    @AfterEach
    void tearDown() {
        employee = null;
        repository.deleteAll();;
    }

    // Test Case Success

    @Test
    void testFindByFirstName_Found(){
        List<Employee> employeesList = repository.findByFirstName("Nelson");
        assertThat(employeesList.get(0).getId()).isEqualTo(employee.getId());
        assertThat(employeesList.get(0).getLastName()).isEqualTo(employee.getLastName());
    }


    @Test
    void prueba(){
        assertThat(3).isEqualTo(3);
    }


}

package com.example.backend.demoReact.service.impl;

import com.example.backend.demoReact.dto.EmployeeDto;
import com.example.backend.demoReact.entity.Employee;
import com.example.backend.demoReact.repository.EmployeeRepository;
import com.example.backend.demoReact.service.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;


class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;
    private EmployeeService employeeService;
    AutoCloseable autoCloseable;
    Employee employee;
    EmployeeDto employeeDto;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        employeeService = new EmployeeServiceImpl(employeeRepository);
        employee = new Employee(1L, "Valentia", "Michi", "lamichi@gmail.com");
        employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setEmail(employee.getEmail());
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testCreateEmployee() {

        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        EmployeeDto result = employeeService.createEmployee(employeeDto);

        assertThat(result.getFirstName()).isEqualTo(employeeDto.getFirstName());
        assertThat(result.getLastName()).isEqualTo(employeeDto.getLastName());
        assertThat(result.getEmail()).isEqualTo(employeeDto.getEmail());

    }

    @Test
    void getEmployeeById() {
    }

    @Test
    void getAllEmployees() {
    }

    @Test
    void updateEmployee() {
    }

    @Test
    void deleteEmployee() {
    }
}
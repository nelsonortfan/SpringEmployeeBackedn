package com.example.backend.demoReact.service.impl;

import com.example.backend.demoReact.dto.EmployeeDto;
import com.example.backend.demoReact.entity.Employee;
import com.example.backend.demoReact.exception.ResourceNotFoundException;
import com.example.backend.demoReact.repository.EmployeeRepository;
import com.example.backend.demoReact.service.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;
    private EmployeeService employeeService;
    AutoCloseable autoCloseable;
    Employee employee, employeeUpdated;
    EmployeeDto employeeDto, employeeDtoUpdate ;


    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        employeeService = new EmployeeServiceImpl(employeeRepository);
        employee = new Employee(1L, "Valentia", "Michi", "lamichi@gmail.com");
        employeeUpdated = new Employee(employee.getId(), "Valentia", "Grisecita", "micorreodegato@gmail.com");
        employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setEmail(employee.getEmail());
        employeeDtoUpdate = new EmployeeDto();
        employeeDtoUpdate.setId(employeeUpdated.getId());
        employeeDtoUpdate.setFirstName(employeeUpdated.getFirstName());
        employeeDtoUpdate.setLastName(employeeUpdated.getLastName());
        employeeDtoUpdate.setEmail(employeeDtoUpdate.getEmail());
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testCreateEmployee() {

        mock(EmployeeRepository.class);

        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        EmployeeDto result = employeeService.createEmployee(employeeDto);

        assertThat(result.getFirstName()).isEqualTo(employeeDto.getFirstName());
        assertThat(result.getLastName()).isEqualTo(employeeDto.getLastName());
        assertThat(result.getEmail()).isEqualTo(employeeDto.getEmail());

    }

    @Test
    void testGetEmployeeById() {
        mock(EmployeeRepository.class);
        mock(Employee.class);

        when(employeeRepository.findById(1L)).thenReturn(Optional.ofNullable(employee));

        assertThat(employeeService.getEmployeeById(1L).getFirstName()).isEqualTo(employee.getFirstName());

    }

    @Test
    void testGetEmployeeByIdNotFoundException(){
        mock(EmployeeRepository.class);
        mock(Employee.class);

        when(employeeRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.getEmployeeById(2L);
        });

        verify(employeeRepository).findById(2L);

    }

    @Test
    void testGetAllEmployees() {
        mock(EmployeeRepository.class);
        mock(Employee.class);

        when(employeeRepository.findAll()).thenReturn(new ArrayList<Employee>(Collections.singleton(employee)));

        assertThat(employeeService.getAllEmployees().get(0).getFirstName()).isEqualTo(employee.getFirstName());

    }

    @Test
    void TestUpdateEmployeeSuccessfully() {
        mock(EmployeeRepository.class);
        mock(Employee.class);

        when(employeeRepository.findById(1L)).thenReturn(Optional.ofNullable(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employeeUpdated);

        EmployeeDto result = employeeService.updateEmployee(1L, employeeDtoUpdate);

        assertThat(result.getFirstName()).isEqualTo("Valentia");
        assertThat(result.getLastName()).isEqualTo("Grisecita");
        assertThat(result.getEmail()).isEqualTo("micorreodegato@gmail.com");

        verify(employeeRepository).findById(1L);
        verify(employeeRepository).save(employee);
    }

    @Test
    void testUpdateEmployeeNotFoundException(){
        mock(EmployeeRepository.class);
        mock(Employee.class);

        when(employeeRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.updateEmployee(2L, employeeDtoUpdate);
        });

        verify(employeeRepository).findById(2L);
        verify(employeeRepository, never()).save(any(Employee.class));



    }

    @Test
    void testDeleteEmployeeSuccessfully() {

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        employeeService.deleteEmployee(1L);

        verify(employeeRepository).findById(1L);
        verify(employeeRepository).deleteById(1L);

    }

    @Test
    void testDeleteEmployeeNotFoundException() {

        when(employeeRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.deleteEmployee(2L);
        });

        verify(employeeRepository).findById(2L);
        verify(employeeRepository, never()).deleteById(anyLong());
    }
}
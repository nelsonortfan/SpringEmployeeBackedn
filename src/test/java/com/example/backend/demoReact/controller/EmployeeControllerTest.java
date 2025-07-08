package com.example.backend.demoReact.controller;

import com.example.backend.demoReact.dto.EmployeeDto;
import com.example.backend.demoReact.entity.Employee;
import com.example.backend.demoReact.exception.ResourceNotFoundException;
import com.example.backend.demoReact.service.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    Employee employee1, employee2;
    EmployeeDto employeeDto1, employeeDto2;
    List<EmployeeDto> employeeList = new ArrayList<EmployeeDto>();

    @BeforeEach
    void setUp() {
        this.employee1 = new Employee(1L, "Mauricio", "Ortiz", "mycorreo1@gmail.com");
        this.employee2 = new Employee(2L, "Nelson", "Farfan", "mycorreo2@gmail.com");
        this.employeeDto1 = new EmployeeDto();
        this.employeeDto2 = new EmployeeDto();
        this.employeeDto1.setId(employee1.getId());
        this.employeeDto1.setFirstName(employee1.getFirstName());
        this.employeeDto1.setLastName(employee1.getLastName());
        this.employeeDto1.setEmail(employee1.getEmail());
        this.employeeDto2.setId(employee2.getId());
        this.employeeDto2.setFirstName(employee2.getFirstName());
        this.employeeDto2.setLastName(employee2.getLastName());
        this.employeeDto2.setEmail(employee2.getEmail());
        this.employeeList.add(employeeDto1);
        this.employeeList.add(employeeDto2);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createEmployee() {

    }

    @Test
    void getEmployeeById() throws Exception {

        when(employeeService.getEmployeeById(1L)).thenReturn(employeeDto1);

        this.mockMvc.perform(get("/api/employees/1")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Mauricio"));

    }

    @Test
    void getEmployeeById_NotFound() throws Exception {

        when(employeeService.getEmployeeById(4L))
                .thenThrow(new ResourceNotFoundException("Employee not found with id: 4"));

        this.mockMvc.perform(get("/api/employees/4")).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllEmployees() throws Exception {
        when(employeeService.getAllEmployees()).thenReturn(employeeList);

        this.mockMvc.perform(get("/api/employees")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void updateEmployee() {
    }

    @Test
    void deleteEmployee() {
    }
}
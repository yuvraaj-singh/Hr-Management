package com.nagarro.hrmanagement.service;

import com.nagarro.hrmanagement.model.Employee;

import java.util.ArrayList;
import java.util.List;

public interface EmployeeService {
    Employee[] getEmployeeList();
    void updateEmployee(Employee employee);
    void uploadEmployee(List<Employee> employees);
}

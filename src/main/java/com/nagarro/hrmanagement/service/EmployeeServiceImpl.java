package com.nagarro.hrmanagement.service;

import com.nagarro.hrmanagement.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Employee[] getEmployeeList(){
        ResponseEntity<Employee[]> response =
                restTemplate.getForEntity(
                        "http://localhost:8080//employee-api/employees/",
                        Employee[].class);
        Employee[] employees = response.getBody();
        return employees;
    }

    @Override
    public void updateEmployee(Employee employee){
        restTemplate.put("http://localhost:8080//employee-api/employees/"+employee.getEmployeeCode(),employee);
    }

    @Override
    public void uploadEmployee(List<Employee> employees){
        for(int i=0; i<employees.size(); i++){
            restTemplate.postForObject("http://localhost:8080//employee-api/employees",employees.get(i),Employee.class);
            System.out.println("Employee Added :"+i+1);
        }
    }
}

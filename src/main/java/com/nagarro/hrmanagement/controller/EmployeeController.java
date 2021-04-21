package com.nagarro.hrmanagement.controller;

import com.nagarro.hrmanagement.model.Employee;
import com.nagarro.hrmanagement.service.EmployeeService;
import com.nagarro.hrmanagement.utils.CSVUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    CSVUtil csvUtil = new CSVUtil();

    @GetMapping("/EmployeeManager")
    public String getEmployeeManagerPage(@ModelAttribute("emp")Employee employee,HttpServletRequest request){
        Employee[] employees = employeeService.getEmployeeList();
        request.getSession().setAttribute("employees",employees);
        System.out.println(employees[0].getEmployeeName());
        return "EmployeeManager";
    }
    @PostMapping("/EmployeeManager")
    public String updateEmployees(@ModelAttribute("emp")Employee employee){
        System.out.println("From Controller :"+employee.getEmployeeCode());
        System.out.println(employee.getEmployeeName());
        System.out.println(employee.getDob());
        employeeService.updateEmployee(employee);
        return "redirect:EmployeeManager";
    }
    @PostMapping("/UploadFile")
    public String uploadEmployees(@RequestParam("file")MultipartFile file){
        List<Employee> employees = csvUtil.fetchEmployessFromCSV(file);

        employeeService.uploadEmployee(employees);
        return "redirect:EmployeeManager";
    }
    @GetMapping("/DownloadFile")
    public String downloadFile(HttpServletResponse response) throws IOException {
        List<Employee> employees = Arrays.asList(employeeService.getEmployeeList());
        csvUtil.exportToCSV(response,employees);
        return "redirect:EmployeeManager";
    }

}

package com.nagarro.hrmanagement.utils;

import com.nagarro.hrmanagement.model.Employee;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

public class CSVUtil {
    public List<Employee> fetchEmployessFromCSV(MultipartFile file){
        try(BufferedReader reader=new BufferedReader(new InputStreamReader(file.getInputStream()))){
            List<Employee> employees = new ArrayList<>();
            String cell = reader.readLine();
            while ((cell=reader.readLine())!=null){
                String[] data = cell.split("\\|");
                Employee employee = new Employee();
                employee.setEmployeeName(data[0]);
                employee.setLocation(data[1]);
                employee.setEmail(data[2]);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date jdate = simpleDateFormat.parse(data[3]);
                java.sql.Date dob = new java.sql.Date(jdate.getTime());
                employee.setDob(dob);
                employees.add(employee);
            }
            return employees;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
    public void exportToCSV(HttpServletResponse response,List<Employee> employeeList) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Employees.csv";
        response.setHeader(headerKey, headerValue);
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"Employee Code", "Employee Name", "Location","Email", "DOB"};
        String[] nameMapping = {"employeeCode", "employeeName", "location", "email","dob"};
        csvWriter.writeHeader(csvHeader);
        for(Employee employee:employeeList){
            csvWriter.write(employee,nameMapping);
        }
        csvWriter.close();
    }
}

package com.ss.demo.controller;

import com.ss.demo.entity.Employee;
import com.ss.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/getEmployee/{id}")
    public Employee getEmployeById(@PathVariable Integer id){
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/getEmployees")
    public List<Employee> getEmployees(){
       return employeeService.getEmployee();
    }

    @PostMapping("/createEmployee")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        int result = employeeService.putEmployee(employee);
        ResponseEntity<Employee> responseEntity;
        if (result == 1){
            responseEntity = new ResponseEntity<>(HttpStatus.OK);
        }else {
            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @PutMapping("/updateEmployee")
    public Employee updateEmployee(@RequestBody Employee employee){
        employeeService.updateEmployee(employee);
        return employee;
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable Integer id){
        employeeService.deleteEmployee(id);
        return "success";
    }
}
